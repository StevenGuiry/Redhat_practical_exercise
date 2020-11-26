import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListEntry implements Serializable {

    public static int reference = 1;
    private String id;
    private String entryDescription;
    private String priorityLevel;

    //constructor
    public ListEntry (String entryDescription, String priorityLevel) {
        this.setEntryDescription(entryDescription);
        this.setPriorityLevel(priorityLevel);
        id = String.valueOf(reference);
        reference++;

    }
    //getter+setter
    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @Override
    public String toString() {
        return id + ' ' + entryDescription + "\t" + priorityLevel;
    }
}

