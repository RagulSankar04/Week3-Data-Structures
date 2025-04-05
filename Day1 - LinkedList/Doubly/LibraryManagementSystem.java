class Book {
    String title;
    String author;
    String genre;
    int bookID;
    boolean isAvailable;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

public class LibraryManagementSystem {
    private Book head;
    private Book tail;

    // Add at beginning
    public void addAtBeginning(String title, String author, String genre, int bookID, boolean available) {
        Book newBook = new Book(title, author, genre, bookID, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    // Add at end
    public void addAtEnd(String title, String author, String genre, int bookID, boolean available) {
        Book newBook = new Book(title, author, genre, bookID, available);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    // Add at specific position
    public void addAtPosition(String title, String author, String genre, int bookID, boolean available, int position) {
        if (position <= 0 || head == null) {
            addAtBeginning(title, author, genre, bookID, available);
            return;
        }

        Book newBook = new Book(title, author, genre, bookID, available);
        Book current = head;
        int index = 0;

        while (current != null && index < position - 1) {
            current = current.next;
            index++;
        }

        if (current == null || current.next == null) {
            addAtEnd(title, author, genre, bookID, available);
        } else {
            newBook.next = current.next;
            newBook.prev = current;
            current.next.prev = newBook;
            current.next = newBook;
        }
    }

    // Remove by Book ID
    public void removeByID(int bookID) {
        Book current = head;

        while (current != null && current.bookID != bookID) {
            current = current.next;
        }

        if (current == null) return; // Not found

        if (current == head) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (current == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    // Search by title
    public Book searchByTitle(String title) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) return current;
            current = current.next;
        }
        return null;
    }

    // Search by author
    public Book searchByAuthor(String author) {
        Book current = head;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) return current;
            current = current.next;
        }
        return null;
    }

    // Update availability
    public void updateAvailability(int bookID, boolean available) {
        Book current = head;
        while (current != null) {
            if (current.bookID == bookID) {
                current.isAvailable = available;
                return;
            }
            current = current.next;
        }
    }

    // Display all books forward
    public void displayForward() {
        System.out.println("Library Books (Forward):");
        Book current = head;
        while (current != null) {
            printBook(current);
            current = current.next;
        }
    }

    // Display all books reverse
    public void displayReverse() {
        System.out.println("Library Books (Reverse):");
        Book current = tail;
        while (current != null) {
            printBook(current);
            current = current.prev;
        }
    }

    // Count total books
    public int countBooks() {
        int count = 0;
        Book current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Utility method
    private void printBook(Book book) {
        System.out.printf("ID: %d | Title: %s | Author: %s | Genre: %s | Available: %s\n",
                book.bookID, book.title, book.author, book.genre, book.isAvailable ? "Yes" : "No");
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        library.addAtEnd("The Alchemist", "Paulo Coelho", "Fiction", 101, true);
        library.addAtBeginning("Atomic Habits", "James Clear", "Self-Help", 102, true);
        library.addAtEnd("To Kill a Mockingbird", "Harper Lee", "Classic", 103, false);
        library.addAtPosition("1984", "George Orwell", "Dystopian", 104, true, 2);

        library.displayForward();
        library.displayReverse();

        System.out.println("\nUpdating availability of Book ID 103 to Available...");
        library.updateAvailability(103, true);
        library.displayForward();

        System.out.println("\nSearching for book by Title '1984'...");
        Book found = library.searchByTitle("1984");
        if (found != null) library.printBook(found);

        System.out.println("\nRemoving Book ID 102...");
        library.removeByID(102);
        library.displayForward();

        System.out.println("\nTotal Books: " + library.countBooks());
    }
}
