import java.util.ArrayList;
import java.util.List;

public class User implements ItemObserver {

    private LibraryManagementSystem libraryManagementSystem;
    private String name;
    private List<Item> borrowedItems;

    public User(String name) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }
    // private List<Item> borrowedItems;

//    //Users should be able to search for a borrowable item by its title or type (DVD or book).
//    private Item search(String title) {
//        return libraryManagementSystem.search(title);
//    }
//    private Item search(Item type) {
//        return libraryManagementSystem.search(type);
//    }

    //Users should be able to borrow an item for a certain period. If the item is already borrowed, users should be able to join a waitlist.
//    private Item borrow(Item item) {
//        return libraryManagementSystem.borrowItem(this, item);
//    }

    public void borrowItem(Item item) {
        borrowedItems.add(item);
    }

    public void returnItem(Item item) {
        borrowedItems.remove(item);
    }

    @Override
    public void update(Item item) {
        System.out.println("The " + item.toString() + " has been returned and is now available.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
