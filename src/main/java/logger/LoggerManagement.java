package logger;

public interface LoggerManagement {
    void write(String message);
    void startLogging();
    void stopLogging();
}
