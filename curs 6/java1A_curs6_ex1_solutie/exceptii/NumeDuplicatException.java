package exceptii;

public class NumeDuplicatException extends Exception {
    public NumeDuplicatException() {
        super("Un animal cu acelasi nume exista deja in adapost");
    }
}