import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Challenge2_WordsInFile {
    public static void main(String[] args) {
        String filePath = "large_text_file.txt";
        int wordCount = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            long startTime = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            long endTime = System.nanoTime();
            br.close();

            System.out.println("Word count: " + wordCount);
            System.out.println("Time taken to read file: " + (endTime - startTime) + " ns");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
