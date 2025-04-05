class TextState {
    String content;
    TextState prev, next;

    public TextState(String content) {
        this.content = content;
        this.prev = this.next = null;
    }
}

public class TextEditor {
    private TextState head = null; // oldest
    private TextState tail = null; // newest
    private TextState current = null;
    private int size = 0;
    private final int MAX_HISTORY = 10;

    // Add new state
    public void addState(String content) {
        TextState newState = new TextState(content);

        // If user adds a new state after undoing, clear forward history
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
            size = countStates(); // adjust size
        }

        if (head == null) {
            head = tail = current = newState;
        } else {
            tail.next = newState;
            newState.prev = tail;
            tail = newState;
            current = newState;
        }

        size++;

        // Trim history if it exceeds max size
        if (size > MAX_HISTORY) {
            head = head.next;
            if (head != null) head.prev = null;
            size--;
        }
    }

    // Undo: go back one step
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo available.");
        }
    }

    // Redo: go forward one step
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo available.");
        }
    }

    // Display current state
    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: \"" + current.content + "\"");
        } else {
            System.out.println("Editor is empty.");
        }
    }

    // Helper to count current states in list
    private int countStates() {
        int count = 0;
        TextState temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Demo
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.addState("Hello");
        editor.addState("Hello, World");
        editor.addState("Hello, World!");
        editor.displayCurrentState(); // "Hello, World!"

        System.out.println("\n-- Undo twice --");
        editor.undo();
        editor.displayCurrentState(); // "Hello, World"
        editor.undo();
        editor.displayCurrentState(); // "Hello"

        System.out.println("\n-- Redo once --");
        editor.redo();
        editor.displayCurrentState(); // "Hello, World"

        System.out.println("\n-- New action after undo --");
        editor.addState("Hello, Universe!");
        editor.displayCurrentState(); // "Hello, Universe!"

        System.out.println("\n-- Undo to empty state --");
        for (int i = 0; i < 5; i++) editor.undo();
        editor.displayCurrentState();
    }
}
