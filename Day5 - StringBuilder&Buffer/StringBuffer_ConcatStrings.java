public class StringBuffer_ConcatStrings {
    public static void main(String[] args) {
        String[] words = {"Java", "is", "fast"};
        StringBuffer sb = new StringBuffer();
        for (String word : words) {
            sb.append(word);
        }
        System.out.println(sb.toString());
    }
}