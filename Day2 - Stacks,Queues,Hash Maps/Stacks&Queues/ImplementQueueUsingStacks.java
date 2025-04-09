import java.util.Stack;

public class ImplementQueueUsingStacks {
    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public ImplementQueueUsingStacks() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void enqueue(int x) {
        stackIn.push(x);
    }

    public int dequeue() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        if (stackOut.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackOut.pop();
    }

    public int peek() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        if (stackOut.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackOut.peek();
    }

    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public static void main(String[] args) {
        ImplementQueueUsingStacks queue = new ImplementQueueUsingStacks();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
    }
}
