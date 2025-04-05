import java.util.Scanner;

class StudentNode {
    int rollNo;
    String name;
    int age;
    String grade;
    StudentNode next;

    public StudentNode(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

public class StudentRecordManagement {
    private StudentNode head = null;

    // Add student at beginning
    public void addAtBeginning(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        newNode.next = head;
        head = newNode;
    }

    // Add student at end
    public void addAtEnd(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        if (head == null) {
            head = newNode;
        } else {
            StudentNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Add at specific position (0-indexed)
    public void addAtPosition(int pos, int rollNo, String name, int age, String grade) {
        if (pos == 0) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        StudentNode temp = head;
        for (int i = 0; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Delete by roll number
    public void deleteByRollNumber(int rollNo) {
        if (head == null) return;

        if (head.rollNo == rollNo) {
            head = head.next;
            return;
        }

        StudentNode temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Record not found.");
        } else {
            temp.next = temp.next.next;
        }
    }

    // Search student
    public void searchByRollNumber(int rollNo) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Found: " + temp.name + " | Age: " + temp.age + " | Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNo + " not found.");
    }

    // Update grade
    public void updateGradeByRollNumber(int rollNo, String newGrade) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    // Display all students
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        StudentNode temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    // Main method for basic menu
    public static void main(String[] args) {
        StudentRecordManagement srm = new StudentRecordManagement();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Position");
            System.out.println("4. Delete by Roll Number");
            System.out.println("5. Search by Roll Number");
            System.out.println("6. Update Grade");
            System.out.println("7. Display All");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            int rollNo, age, pos;
            String name, grade;

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    srm.addAtBeginning(rollNo, name, age, grade);
                    break;
                case 2:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    srm.addAtEnd(rollNo, name, age, grade);
                    break;
                case 3:
                    System.out.print("Enter Position, Roll No, Name, Age, Grade: ");
                    pos = sc.nextInt();
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    srm.addAtPosition(pos, rollNo, name, age, grade);
                    break;
                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    rollNo = sc.nextInt();
                    srm.deleteByRollNumber(rollNo);
                    break;
                case 5:
                    System.out.print("Enter Roll No to search: ");
                    rollNo = sc.nextInt();
                    srm.searchByRollNumber(rollNo);
                    break;
                case 6:
                    System.out.print("Enter Roll No and new Grade: ");
                    rollNo = sc.nextInt();
                    grade = sc.next();
                    srm.updateGradeByRollNumber(rollNo, grade);
                    break;
                case 7:
                    srm.displayAll();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 0);
        sc.close();
    }
}
