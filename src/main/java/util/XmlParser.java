package util;

import model.Settings;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class XmlParser {
    public static final String FILE_PATH = "src/main/resources/config.xml";

    public static Settings parse() throws ParserConfigurationException, SAXException, IOException {
        XmlHandler xmlHandler = new XmlHandler();
        SAXParserFactory.newInstance().newSAXParser().parse(FILE_PATH, xmlHandler);

        return xmlHandler.getSettings();
    }
}
