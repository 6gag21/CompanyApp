import service.Service;


public class Main {


    public static void main(String[] args){
//        new Service().run();
        Integer i = new Integer("245");
       String s = i.toString().intern();
        System.out.println(s);
    }
}
