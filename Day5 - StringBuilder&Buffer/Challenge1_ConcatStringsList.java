import java.util.ArrayList;
import java.util.List;

public class Challenge1_ConcatStringsList {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            strings.add("hello");
        }

        long startTime1 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str);
        }
        long endTime1 = System.nanoTime();
        System.out.println("Time taken by StringBuilder: " + (endTime1 - startTime1) + " ns");

        long startTime2 = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (String str : strings) {
            sbf.append(str);
        }
        long endTime2 = System.nanoTime();
        System.out.println("Time taken by StringBuffer: " + (endTime2 - startTime2) + " ns");
    }
}