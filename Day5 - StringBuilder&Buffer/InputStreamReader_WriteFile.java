import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReader_WriteFile {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            FileWriter writer = new FileWriter("output.txt");

            String line;
            System.out.println("Enter text (type 'exit' to stop):");
            while (!(line = br.readLine()).equalsIgnoreCase("exit")) {
                writer.write(line + System.lineSeparator());
            }

            br.close();
            writer.close();
            System.out.println("Data has been written to output.txt");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}