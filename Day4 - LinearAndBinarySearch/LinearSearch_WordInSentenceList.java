public class LinearSearch_WordInSentenceList {
    public static void main(String[] args) {
        String[] sentences = {
            "The sky is blue.",
            "The grass is green.",
            "The sun is shining."
        };
        String targetWord = "grass";
        String result = "Not Found";
        for (String sentence : sentences) {
            if (sentence.contains(targetWord)) {
                result = sentence;
                break;
            }
        }
        System.out.println("Sentence containing the word: " + result);
    }
}