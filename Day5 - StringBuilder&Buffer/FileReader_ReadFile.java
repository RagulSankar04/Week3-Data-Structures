import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReader_ReadFile {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("sample.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}