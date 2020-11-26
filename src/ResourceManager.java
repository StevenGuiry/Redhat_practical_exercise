import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {

    public static void write(ObservableList<ListEntry> data) {
        try {
            FileOutputStream fos = new FileOutputStream("saves/todolists.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<>(data));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ListEntry> load(Path file){
        try {
            InputStream is = Files.newInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            List<ListEntry> list = (List<ListEntry>) ois.readObject();
            return FXCollections.observableList(list);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return FXCollections.emptyObservableList();
    }
}
