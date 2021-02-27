/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Cách sử dụng: gọi (new DataIO\<Kiểu dữ liệu cần ghi\>()).đọcHoặcViếtGìĐó(mảng cần đọc hoặc viết)
 * @author 
 * @param <T>
 */
public final class DataIO<T> {

    private String USER_SAVE_FILE_NAME;

    public DataIO() {
    }

    public DataIO(String USER_SAVE_FILE_NAME) {
        this.USER_SAVE_FILE_NAME = USER_SAVE_FILE_NAME;
    }

    // ******************* Main methods *******************
    /**
     * Đọc file
     *
     * @return
     */
    public ArrayList<T> readData() {
        ArrayList<T> theList = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(USER_SAVE_FILE_NAME));
            theList = (ArrayList<T>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return theList;
    }

    /**
     * Ghi file
     *
     * @param theList
     */
    public void writeData(List<T> theList) {
        try {
            FileOutputStream fos = new FileOutputStream(USER_SAVE_FILE_NAME, false);
            PrintWriter writer = new PrintWriter(new File(USER_SAVE_FILE_NAME));
            writer.print("");
            writer.close();
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(theList);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
