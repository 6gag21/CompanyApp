package logger.model;

import logger.util.LogLevel;
import logger.util.LoggerType;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class LoggerSettings {

    private LoggerType loggerType;
    private File fileToWrite;
    private LogLevel logLevel;
    private DateTimeFormatter dateTimeFormatter;
    private String logPrefix;

    public LoggerType getLoggerType() {
        return loggerType;
    }

    public File getFileToWrite() {
        return fileToWrite;
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

    public void setLoggerType(LoggerType loggerType) {
        this.loggerType = loggerType;
    }

    public void setFileToWrite(File fileToWrite) {
        this.fileToWrite = fileToWrite;
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
}
