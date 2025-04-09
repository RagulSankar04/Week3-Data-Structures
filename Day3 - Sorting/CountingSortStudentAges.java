import java.util.Scanner;

public class CountingSortStudentAges {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();

        int[] ages = new int[n];

        System.out.println("Enter the ages (between 10 and 18):");
        for (int i = 0; i < n; i++) {
            ages[i] = scanner.nextInt();
        }

        int[] count = new int[9];
        int[] output = new int[n];

        for (int i = 0; i < n; i++) {
            count[ages[i] - 10]++;
        }

        for (int i = 1; i < 9; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[ages[i] - 10] - 1] = ages[i];
            count[ages[i] - 10]--;
        }

        System.out.println("Sorted student ages in ascending order:");
        for (int age : output) {
            System.out.print(age + " ");
        }
        scanner.close();
    }
}
