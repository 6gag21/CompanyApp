package logger.xml;

import logger.model.LoggerSettings;
import logger.util.LogLevel;
import logger.util.LoggerType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class Handler extends DefaultHandler {

    private LoggerSettings loggerSettings;

    private boolean bType;
    private boolean bPathToFile;
    private boolean bLogLevel;
    private boolean bDateFormat;
    private boolean bLogPrefix;

    public LoggerSettings getLoggerSettings(){
        return this.loggerSettings;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(qName.equalsIgnoreCase("LOGGER")){
            loggerSettings = new LoggerSettings();
        }
        if (qName.equalsIgnoreCase("TYPE")) {
            bType = true;
        }
        if (qName.equalsIgnoreCase("pathToFile")) {
            bPathToFile = true;
        }
        if (qName.equalsIgnoreCase("logLevel")) {
            bLogLevel = true;
        }
        if (qName.equalsIgnoreCase("dateFormat")) {
            bDateFormat = true;
        }
        if (qName.equalsIgnoreCase("logPrefix")) {
            bLogPrefix = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (bType) {
            loggerSettings.setLoggerType(LoggerType.valueOf(new String(ch, start, length)));
            bType = false;
        } else if (bPathToFile) {
            loggerSettings.setFileToWrite(new File(new String(ch, start, length)));
            bPathToFile = false;
        } else if (bLogLevel) {
            loggerSettings.setLogLevel(LogLevel.valueOf(new String(ch, start, length)));
            bLogLevel = false;
        } else if (bDateFormat) {
            loggerSettings.setDateTimeFormatter(DateTimeFormatter.ofPattern(new String(ch, start, length)));
            bDateFormat = false;
        } else if (bLogPrefix){
            loggerSettings.setLogPrefix(new String(ch, start, length));
            bLogPrefix = false;
        }
    }
}
