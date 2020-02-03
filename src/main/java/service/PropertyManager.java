package service;

import util.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyManager {
    public static final String defaultPropPath = "src/main/resources/lang/messages_en_US.properties";
    public static final String enLangPath = "src/main/resources/lang/messages_en_US.properties";
    public static final String ruLangPath = "src/main/resources/lang/messages_ru_RU.properties";

    private static PropertyManager instance = null;
    private static Properties properties = null;
    private PropertiesListener propertiesListenerForService = new Service();
    private PropertiesListener propertiesListenerForCommand = Command.ADD;

    private PropertyManager(){
        setDefaultProperties();
    }

    public static PropertyManager getInstance(){
        if(instance == null){
            instance = new PropertyManager();
        }
        return instance;
    }

    private void loadProperties(String path) {

        try(FileInputStream in = new FileInputStream(path) ) {
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProperties(String path){
        loadProperties(path);
        propertiesListenerForService.changeProperties();
        propertiesListenerForCommand.changeProperties();
    }

    public Properties getProperties(){
        return properties;
    }

    private void setDefaultProperties(){
        loadProperties(defaultPropPath);
    }

    public interface PropertiesListener {
        void changeProperties();
    }

}
