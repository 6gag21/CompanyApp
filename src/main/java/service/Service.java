package service;

import console.Console;
import logger.Logger;
import logger.xml.XmlParser;
import model.*;

import java.io.*;
import java.util.*;
import java.util.logging.LogManager;

import org.xml.sax.SAXException;
import util.PropertyManager;
import util.StringUtil;

import javax.xml.parsers.ParserConfigurationException;

public class Service implements PropertyManager.PropertiesListener {
    public static final String DATA_FILE_PATH = "C:\\Users\\Morrison\\Desktop\\data.bin";
    private HashMap<String, Employee> employeeHashMap;

   private static PropertyManager propertyManager;
   private static ResourceBundle properties;
   private static  Logger logger;

    private void add() {
        logger.write("Add method start");
        Employee employee = Console.addEmployee();
        employeeHashMap.put(employee.getLastName(), employee);
        Console.print(properties.getString("EMPLOYEE_ADDED"));
    }

    private void find() {
        String lastName = Console.findEmployee();
        if(checkByLastName(lastName)){
            Employee employee = employeeHashMap.get(lastName);
            Console.print(properties.getString("FIRST_NAME") + employee.getFirstName()+ " | "
                        + properties.getString("LAST_NAME") + employee.getLastName());
        }else {
            Console.print(properties.getString("WRONG_LAST_NAME"));
        }
    }

    private void print() {
        if(employeeHashMap.isEmpty()){
            Console.print(properties.getString("EMPLOYEE_LIST_IS_EMPTY"));
        }else{

            Collection<Employee> collection = employeeHashMap.values();
            for (Employee employee : collection)
                Console.print(properties.getString("FIRST_NAME") + employee.getFirstName() + " | "
                        + properties.getString("LAST_NAME") + employee.getLastName());
        }
    }

    private void remove() {
        String lastName = Console.removeEmployee();
        if(checkByLastName(lastName)){
            employeeHashMap.remove(lastName);
            Console.print(properties.getString("EMPLOYEE_REMOVED"));
        } else {
            Console.print(properties.getString("WRONG_LAST_NAME"));
        }
    }

    private boolean checkByLastName(String lastName){
        return employeeHashMap.containsKey(lastName);
    }

    private boolean readCommand(String command) {

        switch (command.toUpperCase()) {
            case "ADD":
                add();
                break;
            case "FIND":
                find();
                break;
            case "PRINT":
                print();
                break;
            case "REMOVE":
                remove();
                break;
            case "LANGUAGE":
                chooseLanguage();
                break;
            case "CLOSE":
                writeFile();
                return false;

            default:
                Console.print(properties.getString("WRONG_COMMAND"));

        }
        return true;
    }

    public  void run(){
        logger = Logger.getLogger("Test");
        logger.write("START PROGRAM");
        init();
        if (readFile() != null){
            employeeHashMap = (HashMap<String, Employee>) readFile();
        }

        boolean b = true;
        chooseLanguage();

        while(b) {
            Console.print(properties.getString("ENTER_COMMAND"));
            b = readCommand(Console.readCommand());
        }

    }

    private void init(){
        logger.write("Init method start");
        propertyManager = PropertyManager.getInstance();
        employeeHashMap = new HashMap<>();
    }

    private void chooseLanguage(){
        String str = Console.chooseLanguage();
        if(str.equals(StringUtil.EN)) {
            propertyManager.setProperties(PropertyManager.locales[0]);
        }
        else if(str.equals(StringUtil.RU))  {
            propertyManager.setProperties(PropertyManager.locales[1]);
        }
        else{
            Console.print(StringUtil.WRONG_LANGUAGE);
            chooseLanguage();
        }
        Console.print(properties.getString("MANUAL"));
    }

    @Override

    public  void changeProperties() {
        properties = propertyManager.getProperties();
    }
    private void writeFile(){
        try(ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH))){
            serial.writeObject(employeeHashMap);
            serial.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readFile(){
        File file = new File(DATA_FILE_PATH);
        Object obj;
        if(!file.exists()){
            try {
               file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            {
            try (ObjectInputStream serial = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH))) {
                obj = serial.readObject();
                return obj;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();

            }
        }
        return null;
    }
}
