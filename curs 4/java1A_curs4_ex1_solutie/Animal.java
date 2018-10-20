/*
 *  TODO: incapsuleaza clasa Animal 
 */
public class Animal {
    private static int idGenerator = 0;
    private String nume;
    private int varsta;
    private int greutate;
    private final int id;
    
    public Animal(String nume, int varsta, int greutate) {
        this.nume = nume;
        this.varsta = varsta;
        this.greutate = greutate;
        // TODO: atribuie variabilei de instanta "id" o valoare unica pentru fiecare instanta
        id = idGenerator++;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNume() {
        return nume;
    }
    
    public void setNume(String nume) {
        this.nume = nume;
    }
    
    public int getVarsta() {
        return varsta;
    }
    
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
    
    public int getGreutate() {
        return greutate;
    }
    
    public void setGreutate(int greutate) {
        this.greutate = greutate;
    }
    
    @Override
    public String toString() {
        return "nume=" + nume + ", varsta=" + varsta + ", greuate=" + greutate; 
    }
    
}