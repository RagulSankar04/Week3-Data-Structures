public class Compare_Buffer_Builder {
    public static void main(String[] args) {
        int count = 1000000;

        long startTime1 = System.nanoTime();
        StringBuffer sb1 = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb1.append("hello");
        }
        long endTime1 = System.nanoTime();
        System.out.println("Time taken by StringBuffer: " + (endTime1 - startTime1) + " ns");

        long startTime2 = System.nanoTime();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb2.append("hello");
        }
        long endTime2 = System.nanoTime();
        System.out.println("Time taken by StringBuilder: " + (endTime2 - startTime2) + " ns");
    }
}