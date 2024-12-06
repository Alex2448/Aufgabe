import java.util.ArrayList;
import java.util.List;

public class User implements ItemObserver {

    private String name;
    private UUID id;

    public User(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();   
    }

    @Override
    public void notifyItemAvailable(Item item) {
        System.out.println("The " + item.toString() + " has been returned and is now available.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
