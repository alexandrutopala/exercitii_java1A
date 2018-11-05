package exceptii;

public class IdInexistentException extends Exception {
    public IdInexistentException() {
        super("Nu exista niciun animal in acest adapost cu id-il specificat");
    }
}