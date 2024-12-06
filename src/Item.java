import java.util.UUID;

public abstract class Item {
    private UUID id;
    private String title;
    private boolean borrowed;
    private long borrowTime;

    public Item(UUID id, String title, long borrowTime) {
        this.id = id;
        this.title = title;
        this.borrowTime = borrowTime;
        this.borrowed = false;
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
        return borrowTime;
    }

    public void setBorrowTime(long borrowTime) {
        this.borrowTime = borrowTime;
    }
    public abstract String getItemType();

}
