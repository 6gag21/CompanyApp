package data;

public interface DataManagement<T> {

    void writeFile(T t);

    T readFile();
}
