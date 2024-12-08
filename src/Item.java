import java.util.LinkedList;
import java.util.UUID;

public abstract class Item implements Subject {
    private UUID id;
    private String title;
    private boolean isBorrowed;
    private long maxBorrowTime;
    private final LinkedList<ItemObserver> observers;

    public Item(UUID id, String title, long maxBorrowTime) {
        this.id = id;
        this.title = title;
        this.maxBorrowTime = maxBorrowTime;
        this.isBorrowed = false;
        this.observers = new LinkedList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setBorrowTime(long maxBorrowTime) {
        this.maxBorrowTime = maxBorrowTime;
    }
    
    public abstract String getItemType();

    //TODO: soll das in ItemObserver Methode sein??
    public LinkedList<ItemObserver> getObservers() {
        return observers;
    }

    //TODO: soll das in ItemObserver Methode sein??
    public ItemObserver getNextObserver() {
        return observers.getFirst();
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
