package util;

import model.Settings;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class ProjectManager {

    private static ProjectManager instance;
    private Settings settings;

    private ProjectManager() {
        try {
            settings = XmlParser.parse();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        if (instance == null) {
            instance = new ProjectManager();
        }
        LangManager.init();
    }

    public static ProjectManager getInstance() {
        init();
        return instance;
    }

    public Settings getSettings() {
        return settings;
    }
}
