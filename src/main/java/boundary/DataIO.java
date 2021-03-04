/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import User.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cách sử dụng: gọi (new DataIO\<Kiểu dữ liệu cần ghi\>()).đọcHoặcViếtGìĐó(mảng
 * cần đọc hoặc viết)
 *
 * @author
 * @param <T>
 */
public final class DataIO<T> {

    private String saveFileName; // Default

    public DataIO() {
        saveFileName = "users.dat";
    }

    public DataIO(String USER_SAVE_FILE_NAME) {
        this.saveFileName = USER_SAVE_FILE_NAME;
    }

    // ******************* Main methods *******************
    /**
     * Đọc file
     *
     * @return
     */
    public ArrayList<T> readData() {
        ObjectInputStream in = null;
        ArrayList<T> theList = null;
        try {
            in = new ObjectInputStream(new FileInputStream("users.dat"));
            theList = (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(UserDataIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return theList;
    }

    /**
     * Ghi file
     *
     * @param theList
     */
    public void writeData(List<T> theList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(saveFileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(theList);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(UserDataIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
