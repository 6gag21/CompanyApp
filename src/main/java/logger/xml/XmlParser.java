package logger.xml;


import logger.model.LoggerSettings;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class XmlParser {
    public static final String FILE_PATH = "src/main/resources/config.xml";

    public static LoggerSettings parse() throws ParserConfigurationException, SAXException, IOException {
        Handler handler = new Handler();
        SAXParserFactory.newInstance().newSAXParser().parse(FILE_PATH, handler);

        return handler.getLoggerSettings();
    }
}
