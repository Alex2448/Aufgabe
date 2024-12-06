import java.util.UUID;

public abstract class Item implements Subject {
    private UUID id;
    private String title;
    private boolean isBorrowed;
    private long maxBorrowTime;

    public Item(UUID id, String title, long mxBorrowTime) {
        this.id = id;
        this.title = title;
        this.maxBorrowTime = maxBorrowTime;
        this.isBorrowed = false;
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

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }

    public long getBorrowTime() {
        return maxBorrowTime;
    }

    public void setBorrowTime(long mxBorrowTime) {
        this.maxBorrowTime = maxBorrowTime;
    }
    
    public abstract String getItemType();

}
