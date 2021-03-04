/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public DataIO(String fileName) {
        this.saveFileName = fileName;
    }

    // ******************* Main methods *******************
    /**
     * Đọc file
     *
     * @return
     */
    public ArrayList<T> readData() {
        ArrayList<T> theList = new ArrayList<>();
        FileInputStream fin = null;
        ObjectInputStream in = null;
        try {
            fin = new FileInputStream("users.dat");
            in = new ObjectInputStream(fin);
            theList = (ArrayList<T>) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
