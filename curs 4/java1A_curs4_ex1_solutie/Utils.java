public final class Utils {
    private Utils() {
    }
    
    public static Animal[] copy(Animal [] org) {
        Animal[] copy = new Animal[org.length];
        int i = 0;
        while (i < org.length && org[i] != null) {
            copy[i] = org[i];
            i++;
        }
        return copy;
    }
}