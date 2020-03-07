public interface Subject {
    boolean addObserver(User user);
    void removeObserver(User user);
    void notifyAllObservers(Notification notification);
}
