import java.util.Scanner;

class MovieNode {
    String title;
    String director;
    int year;
    double rating;
    MovieNode next;
    MovieNode prev;

    public MovieNode(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

public class MovieManagementSystem {
    private MovieNode head = null;
    private MovieNode tail = null;

    // Add at beginning
    public void addAtBeginning(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Add at end
    public void addAtEnd(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Add at position (0-indexed)
    public void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position == 0) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        MovieNode newNode = new MovieNode(title, director, year, rating);
        MovieNode temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
        } else {
            newNode.next = temp.next;
            newNode.prev = temp;
            temp.next.prev = newNode;
            temp.next = newNode;
        }
    }

    // Delete by movie title
    public void deleteByTitle(String title) {
        MovieNode temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Movie not found.");
            return;
        }

        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        System.out.println("Movie deleted successfully.");
    }

    // Search by director
    public void searchByDirector(String director) {
        MovieNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movies found for director: " + director);
        }
    }

    // Search by rating
    public void searchByRating(double rating) {
        MovieNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movies found with rating: " + rating);
        }
    }

    // Update rating by title
    public void updateRating(String title, double newRating) {
        MovieNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }

    // Display all forward
    public void displayForward() {
        if (head == null) {
            System.out.println("No movies to display.");
            return;
        }
        System.out.println("Movies (Forward):");
        MovieNode temp = head;
        while (temp != null) {
            printMovie(temp);
            temp = temp.next;
        }
    }

    // Display all reverse
    public void displayReverse() {
        if (tail == null) {
            System.out.println("No movies to display.");
            return;
        }
        System.out.println("Movies (Reverse):");
        MovieNode temp = tail;
        while (temp != null) {
            printMovie(temp);
            temp = temp.prev;
        }
    }

    private void printMovie(MovieNode m) {
        System.out.println("Title: " + m.title + ", Director: " + m.director + ", Year: " + m.year + ", Rating: " + m.rating);
    }

    public static void main(String[] args) {
        MovieManagementSystem mms = new MovieManagementSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Movie Management System ---");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Position");
            System.out.println("4. Delete by Title");
            System.out.println("5. Search by Director");
            System.out.println("6. Search by Rating");
            System.out.println("7. Update Rating");
            System.out.println("8. Display All (Forward)");
            System.out.println("9. Display All (Reverse)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            String title, director;
            int year, position;
            double rating;

            switch (choice) {
                case 1:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    title = sc.nextLine();
                    director = sc.nextLine();
                    year = sc.nextInt();
                    rating = sc.nextDouble();
                    mms.addAtBeginning(title, director, year, rating);
                    break;
                case 2:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    title = sc.nextLine();
                    director = sc.nextLine();
                    year = sc.nextInt();
                    rating = sc.nextDouble();
                    mms.addAtEnd(title, director, year, rating);
                    break;
                case 3:
                    System.out.print("Enter Position, Title, Director, Year, Rating: ");
                    position = sc.nextInt();
                    sc.nextLine(); // consume newline
                    title = sc.nextLine();
                    director = sc.nextLine();
                    year = sc.nextInt();
                    rating = sc.nextDouble();
                    mms.addAtPosition(position, title, director, year, rating);
                    break;
                case 4:
                    System.out.print("Enter Movie Title to delete: ");
                    title = sc.nextLine();
                    mms.deleteByTitle(title);
                    break;
                case 5:
                    System.out.print("Enter Director Name to search: ");
                    director = sc.nextLine();
                    mms.searchByDirector(director);
                    break;
                case 6:
                    System.out.print("Enter Rating to search: ");
                    rating = sc.nextDouble();
                    mms.searchByRating(rating);
                    break;
                case 7:
                    System.out.print("Enter Movie Title and New Rating: ");
                    title = sc.nextLine();
                    rating = sc.nextDouble();
                    mms.updateRating(title, rating);
                    break;
                case 8:
                    mms.displayForward();
                    break;
                case 9:
                    mms.displayReverse();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
        sc.close();
    }
}
