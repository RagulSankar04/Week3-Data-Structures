class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime = 0;
    int turnAroundTime = 0;
    Process next;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

public class RoundRobinScheduler {
    private Process tail = null;

    // Add process at end of circular list
    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (tail == null) {
            tail = newProcess;
            newProcess.next = newProcess;
        } else {
            newProcess.next = tail.next;
            tail.next = newProcess;
            tail = newProcess;
        }
    }

    // Remove process by ID
    public void removeProcess(int pid) {
        if (tail == null) return;

        Process curr = tail.next;
        Process prev = tail;

        do {
            if (curr.pid == pid) {
                if (curr == tail && curr.next == tail) {
                    tail = null; // only one node
                } else {
                    prev.next = curr.next;
                    if (curr == tail) tail = prev;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != tail.next);
    }

    // Display all processes
    public void displayQueue() {
        if (tail == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Current Process Queue:");
        Process curr = tail.next;
        do {
            System.out.printf("PID: %d | Remaining Time: %d | Priority: %d\n",
                    curr.pid, curr.remainingTime, curr.priority);
            curr = curr.next;
        } while (curr != tail.next);
        System.out.println();
    }

    // Simulate round robin scheduling
    public void simulateRoundRobin(int timeQuantum) {
        if (tail == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        Process curr = tail.next;
        int currentTime = 0;
        int totalProcesses = 0;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        // Count processes
        Process temp = tail.next;
        do {
            totalProcesses++;
            temp = temp.next;
        } while (temp != tail.next);

        System.out.println("=== Round Robin Scheduling Simulation ===");
        while (tail != null) {
            displayQueue();

            if (curr.remainingTime <= timeQuantum) {
                currentTime += curr.remainingTime;
                curr.remainingTime = 0;
                curr.turnAroundTime = currentTime;
                curr.waitingTime = curr.turnAroundTime - curr.burstTime;

                System.out.printf("Process %d completed. TAT: %d, WT: %d\n",
                        curr.pid, curr.turnAroundTime, curr.waitingTime);

                totalWaitingTime += curr.waitingTime;
                totalTurnAroundTime += curr.turnAroundTime;

                int completedPID = curr.pid;
                curr = curr.next;
                removeProcess(completedPID);
            } else {
                curr.remainingTime -= timeQuantum;
                currentTime += timeQuantum;
                curr = curr.next;
            }
        }

        double avgWT = (double) totalWaitingTime / totalProcesses;
        double avgTAT = (double) totalTurnAroundTime / totalProcesses;

        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);
        System.out.printf("Average Turn-Around Time: %.2f\n", avgTAT);
    }

    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        scheduler.addProcess(1, 5, 2);
        scheduler.addProcess(2, 3, 1);
        scheduler.addProcess(3, 8, 3);
        scheduler.addProcess(4, 6, 2);

        int timeQuantum = 2;
        scheduler.simulateRoundRobin(timeQuantum);
    }
}
