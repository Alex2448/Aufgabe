import java.util.LinkedList;
import java.util.UUID;

public abstract class Item implements Subject {
    private final UUID id;
    private final String title;
    private final long maxBorrowTime;
    private final LinkedList<ItemObserver> observers;
    private boolean isBorrowed;

    public Item(UUID id, String title, long maxBorrowTime) {
        this.id = id;
        this.title = title;
        this.maxBorrowTime = maxBorrowTime;
        this.isBorrowed = false;
        this.observers = new LinkedList<>();
    }

    public abstract String getItemType();

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    public long getBorrowTime() {
        return maxBorrowTime;
    }

    public LinkedList<ItemObserver> getObservers() {
        return observers;
    }

    @Override
    public void registerObserver(ItemObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ItemObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyNextObserver() {
        ItemObserver observer = observers.poll();
        if (observer != null) {
            observer.notifyItemAvailable(this);
        }
    }
}
