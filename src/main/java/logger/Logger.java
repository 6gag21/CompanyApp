package logger;

import model.Settings;
import util.ProjectManager;

import java.io.*;
import java.time.LocalDateTime;

public class Logger implements LoggerManagement {

    private Settings settings;
    private boolean isLogging;

    private Logger() {
        startLogging();
        this.settings = ProjectManager.getInstance().getSettings();
    }

    private void writeToFile(String message) {
        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(settings.getLogFile(), true)))) {
            printWriter.printf("%s | %s | %s | %s %n", LocalDateTime.now().format(settings.getDateTimeFormatter()),
                    settings.getLogLevel().toString(), settings.getLogPrefix(), message);
            printWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeToConsole(String message) {
        System.err.printf("%s | %s | %s | %s %n", LocalDateTime.now().format(settings.getDateTimeFormatter()),
                settings.getLogLevel().toString(), settings.getLogPrefix(), message);
    }

    @Override
    public void write(String message) {
        if (!isLogging) return;
        switch (settings.getLoggerType()) {
            case CONSOLE:
                writeToConsole(message);
                break;
            case FILE:
                writeToFile(message);
                break;
        }
    }

    public static Logger getLogger(String name) {
        return new Logger();
    }

    @Override
    public void startLogging() {
        isLogging = true;
    }

    @Override
    public void stopLogging() {
        isLogging = false;
    }
}
