import java.util.*;
import java.util.stream.Collectors;

public class LibraryManagementSystem {
    private final Map<UUID,Item> items;
    private final Map<Item,User> borrowedItems;
    private final Map<User,Item> borrowingUsers;

    public LibraryManagementSystem() {
        this.items = new HashMap<>();
        this.borrowedItems = new HashMap<>();
        this.borrowingUsers = new HashMap<>();
    }

    //TODO
    public List<Item> search (String searchText) {
        return items.values().stream()
                .filter(item -> searchText.equalsIgnoreCase(item.getTitle()) || Objects.equals(searchText, item.getItemType()))
                .collect(Collectors.toList());
    }

    public void lendItem (User user, Item item) {
        if (!item.isBorrowed() && items.containsKey(item.getId()) &&
           (item.getObservers().isEmpty() || item.getNextObserver().equals(user))) {
            item.setBorrowed(true);
            borrowedItems.put(item,user);
            borrowingUsers.put(user,item);
            // user.lendItem(item); //todo wue früher???
            item.removeObserver(user);
        } else {
            if (!(borrowingUsers.containsKey(user) && borrowedItems.containsKey(item))) {
                item.registerObserver(user);
            }
        }
    }

    public void returnItem (Item item) {
        if (borrowedItems.containsKey(item)) {
            item.setBorrowed(false);
            User user = borrowedItems.get(item);
            borrowedItems.remove(item,user);
            borrowingUsers.remove(user, item);
            // user.returnItem(item); // todo wie früher??
            item.notifyNextObserver();
        }
    }

    // Todo tests
    public void unsubscribeFromWaitingList(User user, Item item) {
        item.removeObserver(user);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.values());
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

    public Map<User, Item> getBorrowingUsers() {
        return borrowingUsers;
    }
}
