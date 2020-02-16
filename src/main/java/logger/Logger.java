package logger;

import logger.model.LoggerSettings;

import java.io.*;
import java.time.LocalDateTime;


public class Logger implements Ilogger {

    private LoggerSettings loggerSettings;
    private boolean isLogging;

    private Logger(){
        startLogging();
        this.loggerSettings = LogManager.getLogManager().getLoggerSettings();
    }

    private void writeToFile(String message){
        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(loggerSettings.getFileToWrite(), true)))) {
            printWriter.printf("%s | %s | %s | %s %n", LocalDateTime.now().format(loggerSettings.getDateTimeFormatter()),
                    loggerSettings.getLogLevel().toString(), loggerSettings.getLogPrefix(), message);
            printWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeToConsole(String message){
        System.err.printf("%s | %s | %s | %s %n", LocalDateTime.now().format(loggerSettings.getDateTimeFormatter()),
                loggerSettings.getLogLevel().toString(), loggerSettings.getLogPrefix(), message);
    }

    @Override
    public void write(String message) {
        if(!isLogging) return;
        switch (loggerSettings.getLoggerType()){
            case CONSOLE:
                writeToConsole(message);
                break;
            case FILE:
                writeToFile(message);
                break;
        }
    }

    public static Logger getLogger(String name){
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
