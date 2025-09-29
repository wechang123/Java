package weather;

public interface Subject {
    void registerObject(Observer o);
    void removeObject(Observer o);
    void notifyObservers();
}
