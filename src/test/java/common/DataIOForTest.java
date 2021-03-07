package common;

import common.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;

public final class DataIOForTest {

    private static final String FILE_SAVE_DATA = "users.dat";

    public List<User> readData() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_SAVE_DATA))) {
            return (List<User>) inputStream.readObject();
        } catch (Exception ex) {
            Logger.getLogger(DataIOForTest.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    public void writeData(List<User> users) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_DATA))) {
            objectOutputStream.writeObject(users);
        } catch (IOException ex) {
            Logger.getLogger(DataIOForTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
