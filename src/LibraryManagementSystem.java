import java.util.*;
import java.util.stream.Collectors;

public class LibraryManagementSystem implements Subject {
    private final List<Item> borrowableItems;
    private final Map<Item, Queue<ItemObserver>> waitingMap;
    private final Map<Item,User> borrowedItems;
    private final Map<User,Item> borrowingUsers;

    public LibraryManagementSystem() {
        this.borrowableItems = new ArrayList<>();
        this.waitingMap = new HashMap<>();
        this.borrowedItems = new HashMap<>();
        this.borrowingUsers = new HashMap<>();
    }

    public List<Item> search(String searchText) {
        return borrowableItems.stream()
                .filter(item -> searchText.equalsIgnoreCase(item.getTitle()) || Objects.equals(searchText, item.getItemType()))
                .collect(Collectors.toList());
    }

    public void borrowItem (User user, Item item) {
        if (!search(item.getTitle()).isEmpty() || waitingMap.containsKey(item)) {
            borrowableItems.remove(item);
            item.setBorrowed(true);
            borrowedItems.put(item,user);
            borrowingUsers.put(user,item);
            user.borrowItem(item);
            removeObserver(user, item);
        } else {
            if (!(borrowingUsers.containsKey(user) && borrowedItems.containsKey(item))) {
                registerObserver(user, item);
            }
        }
    }

    public void returnItem (Item item) {
        if (borrowedItems.containsKey(item)) {
            item.setBorrowed(false);
            User user = borrowedItems.get(item);
            borrowedItems.remove(item,user);
            borrowingUsers.remove(user, item);
            user.returnItem(item);
            checkAndNotifyNextObserver(item);
        }
    }

    @Override
    public void registerObserver(ItemObserver observer, Item item) {
        Queue<ItemObserver> waitingObserver = waitingMap.get(item);
        if (waitingObserver == null) {
            waitingObserver = new LinkedList<>();
            waitingObserver.add(observer);
            waitingMap.put(item, waitingObserver);
        } else {
            waitingObserver.add(observer);
            waitingMap.replace(item, waitingObserver);
        }
    }

    @Override
    public void removeObserver(ItemObserver observer, Item item) {
        Queue<ItemObserver> waitingObserver = waitingMap.get(item);
        if (waitingObserver != null) {
            waitingObserver.remove(observer);
            if (waitingObserver.isEmpty()) {
                waitingMap.remove(item);
            } else {
                waitingMap.replace(item, waitingObserver);
            }
        }
    }

    @Override
    public void checkAndNotifyNextObserver(Item item) {
        Queue<ItemObserver> waitingObserver = waitingMap.get(item);
        if (waitingObserver != null) {
            ItemObserver nextUser = waitingObserver.peek();
            if (nextUser != null) {
                nextUser.update(item);
            }
        } else {
            borrowableItems.add(item);
        }
        ; //TODO: nur Menschen, die genau das Item abonniert haben sollen notified werden
//        for (ItemObserver observer : waitingList) {
//            observer.update(item);
//        }
    }

    public List<Item> getBorrowableItems() {
        return borrowableItems;
    }

    public void addBorrowableItem(Item item) {
        borrowableItems.add(item);
    }

    public Map<Item, Queue<ItemObserver>> getWaitingList() {
        return waitingMap;
    }

    public Map<Item, User> getBorrowedItems() {
        return borrowedItems;
    }

    public Map<User, Item> getBorrowingUsers() {
        return borrowingUsers;
    }
}
