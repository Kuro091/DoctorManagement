/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import doctor.Specialization;
import doctor.Doctor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author nanht
 */
public final class Validate {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final Pattern PATTERN_USERNAME = Pattern.compile("^[A-Za-z][A-Za-z0-9]{4,}$");
    static final Pattern PATTERN_PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,}$");

    private Validate() {
        throw new UnsupportedOperationException();
    }

    public static String getPassword(String msg) throws IOException {
        while (true) {
            System.out.print(msg);
            String check = in.readLine().trim();
            if (PATTERN_PASSWORD.matcher(check).find()) {
                return check;
            } else {
                System.out.println(ConsoleColors.RED + "Wrong format! (Password >=6 char, include both number and char, not include any other type of char)");
                System.out.println(ConsoleColors.RED + "Enter again: ");
            }
        }
    }

    public static String getUsername(String msg) throws IOException {
        while (true) {
            System.out.print(msg);
            String check = in.readLine().trim();
            if (PATTERN_USERNAME.matcher(check).find()) {
                return check;
            } else {
                System.out.println(ConsoleColors.RED + "Wrong format! (Password >=5 char, starts with character)");
                System.err.println("Enter again: ");
            }
        }
    }

    public static String getString(String msg) throws IOException {
        while (true) {
            System.out.println(msg);
            String check = in.readLine().trim();
            if (check.isEmpty()) {
                System.out.println(ConsoleColors.RED + "Input is not empty");
            } else {
                return check;
            }
        }
    }

    public static int getIntLimit(String msg, int min, int max) throws IOException {
        while (true) {
            try {
                System.out.println(msg);
                int number = Integer.parseInt(in.readLine());
                if (number < min || number > max) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Valid input are in the range of[" + min + ", " + max + "]. ");
            }
        }
    }

    public static boolean getYesNo(String msg) throws IOException {
        while (true) {
            String check = getString(msg);
            if (check.equalsIgnoreCase("Y")) {
                return true;
            }
            if (check.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Enter Y/N(y/n)");
        }
    }

    public static Date getDateCurrent(String msg) throws IOException {
        Date now = new Date();
        while (true) {
            String check = getString(msg);
            SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
            fd.setLenient(false);
            try {
                Date date = fd.parse(check);
                if (date.before(now)) {
                    return date;
                }
                System.out.println(ConsoleColors.RED + "Enter \"Date\" type before " + fd.format(now));
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "That day was not found");
            }
        }
    }

    public static Specialization selectSpecialization() {
        Specialization theSpecialization = null;
        try {
            int count = 0;
            for (Specialization currentSpecialization : Specialization.values()) {
                count++;
                System.out.println(count + ". " + currentSpecialization.name());
            }
            theSpecialization = Specialization.values()[getIntLimit("Select specialization: ", 1, count) - 1];
        } catch (IOException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theSpecialization;
    }
}
