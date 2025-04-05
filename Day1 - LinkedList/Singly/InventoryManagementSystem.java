class Item {
    String itemName;
    int itemID;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemID, int quantity, double price) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

public class InventoryManagementSystem {
    private Item head;

    // Add item at beginning
    public void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Add item at end
    public void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    // Add item at specific position
    public void addAtPosition(String name, int id, int quantity, double price, int position) {
        if (position <= 0 || head == null) {
            addAtBeginning(name, id, quantity, price);
            return;
        }

        Item newItem = new Item(name, id, quantity, price);
        Item temp = head;
        int count = 0;

        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            addAtEnd(name, id, quantity, price);
        } else {
            newItem.next = temp.next;
            temp.next = newItem;
        }
    }

    // Remove item by Item ID
    public void removeByID(int id) {
        if (head == null) return;

        if (head.itemID == id) {
            head = head.next;
            return;
        }

        Item current = head;
        while (current.next != null && current.next.itemID != id) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Update quantity by Item ID
    public void updateQuantity(int id, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemID == id) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    // Search by ID
    public Item searchByID(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemID == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Search by name
    public Item searchByName(String name) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Calculate total value
    public double calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        return total;
    }

    // Merge Sort Helper Methods
    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Item mergeByName(Item a, Item b, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        if ((ascending && a.itemName.compareToIgnoreCase(b.itemName) <= 0) ||
            (!ascending && a.itemName.compareToIgnoreCase(b.itemName) > 0)) {
            a.next = mergeByName(a.next, b, ascending);
            return a;
        } else {
            b.next = mergeByName(a, b.next, ascending);
            return b;
        }
    }

    private Item mergeByPrice(Item a, Item b, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        if ((ascending && a.price <= b.price) || (!ascending && a.price > b.price)) {
            a.next = mergeByPrice(a.next, b, ascending);
            return a;
        } else {
            b.next = mergeByPrice(a, b.next, ascending);
            return b;
        }
    }

    private Item mergeSort(Item head, boolean byName, boolean ascending) {
        if (head == null || head.next == null) return head;

        Item middle = getMiddle(head);
        Item nextToMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, byName, ascending);
        Item right = mergeSort(nextToMiddle, byName, ascending);

        return byName ? mergeByName(left, right, ascending) : mergeByPrice(left, right, ascending);
    }

    public void sortByName(boolean ascending) {
        head = mergeSort(head, true, ascending);
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSort(head, false, ascending);
    }

    // Display inventory
    public void displayInventory() {
        Item temp = head;
        System.out.println("Inventory:");
        while (temp != null) {
            System.out.printf("ID: %d | Name: %s | Qty: %d | Price: %.2f\n",
                    temp.itemID, temp.itemName, temp.quantity, temp.price);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem inventory = new InventoryManagementSystem();
        inventory.addAtEnd("Apple", 101, 50, 1.5);
        inventory.addAtBeginning("Banana", 102, 30, 0.8);
        inventory.addAtEnd("Orange", 103, 20, 1.2);
        inventory.addAtPosition("Mango", 104, 25, 2.0, 2);

        inventory.displayInventory();
        System.out.println("Total Inventory Value: " + inventory.calculateTotalValue());

        System.out.println("\nSorted by Price (Descending):");
        inventory.sortByPrice(false);
        inventory.displayInventory();

        System.out.println("\nRemoving Item with ID 102...");
        inventory.removeByID(102);
        inventory.displayInventory();
    }
}
