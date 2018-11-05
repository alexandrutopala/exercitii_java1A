public class AnimalAcvatic extends Animal { 
    public AnimalAcvatic(String nume, int varsta, int greutate) {
        super(nume, varsta, greutate);
    }
    
    @Override
    public String toString() {
        String animalToString = super.toString();
        return animalToString + " (acvatic)";
    }
}