public interface Subject {
    void registerObserver(ItemObserver observer, Item item);
    void removeObserver(ItemObserver observer, Item item);
    void checkAndNotifyNextObserver(Item item);
}
