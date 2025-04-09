import java.util.Scanner;

public class InsertionSortEmployeeIDs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of employees: ");
        int n = scanner.nextInt();

        int[] ids = new int[n];

        System.out.println("Enter the employee IDs:");
        for (int i = 0; i < n; i++) {
            ids[i] = scanner.nextInt();
        }

        for (int i = 1; i < n; i++) {
            int key = ids[i];
            int j = i - 1;

            while (j >= 0 && ids[j] > key) {
                ids[j + 1] = ids[j];
                j--;
            }

            ids[j + 1] = key;
        }

        System.out.println("Sorted employee IDs in ascending order:");
        for (int id : ids) {
            System.out.print(id + " ");
        }
        scanner.close();
    }
}
