import java.util.ArrayList;
import java.util.List;

public class User implements ItemObserver {

    private LibraryManagementSystem libraryManagementSystem;
    private String name;
    private List<Item> borrowedItems;
    private UUID id;

    public User(String name) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        this.uuid = UUID.randomUUID();   
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
