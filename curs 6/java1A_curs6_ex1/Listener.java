public interface Listener {
    public void notifyList();
    public default void notifyWindowClosed() {}
}