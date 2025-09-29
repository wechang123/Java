package teacher;

public interface Teacher {
    void subscribeObserver(Student member);
    void unsubscribeObserver(Student member);
    void notifyObservers(String message);
}
