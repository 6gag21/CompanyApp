package logger;

import logger.model.LoggerSettings;
import logger.xml.XmlParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class LogManager {

    private static LogManager instance;
    private LoggerSettings loggerSettings;

    private LogManager() {
        try {
            loggerSettings = XmlParser.parse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LogManager getLogManager(){
        if(instance == null){
            instance = new LogManager();
        }
        return instance;
    }

    public LoggerSettings getLoggerSettings(){
        return this.loggerSettings;
    }

}
