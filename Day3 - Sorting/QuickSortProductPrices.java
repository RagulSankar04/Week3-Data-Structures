import java.util.Scanner;

public class QuickSortProductPrices {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of products: ");
        int n = scanner.nextInt();

        int[] prices = new int[n];

        System.out.println("Enter the prices of the products:");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        quickSort(prices, 0, n - 1);

        System.out.println("Sorted product prices in ascending order:");
        for (int price : prices) {
            System.out.print(price + " ");
        }
        scanner.close();
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
