import java.util.Arrays;
import java.util.Random;

public class SearchTargetInDataset {
    public static boolean linearSearch(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) return true;
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static int[] generateRandomArray(int size, int bound) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(bound);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};
        int target = -1;

        for (int size : sizes) {
            int[] data = generateRandomArray(size, size * 2);

            long start = System.nanoTime();
            boolean foundLinear = linearSearch(data, target);
            long timeLinear = System.nanoTime() - start;

            start = System.nanoTime();
            boolean foundBinary = binarySearch(data, target);
            long timeBinary = System.nanoTime() - start;

            System.out.println("Dataset Size: " + size);
            System.out.println("Linear Search Time: " + timeLinear / 1e6 + " ms");
            System.out.println("Binary Search Time: " + timeBinary / 1e6 + " ms");
            System.out.println();
        }
    }
}
