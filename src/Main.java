import java.io.*;
import java.util.ArrayList;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 11/3/2016.
 */
public class Main {
    public static long id = 1000002;
    public Admin admin;
    public ArrayList<Supervisor> supervisors;
    public ArrayList<Staff> Staff;
    public Admin readAdmin() {
        Admin temp = null;
        try {
            File file = new File("Admin.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            temp = (Admin) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public void writeAdmin() {
        try {
            File file = new File("Admin.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(admin);
            objectOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Supervisor> readSupervisors() {
        ArrayList<Supervisor> temp = null;
        try {
            File file = new File("Supervisors.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            temp = (ArrayList<Supervisor>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public void writeSupervisors() {
        try {
            File file = new File("Supervisors.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(supervisors);
            objectOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Staff> readStaff() {
        ArrayList<Staff> temp = null;
        try {
            File file = new File("Staff.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            temp = (ArrayList<Staff>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public void writeStaff() {
        try {
            File file = new File("Staff.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(Staff);
            objectOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void go(Main main) {
        admin = readAdmin();
        supervisors = readSupervisors();
        Staff = readStaff();
        Interface frame = new Interface(main);
//        Staff = new ArrayList<>();
//        supervisors = new ArrayList<>();
//        admin = new Admin("Admin", 1000001, "admin", "admin", "1234567890", "admin@admin.com");
//        supervisors.add(new Supervisor("Ravi", 1000002, "ravi", "ravi",
//                "1234567890", "ravi@gmail.com", "Electricity"));
//        supervisors.add(new Supervisor("Pranav", 1000003, "pranav", "pranav",
//                "1234567890", "pranav@gmail.com", "HVAC"));
//        supervisors.add(new Supervisor("Prasoon", 1000004, "prasoon", "prasoon",
//                "1234567890", "prasoon@gmail.com", "Audio/Video"));
//        supervisors.add(new Supervisor("Parth", 1000005, "parth", "parth",
//                "1234567890", "parth@gmail.com", "Security"));
//        supervisors.add(new Supervisor("Pal", 1000002, "pal", "pal",
//                "1234567890", "pal@gmail.com", "Housekeeping"));
//        writeAdmin();
//        writeSupervisors();
    }
}
