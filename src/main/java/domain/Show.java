package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Show extends Entity<Integer> {
    private String name;
    private String location;
    private LocalDateTime dateAndTime;
    private String description;
    private String dateToString;

    public Show(String name, String location, LocalDateTime dateAndTime, String description) {
        this.name = name;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.description = description;

    }
    public Show() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateToString() {
        return dateToString;
    }

    public void setDateToString() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateToString = dateAndTime.format(myFormatObj);
    }

    @Override
    public String toString() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "Show{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", dateAndTime=" + dateAndTime.format(myFormatObj)+
                ", description='" + description + '\'' +
                '}';
    }
}
