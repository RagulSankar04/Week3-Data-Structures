import java.util.Scanner;

class TaskNode {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    TaskNode next;

    public TaskNode(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

public class TaskScheduler {
    private TaskNode head = null;
    private TaskNode tail = null;
    private TaskNode current = null;

    // Add task at beginning
    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newNode = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newNode;
            tail.next = head;
            current = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
    }

    // Add task at end
    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newNode = new TaskNode(taskId, taskName, priority, dueDate);
        if (tail == null) {
            head = tail = newNode;
            tail.next = head;
            current = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    // Add at position (0-indexed)
    public void addAtPosition(int position, int taskId, String taskName, int priority, String dueDate) {
        if (position == 0) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }

        TaskNode newNode = new TaskNode(taskId, taskName, priority, dueDate);
        TaskNode temp = head;
        for (int i = 0; i < position - 1 && temp.next != head; i++) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        if (temp == tail) {
            tail = newNode;
        }
        tail.next = head;
    }

    // Remove by Task ID
    public void removeByTaskId(int taskId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        TaskNode temp = head;
        TaskNode prev = tail;
        boolean found = false;

        do {
            if (temp.taskId == taskId) {
                found = true;
                break;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("Task ID not found.");
            return;
        }

        if (head == tail && head.taskId == taskId) { // only one node
            head = tail = null;
            current = null;
        } else if (temp == head) {
            head = head.next;
            tail.next = head;
            current = head;
        } else if (temp == tail) {
            tail = prev;
            tail.next = head;
        } else {
            prev.next = temp.next;
        }

        System.out.println("Task removed successfully.");
    }

    // View current task and move to next
    public void viewAndMoveToNext() {
        if (current == null) {
            System.out.println("No tasks to display.");
            return;
        }
        printTask(current);
        current = current.next;
    }

    // Display all tasks from head
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }

        TaskNode temp = head;
        System.out.println("All Tasks:");
        do {
            printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    // Search by priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        TaskNode temp = head;
        boolean found = false;

        do {
            if (temp.priority == priority) {
                printTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No task found with priority " + priority);
        }
    }

    private void printTask(TaskNode t) {
        System.out.println("TaskID: " + t.taskId + ", Name: " + t.taskName + ", Priority: " + t.priority + ", Due Date: " + t.dueDate);
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Task Scheduler ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by Task ID");
            System.out.println("5. View Current Task and Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            int taskId, priority, position;
            String taskName, dueDate;

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID, Name, Priority, Due Date: ");
                    taskId = sc.nextInt();
                    sc.nextLine();
                    taskName = sc.nextLine();
                    priority = sc.nextInt();
                    sc.nextLine();
                    dueDate = sc.nextLine();
                    scheduler.addAtBeginning(taskId, taskName, priority, dueDate);
                    break;
                case 2:
                    System.out.print("Enter Task ID, Name, Priority, Due Date: ");
                    taskId = sc.nextInt();
                    sc.nextLine();
                    taskName = sc.nextLine();
                    priority = sc.nextInt();
                    sc.nextLine();
                    dueDate = sc.nextLine();
                    scheduler.addAtEnd(taskId, taskName, priority, dueDate);
                    break;
                case 3:
                    System.out.print("Enter Position, Task ID, Name, Priority, Due Date: ");
                    position = sc.nextInt();
                    taskId = sc.nextInt();
                    sc.nextLine();
                    taskName = sc.nextLine();
                    priority = sc.nextInt();
                    sc.nextLine();
                    dueDate = sc.nextLine();
                    scheduler.addAtPosition(position, taskId, taskName, priority, dueDate);
                    break;
                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    taskId = sc.nextInt();
                    scheduler.removeByTaskId(taskId);
                    break;
                case 5:
                    scheduler.viewAndMoveToNext();
                    break;
                case 6:
                    scheduler.displayAllTasks();
                    break;
                case 7:
                    System.out.print("Enter Priority to search: ");
                    priority = sc.nextInt();
                    scheduler.searchByPriority(priority);
                    break;
                case 0:
                    System.out.println("Exiting Task Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}
