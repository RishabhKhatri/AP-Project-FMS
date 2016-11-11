import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 11/3/2016.
 */
public class Main {
    public static void writeFile(String fileName) {
        // Write csv files
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            CSVWriter writer = new CSVWriter(new FileWriter("data.csv", true));
            String[] entries = "first,second,third".split(",");
            writer.writeNext(entries);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String fileName) {
        // Read csv files
        try {
            CSVReader reader = new CSVReader(new FileReader(new File("data.csv")));
            String[] line;
            while ((line = reader.readNext())!=null) {
                System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
            }
            reader.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static void main(String[] args) {
        writeFile("data.csv");
        readFile("data.csv");
    }
}
