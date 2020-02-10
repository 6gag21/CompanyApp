package util;

import service.Service;
import java.util.Locale;
import java.util.ResourceBundle;

public final class PropertyManager {

    private static PropertyManager instance;
    private PropertiesListener propertiesListenerForService;
    private static Locale currentLocale;
    private static ResourceBundle properties;
    public static Locale[] locales = {new Locale("en", "US"), new Locale("ru", "RU")};

    private PropertyManager(){
        currentLocale = Locale.ENGLISH;
        loadProperties(currentLocale);
        propertiesListenerForService = new Service();
    }

    public static PropertyManager getInstance(){
        if(instance == null){
            instance = new PropertyManager();
        }
        return instance;
    }

    private void loadProperties(Locale locale) {
        properties = ResourceBundle.getBundle("lang/messages", locale);
    }

    public void setProperties(Locale locale){
        loadProperties(locale);
        propertiesListenerForService.changeProperties();
    }

    public ResourceBundle getProperties(){
        return properties;
    }

    public interface PropertiesListener {
        void changeProperties();
    }

}
