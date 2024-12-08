public interface Subject {
    void registerObserver(ItemObserver observer);

    void removeObserver(ItemObserver observer);

    void notifyNextObserver();
}
