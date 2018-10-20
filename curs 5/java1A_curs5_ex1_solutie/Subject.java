import java.util.*;

public class Subject {
    private List<Listener> listeners = new ArrayList<>();
    
    public void addListener(Listener l) {
        listeners.add(l);
    }
    
    public void removeListener(Listener l) {
        listeners.remove(l);
    }
    
    public void notifyListeners() {
        listeners.forEach(Listener::notifyList);
    }
    
    public void notifyWindowClosed() {
        listeners.forEach(Listener::notifyWindowClosed);
    }
}