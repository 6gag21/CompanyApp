package logger;

public interface Ilogger {
    void write(String message);
    void startLogging();
    void stopLogging();
}
