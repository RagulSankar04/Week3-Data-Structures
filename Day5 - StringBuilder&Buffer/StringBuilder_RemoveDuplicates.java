import java.util.HashSet;

public class StringBuilder_RemoveDuplicates {
    public static void main(String[] args) {
        String str = "programming";
        StringBuilder sb = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!seen.contains(ch)) {
                seen.add(ch);
                sb.append(ch);
            }
        }

        System.out.println(sb.toString());
    }
}