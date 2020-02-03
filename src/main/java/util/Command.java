package util;

import service.PropertyManager;

import java.util.HashMap;
import java.util.Map;

public enum Command implements PropertyManager.PropertiesListener {
    ADD, FIND, PRINT, REMOVE, CLOSE, CHOOSE_LANGUAGE;


    private String command;
    private static PropertyManager propertyManager;

  public static Map<String, Command> map =  new HashMap<>();



  static {
      propertyManager = PropertyManager.getInstance();
      setCommand();
      changeMap();
  }



  private static void changeMap() {
       map.clear();
       for (Command c : Command.values()) {
           map.put(c.command, c);

       }
   }
   private static void setCommand(){
       for (Command c : Command.values()){
           c.command = propertyManager.getProperties().getProperty(c.name());
       }
   }


    @Override
    public void changeProperties() {
        setCommand();
        changeMap();
    }
}
