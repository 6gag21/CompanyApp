package data;

import model.Employee;
import util.ProjectManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Data implements DataManagement<Map<String, Employee>> {

    File dataFile;

    public Data() {
        this.dataFile = ProjectManager.getInstance().getSettings().getDataFile();
    }


    @Override
    public void writeFile(Map<String, Employee> stringEmployeeMap) {
        try (ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            serial.writeObject(stringEmployeeMap);
            serial.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Employee> readFile() {
        HashMap<String, Employee> employeeHashMap;
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream serial = new ObjectInputStream(new FileInputStream(dataFile))) {
                employeeHashMap = (HashMap<String, Employee>) serial.readObject();
                return employeeHashMap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
