package service;

import console.Console;
import model.*;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import util.Command;
import util.PropertyKeys;
import util.StringUtil;

public class Service implements PropertyManager.PropertiesListener {
    public static final String FILE_PATH = "C:\\Users\\Morrison\\Desktop\\data.bin";

   private static PropertyManager propertyManager;
   private static Properties properties;
   private static Company company;

    private void add(Scanner scanner) {
        Console.print(properties.getProperty(PropertyKeys.ENTER_FIRST_NAME.name()));
        String firstName = scanner.next();
        Console.print(properties.getProperty(PropertyKeys.ENTER_LAST_NAME.name()));
        String lastName = scanner.next();
        company.employeeHashMap.put(lastName, new Employee(firstName, lastName));
        Console.print(properties.getProperty(PropertyKeys.EMPLOYEE_ADDED.name()));
    }

    private void find(Scanner scanner) {
        Console.print(properties.getProperty(PropertyKeys.FOR_FIND.name()));
        String lastName = scanner.next();
        if(checkByLastName(lastName)){
            Employee employee = company.employeeHashMap.get(lastName);
            Console.print(properties.getProperty(PropertyKeys.FIRST_NAME.name()) + employee.getFirstName()+ " | "
                        + properties.getProperty(PropertyKeys.LAST_NAME.name()) + employee.getLastName());
        }else {
            Console.print(properties.getProperty(PropertyKeys.WRONG_LAST_NAME.name()));
        }
    }

    private void print() {
        if(company.employeeHashMap.isEmpty()){
            Console.print(properties.getProperty(PropertyKeys.EMPLOYEE_LIST_IS_EMPTY.name()));
        }
        Collection<Employee> collection = company.employeeHashMap.values();
        for (Employee employee : collection)
            Console.print(properties.getProperty(PropertyKeys.FIRST_NAME.name()) + employee.getFirstName() + " | " + properties.getProperty(PropertyKeys.LAST_NAME.name()) + employee.getLastName());
    }

    private void remove(Scanner scanner) {
        Console.print(properties.getProperty(PropertyKeys.FOR_REMOVE.name()));
        String lastName = scanner.next();
        if(checkByLastName(lastName)){
            company.employeeHashMap.remove(lastName);
            Console.print(properties.getProperty(PropertyKeys.EMPLOYEE_REMOVED.name()));
        } else {
            Console.print(properties.getProperty(PropertyKeys.WRONG_LAST_NAME.name()));
        }
    }

    private boolean checkByLastName(String lastName){
        return company.employeeHashMap.containsKey(lastName);
    }

    private   boolean readCommand(String command, Scanner scanner) {
        if(!Command.map.containsKey(command)){
            Console.print(properties.getProperty(PropertyKeys.WRONG_COMMAND.name()));
            return true;
        }
        switch (Command.map.get(command)) {
            case ADD:
                add(scanner);
                break;
            case FIND:
                find(scanner);
                break;
            case PRINT:
                print();
                break;
            case REMOVE:
                remove(scanner);
                break;
            case CHOOSE_LANGUAGE:
                selectLanguage(scanner);
                break;
            case CLOSE:
                writeFile();
                return false;

        }
        return true;
    }

    public  void run(){
        init();
        if (readFile() != null){
            company.employeeHashMap = (HashMap<String, Employee>) readFile();
        }
        boolean b = true;
        Scanner scanner = Console.write();
        selectLanguage(scanner);
        while(b) {
            Console.print(properties.getProperty(PropertyKeys.ENTER_COMMAND.name()));
            b = readCommand(scanner.next().toUpperCase(), scanner);
        }
        scanner.close();
    }

    private void init(){
        propertyManager = PropertyManager.getInstance();
        company = new Company();
    }

    private void selectLanguage(Scanner scanner){
        Console.print(StringUtil.SELECT_LANGUAGE);
        String str = scanner.next().toUpperCase();
        if(str.equals(StringUtil.EN)) {
            propertyManager.setProperties(PropertyManager.enLangPath);
        }
        else if(str.equals(StringUtil.RU))  {
            propertyManager.setProperties(PropertyManager.ruLangPath);
        }
        else{
            Console.print(StringUtil.WRONG_LANGUAGE);
            selectLanguage(scanner);
        }
        Console.print(properties.getProperty(PropertyKeys.MANUAL.name()));
    }

    @Override

    public  void changeProperties() {
        properties = propertyManager.getProperties();
    }
    private void writeFile(){
        try(ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            serial.writeObject(company.employeeHashMap);
            serial.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readFile(){
        File file = new File(FILE_PATH);
        Object obj;
        if(!file.exists()){
            try {
               file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            {
            try (ObjectInputStream serial = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                obj = serial.readObject();
                return obj;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();

            }
        }
        return null;
    }
}
