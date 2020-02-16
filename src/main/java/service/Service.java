package service;

import console.Console;
import data.Data;
import logger.Logger;
import model.*;

import java.util.*;

import util.LangManager;
import util.StringUtil;

public class Service implements LangManager.PropertiesListener {

    private Map<String, Employee> employeeHashMap;
    private static Data data;
    private static LangManager langManager;
    private static ResourceBundle properties;
    private static Logger logger;

    private void add() {
        logger.write("Add method start");
        Employee employee = Console.addEmployee();
        employeeHashMap.put(employee.getLastName(), employee);
        Console.print(properties.getString("EMPLOYEE_ADDED"));
    }

    private void find() {
        String lastName = Console.findEmployee();
        if (checkByLastName(lastName)) {
            Employee employee = employeeHashMap.get(lastName);
            Console.print(properties.getString("FIRST_NAME") + employee.getFirstName() + " | "
                    + properties.getString("LAST_NAME") + employee.getLastName());
        } else {
            Console.print(properties.getString("WRONG_LAST_NAME"));
        }
    }

    private void print() {
        if (employeeHashMap.isEmpty()) {
            Console.print(properties.getString("EMPLOYEE_LIST_IS_EMPTY"));
        } else {

            Collection<Employee> collection = employeeHashMap.values();
            for (Employee employee : collection)
                Console.print(properties.getString("FIRST_NAME") + employee.getFirstName() + " | "
                        + properties.getString("LAST_NAME") + employee.getLastName());
        }
    }

    private void remove() {
        String lastName = Console.removeEmployee();
        if (checkByLastName(lastName)) {
            employeeHashMap.remove(lastName);
            Console.print(properties.getString("EMPLOYEE_REMOVED"));
        } else {
            Console.print(properties.getString("WRONG_LAST_NAME"));
        }
    }

    private boolean checkByLastName(String lastName) {
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

    public void run() {
        logger = Logger.getLogger("Test");
        logger.write("START PROGRAM");
        init();
        if (readFile() != null) {
            employeeHashMap = readFile();
        }

        boolean b = true;
        chooseLanguage();

        while (b) {
            Console.print(properties.getString("ENTER_COMMAND"));
            b = readCommand(Console.readCommand());
        }

    }

    private void init() {
        logger.write("Init method start");
        langManager = LangManager.getInstance();
        employeeHashMap = new HashMap<>();
        data = new Data();
    }

    private void chooseLanguage() {
        String str = Console.chooseLanguage();
        if (str.equals(StringUtil.EN)) {
            langManager.setProperties(LangManager.getInstance().locales[0]);
        } else if (str.equals(StringUtil.RU)) {
            langManager.setProperties(LangManager.getInstance().locales[1]);
        } else {
            Console.print(StringUtil.WRONG_LANGUAGE);
            chooseLanguage();
        }
        Console.print(properties.getString("MANUAL"));
    }

    @Override

    public void changeProperties() {
        properties = langManager.getProperties();
    }

    private void writeFile() {
        data.writeFile(employeeHashMap);
    }

    private Map<String, Employee> readFile() {
        return data.readFile();
    }
}
