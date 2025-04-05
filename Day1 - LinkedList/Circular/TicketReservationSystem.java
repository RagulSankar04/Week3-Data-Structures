class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

public class TicketReservationSystem {
    private Ticket head = null;
    private Ticket tail = null;

    // Add ticket at end
    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
    }

    // Remove ticket by Ticket ID
    public void removeTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket current = head;
        Ticket prev = tail;
        boolean found = false;

        do {
            if (current.ticketID == ticketID) {
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("Ticket ID not found.");
            return;
        }

        if (current == head && current == tail) { // Only one ticket
            head = tail = null;
        } else if (current == head) {
            head = head.next;
            tail.next = head;
        } else if (current == tail) {
            tail = prev;
            tail.next = head;
        } else {
            prev.next = current.next;
        }

        System.out.println("Ticket with ID " + ticketID + " removed.");
    }

    // Display all tickets
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        System.out.println("Current Bookings:");
        do {
            System.out.println("Ticket ID: " + current.ticketID +
                               ", Customer: " + current.customerName +
                               ", Movie: " + current.movieName +
                               ", Seat: " + current.seatNumber +
                               ", Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    // Search by customer or movie name
    public void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets to search.");
            return;
        }

        boolean found = false;
        Ticket current = head;
        System.out.println("Search Results for \"" + keyword + "\":");
        do {
            if (current.customerName.equalsIgnoreCase(keyword) ||
                current.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Ticket ID: " + current.ticketID +
                                   ", Customer: " + current.customerName +
                                   ", Movie: " + current.movieName +
                                   ", Seat: " + current.seatNumber +
                                   ", Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) System.out.println("No matching ticket found.");
    }

    // Count tickets
    public int countTickets() {
        if (head == null) return 0;
        int count = 0;
        Ticket current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);
        return count;
    }

    // Demo
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket(101, "Alice", "Avengers", "A1", "10:00 AM");
        system.addTicket(102, "Bob", "Inception", "B2", "1:00 PM");
        system.addTicket(103, "Charlie", "Avengers", "A2", "10:00 AM");
        system.addTicket(104, "Diana", "Interstellar", "C3", "3:30 PM");

        system.displayTickets();

        System.out.println("\n-- Search by Movie --");
        system.searchTicket("Avengers");

        System.out.println("\n-- Remove Ticket ID 102 --");
        system.removeTicket(102);
        system.displayTickets();

        System.out.println("\n-- Total Booked Tickets: " + system.countTickets());
    }
}
