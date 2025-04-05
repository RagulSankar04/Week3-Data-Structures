import java.util.ArrayList;
import java.util.HashSet;

class User {
    int userID;
    String name;
    int age;
    ArrayList<Integer> friendIDs;
    User next;

    public User(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendIDs = new ArrayList<>();
        this.next = null;
    }
}

public class SocialMediaNetwork {
    private User head = null;

    // Add user
    public void addUser(int userID, String name, int age) {
        if (getUserByID(userID) != null) {
            System.out.println("User with this ID already exists.");
            return;
        }

        User newUser = new User(userID, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newUser;
        }
    }

    // Add a friend connection between two users
    public void addFriend(int userID1, int userID2) {
        if (userID1 == userID2) {
            System.out.println("Cannot add yourself as a friend.");
            return;
        }

        User user1 = getUserByID(userID1);
        User user2 = getUserByID(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!user1.friendIDs.contains(userID2)) user1.friendIDs.add(userID2);
        if (!user2.friendIDs.contains(userID1)) user2.friendIDs.add(userID1);
    }

    // Remove a friend connection
    public void removeFriend(int userID1, int userID2) {
        User user1 = getUserByID(userID1);
        User user2 = getUserByID(userID2);

        if (user1 != null) user1.friendIDs.remove(Integer.valueOf(userID2));
        if (user2 != null) user2.friendIDs.remove(Integer.valueOf(userID1));
    }

    // Display all friends of a user
    public void displayFriends(int userID) {
        User user = getUserByID(userID);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println(user.name + "'s Friends:");
        if (user.friendIDs.isEmpty()) {
            System.out.println("No friends yet.");
            return;
        }

        for (int fid : user.friendIDs) {
            User friend = getUserByID(fid);
            if (friend != null) {
                System.out.printf("ID: %d | Name: %s | Age: %d\n", friend.userID, friend.name, friend.age);
            }
        }
    }

    // Find mutual friends
    public void findMutualFriends(int userID1, int userID2) {
        User user1 = getUserByID(userID1);
        User user2 = getUserByID(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("User(s) not found.");
            return;
        }

        HashSet<Integer> set = new HashSet<>(user1.friendIDs);
        ArrayList<Integer> mutual = new ArrayList<>();

        for (int fid : user2.friendIDs) {
            if (set.contains(fid)) mutual.add(fid);
        }

        System.out.println("Mutual Friends:");
        if (mutual.isEmpty()) {
            System.out.println("No mutual friends.");
        } else {
            for (int fid : mutual) {
                User mutualUser = getUserByID(fid);
                if (mutualUser != null) {
                    System.out.printf("ID: %d | Name: %s\n", mutualUser.userID, mutualUser.name);
                }
            }
        }
    }

    // Search by name
    public User searchByName(String name) {
        User current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) return current;
            current = current.next;
        }
        return null;
    }

    // Search by ID
    public User getUserByID(int id) {
        User current = head;
        while (current != null) {
            if (current.userID == id) return current;
            current = current.next;
        }
        return null;
    }

    // Count friends
    public void countFriends() {
        User current = head;
        while (current != null) {
            System.out.printf("%s has %d friend(s)\n", current.name, current.friendIDs.size());
            current = current.next;
        }
    }

    // Demo
    public static void main(String[] args) {
        SocialMediaNetwork network = new SocialMediaNetwork();

        network.addUser(1, "Alice", 25);
        network.addUser(2, "Bob", 27);
        network.addUser(3, "Charlie", 24);
        network.addUser(4, "Diana", 26);
        network.addUser(5, "Eve", 22);

        network.addFriend(1, 2);
        network.addFriend(1, 3);
        network.addFriend(2, 3);
        network.addFriend(2, 4);
        network.addFriend(4, 5);

        System.out.println("\n--- Friends of Alice ---");
        network.displayFriends(1);

        System.out.println("\n--- Friends of Bob ---");
        network.displayFriends(2);

        System.out.println("\n--- Mutual Friends between Alice and Bob ---");
        network.findMutualFriends(1, 2);

        System.out.println("\n--- Friend Counts ---");
        network.countFriends();

        System.out.println("\n--- Removing friendship between Bob and Charlie ---");
        network.removeFriend(2, 3);
        network.displayFriends(2);

        System.out.println("\n--- Searching for user by name 'Eve' ---");
        User u = network.searchByName("Eve");
        if (u != null) {
            System.out.printf("Found: ID: %d | Name: %s | Age: %d\n", u.userID, u.name, u.age);
        } else {
            System.out.println("User not found.");
        }
    }
}
