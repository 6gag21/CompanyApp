package console;


import java.util.Scanner;

public class Console {

    public static Scanner write(){
        return new Scanner(System.in);
    }

    public static void print(String string){
        System.out.println(string);
    }
}
