/*
 *  TODO: incapsuleaza clasa Animal 
 */
public class Animal {
    String nume;
    int varsta;
    int greutate;
    final int id;
    
    public Animal(String nume, int varsta, int greutate) {
        this.nume = nume;
        this.varsta = varsta;
        this.greutate = greutate;
        // TODO: atribuie variabilei de instanta "id" o valoare unica pentru fiecare instanta
    }    
    
    @Override
    public String toString() {
        return "nume=" + nume + ", varsta=" + varsta + ", greuate=" + greutate; 
    }
    
}