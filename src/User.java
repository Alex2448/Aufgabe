import java.util.UUID;

public class User implements ItemObserver {

    private String name;
    private final UUID id;

    public User(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }
}
