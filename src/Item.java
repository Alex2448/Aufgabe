import java.util.UUID;

public abstract class Item implements Subject {
    private UUID id;
    private String title;
    private boolean isBorrowed;
    private long maxBorrowTime;
    private Queue<Observer> observers;

    public Item(UUID id, String title, long mxBorrowTime) {
        this.id = id;
        this.title = title;
        this.maxBorrowTime = maxBorrowTime;
        this.isBorrowed = false;
        this.observers = new Queue<>();
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
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public long getBorrowTime() {
        return maxBorrowTime;
    }

    public void setBorrowTime(long mxBorrowTime) {
        this.maxBorrowTime = maxBorrowTime;
    }
    
    public abstract String getItemType();

     public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notifyItemAvailable(this);
        }
    }

}
