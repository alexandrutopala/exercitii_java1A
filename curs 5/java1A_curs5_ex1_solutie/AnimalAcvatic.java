public class AnimalAcvatic extends Animal { 
    public AnimalAcvatic(String nume, int varsta, int greutate) {
        super(nume, varsta, greutate);
    }
    
    // TODO: Suprascrie metoda "toString" astfel incat reprezentarea sub forma de 
    //       sir de caractere a unei instante de "AnimalAcvatic" sa cuprinda toate 
    //       atributele clasei si informatia ca este un animal acvatic
    public String toString() {
        String animalToString = super.toString();
        return animalToString + " (acvatic)";
    }
}