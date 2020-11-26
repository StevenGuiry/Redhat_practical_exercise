import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.nio.file.Paths;

public class MainController implements Serializable {

    @FXML
    private Button addButton, deleteButton, updateButton, saveButton;
    @FXML
    private TextField entryTextField, updateEntryTextField;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ListView<ListEntry> toDoList;

    ObservableList<ListEntry> observableList;
    ObservableList<String> priorityLevels;


    public void initialize() {
        observableList = ResourceManager.load(Paths.get("saves/todolists.txt"));
        toDoList.setItems(observableList);

        priorityLevels = FXCollections.observableArrayList("High Priority", "Moderate Priority", "Low Priority");
        comboBox.setItems(priorityLevels);
    }

    @FXML
    private void addEntry(Event e) {
        observableList.add(new ListEntry(entryTextField.getText(),comboBox.getValue() == null ? "No Priority" : comboBox.getValue()));
        toDoList.setItems(observableList);
        entryTextField.clear();
    }

    @FXML
    private void deleteEntry(Event e) {
        if (validIndex(toDoList.getSelectionModel().getSelectedIndex())) {
            observableList.remove(toDoList.getSelectionModel().getSelectedItem());
            toDoList.setItems(observableList);
            ListEntry.reference--;
            toDoList.refresh();
        }
    }

    @FXML
    private void updateEntry(Event e) {
        if (validIndex(toDoList.getSelectionModel().getSelectedIndex())) {
            int index = toDoList.getSelectionModel().getSelectedIndex();
            observableList.get(index).setEntryDescription(updateEntryTextField.getText());
            toDoList.refresh();
            updateEntryTextField.clear();
        }
    }

    private boolean validIndex (int index) {
        return index >= 0 && index < observableList.size();
    }

    public void save() {
        ResourceManager.write(observableList);
    }
}
