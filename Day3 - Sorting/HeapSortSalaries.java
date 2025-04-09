import java.util.Scanner;

public class HeapSortSalaries {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of applicants: ");
        int n = scanner.nextInt();

        int[] salaries = new int[n];

        System.out.println("Enter the expected salaries:");
        for (int i = 0; i < n; i++) {
            salaries[i] = scanner.nextInt();
        }

        heapSort(salaries);

        System.out.println("Sorted salary demands in ascending order:");
        for (int salary : salaries) {
            System.out.print(salary + " ");
        }
        scanner.close();
    }

    static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}