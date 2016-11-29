import java.io.*;
import java.util.ArrayList;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 11/3/2016.
 */
public class Main {
    public static long id = 1000002;
    public static Admin admin = new Admin("Rishabh", 1000001, "admin", "admin",
            "8505915101", "admin@admin.com");
    public static ArrayList<Supervisor> supervisors = new ArrayList<>();
    public static ArrayList<Staff> Staff = new ArrayList<>();
    public static Person readAdmin() {
        Person temp = null;
        try {
            File file = new File("Admin.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            temp = (Person)objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public static ArrayList<Person> readSupervisors() {
        ArrayList<Person> temp = null;
        try {
            File file = new File("Supervisors.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            temp = (ArrayList<Person>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public static void writeSupervisors(ArrayList<Person> supervisors) {
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
    public static ArrayList<Person> readStaff() {
        ArrayList<Person> temp = null;
        try {
            File file = new File("Staff.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            temp = (ArrayList<Person>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public static void writeStaff(ArrayList<Person> staff) {
        try {
            File file = new File("Staff.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(staff);
            objectOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        Admin = readAdmin();
//        Supervisors = readSupervisors();
//        Staff = readStaff();
        supervisors.add(new Supervisor("Ravi", 1000002, "ravi", "ravi",
                "9988776655", "ravi@jhatu.com", "Electricity"));
        supervisors.add(new Supervisor("Prasoon", 1000003, "prasoon", "prasoon",
                "9988776655", "prasoon@jhatu.com", "HVAC"));
        supervisors.add(new Supervisor("Siddharth", 1000002, "siddharth", "chandra",
                "9988776655", "siddharth@jhatu.com", "Security"));
        supervisors.add(new Supervisor("Naveen", 1000002, "naveen", "attri",
                "9988776655", "naveen@jhatu.com", "Audio/Video"));
        supervisors.add(new Supervisor("Mayank", 1000002, "mayank", "bhoria",
                "9988776655", "mayank@jhatu.com", "Housekeeping"));
        Interface frame = new Interface();
    }
}
