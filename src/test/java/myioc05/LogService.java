package myioc05;

import java.util.Calendar;

public class LogService {

    private String name;

    private String level;

    @Override
    public String toString() {
        return "LogService{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
