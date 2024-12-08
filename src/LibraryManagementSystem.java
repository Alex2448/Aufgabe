import java.util.*;
import java.util.stream.Collectors;

public class LibraryManagementSystem {
    private final Map<UUID,User> users;
    private final Map<UUID,Item> items;
    private final Map<Item,User> borrowedItems;
    private final Map<User,List<Item>> borrowingUsers;

    public LibraryManagementSystem() {
        this.users = new HashMap<>();
        this.items = new HashMap<>();
        this.borrowedItems = new HashMap<>();
        this.borrowingUsers = new HashMap<>();
    }

    public List<Item> search (String searchText) {
        return items.values().stream()
                .filter(item -> searchText.equalsIgnoreCase(item.getTitle()) ||
                        Objects.equals(searchText.toLowerCase(), item.getItemType().toLowerCase()))
                .collect(Collectors.toList());
    }

    public void lendItem (User user, Item item) {
        // users should be able to only borrow items that are registered in the library
        if (!items.containsKey((item.getId()))) {
            return;
        }

        // user should not be able to borrow the same item again if he is currently borrowing it
        if (borrowedItems.get(item) == user) {
            return;
        }

        if (itemCanBeBorrowedByUser(item, user)) {
            item.setBorrowed(true);
            borrowedItems.put(item,user);
            if (!borrowingUsers.containsKey(user)) {
                borrowingUsers.put(user, new ArrayList<>());
            }
            borrowingUsers.get(user).add(item);
            item.removeObserver(user);
        } else {
            item.registerObserver(user);
        }
    }

    public void returnItem (Item item) {
        if (borrowedItems.containsKey(item)) {
            item.setBorrowed(false);
            User user = borrowedItems.get(item);
            borrowedItems.remove(item,user);
            borrowingUsers.get(user).remove(item);
            item.notifyNextObserver();
        }
    }

    public void unsubscribeFromWaitingList(User user, Item item) {
        item.removeObserver(user);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.values());
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public void removeItem(Item item) {
        items.remove(item.getId());
    }

    public Map<Item, User> getBorrowedItems() {
        return borrowedItems;
    }

    public Map<User, List<Item>> getBorrowingUsers() {
        return borrowingUsers;
    }

    private boolean itemCanBeBorrowedByUser(Item item, User user) {
        return !item.isBorrowed() &&
                (item.getObservers().isEmpty() || item.getObservers().getFirst().equals(user));
    }
}
