public class StringConcatPerformance {
    public static void main(String[] args) {
        int n = 100000;

        long start = System.nanoTime();
        String str = "";
        for (int i = 0; i < n; i++) {
            str += "a";
        }
        long timeString = System.nanoTime() - start;
        System.out.println("Time taken with String: " + timeString / 1e6 + " ms");

        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long timeBuilder = System.nanoTime() - start;
        System.out.println("Time taken with StringBuilder: " + timeBuilder / 1e6 + " ms");

        start = System.nanoTime();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buffer.append("a");
        }
        long timeBuffer = System.nanoTime() - start;
        System.out.println("Time taken with StringBuffer: " + timeBuffer / 1e6 + " ms");
    }
}