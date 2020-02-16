package console;


import model.Employee;
import util.ProjectManager;
import util.LangManager;
import util.StringUtil;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Console {

    private static Scanner scanner;
    private static ResourceBundle properties;

    static{
        init();
    }

    private static void init(){
        ProjectManager.init();
        scanner = new Scanner(System.in);
        properties = LangManager.getInstance().getProperties();
    }

    public static String chooseLanguage(){
        Console.print(StringUtil.SELECT_LANGUAGE);
        return scanner.next().toUpperCase();
    }

    public static String readCommand(){
        return scanner.next();
    }

    public static Employee addEmployee(){
        Console.print(properties.getString("ENTER_FIRST_NAME"));
        String firstName = scanner.next();
        Console.print(properties.getString("ENTER_LAST_NAME"));
        String lastName = scanner.next();

        return new Employee(firstName, lastName);
    }

    public static String findEmployee(){
        Console.print(properties.getString("FOR_FIND"));

        return scanner.next();
    }

    public static String removeEmployee(){
        Console.print(properties.getString("FOR_REMOVE"));

        return scanner.next();
    }

    public static void print(String string){
        System.out.println(string);
    }
}
