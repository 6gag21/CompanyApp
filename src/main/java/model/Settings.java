package model;

import logger.util.LogLevel;
import logger.util.LoggerType;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class Settings {

    private LoggerType loggerType;
    private File logFile;
    private LogLevel logLevel;
    private DateTimeFormatter dateTimeFormatter;
    private String logPrefix;
    private File dataFile;

    public LoggerType getLoggerType() {
        return loggerType;
    }

    public File getLogFile() {
        return logFile;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public String getLogPrefix() {
        return logPrefix;
    }

    public File getDataFile() {
        return dataFile;
    }

    public void setLoggerType(LoggerType loggerType) {
        this.loggerType = loggerType;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public void setLogPrefix(String logPrefix) {
        this.logPrefix = logPrefix;
    }

    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }
}
