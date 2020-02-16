package util;

import logger.util.LogLevel;
import logger.util.LoggerType;
import model.Settings;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class XmlHandler extends DefaultHandler {

    private Settings settings;

    private boolean bType;
    private boolean bLogFile;
    private boolean bLogLevel;
    private boolean bDateFormat;
    private boolean bLogPrefix;
    private boolean bDataFile;

    public Settings getSettings() {
        return this.settings;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("LOGGER")) {
            settings = new Settings();
        }
        if (qName.equalsIgnoreCase("TYPE")) {
            bType = true;
        }
        if (qName.equalsIgnoreCase("pathToFile")) {
            bLogFile = true;
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
        if (qName.equalsIgnoreCase("pathToData")) {
            bDataFile = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (bType) {
            settings.setLoggerType(LoggerType.valueOf(new String(ch, start, length)));
            bType = false;
        } else if (bLogFile) {
            settings.setLogFile(new File(new String(ch, start, length)));
            bLogFile = false;
        } else if (bLogLevel) {
            settings.setLogLevel(LogLevel.valueOf(new String(ch, start, length)));
            bLogLevel = false;
        } else if (bDateFormat) {
            settings.setDateTimeFormatter(DateTimeFormatter.ofPattern(new String(ch, start, length)));
            bDateFormat = false;
        } else if (bLogPrefix) {
            settings.setLogPrefix(new String(ch, start, length));
            bLogPrefix = false;
        } else if (bDataFile) {
            settings.setDataFile(new File(new String(ch, start, length)));
            bDataFile = false;
        }
    }
}
