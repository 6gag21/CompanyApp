package util;

import service.Service;
import java.util.Locale;
import java.util.ResourceBundle;

public final class LangManager {

    private static LangManager instance;
    private PropertiesListener propertiesListenerForService;
    private Locale currentLocale;
    private ResourceBundle properties;
    public  Locale[] locales;

    private LangManager(){
        locales = new Locale[] {new Locale("en", "US"), new Locale("ru", "RU")};
        currentLocale = Locale.ENGLISH;
        loadProperties(currentLocale);
        propertiesListenerForService = new Service();
    }

    public static LangManager getInstance(){
        if(instance == null){
            instance = new LangManager();
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
