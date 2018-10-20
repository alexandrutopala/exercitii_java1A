public class AnimalTerestru extends Animal {
    private String regiune;
    
    public AnimalTerestru(String nume, int varsta, int greutate, String regiune) {
        super(nume, varsta, greutate);
        this.regiune = regiune;
    }
    
    public String getRegiune() {
        return regiune;
    }
    
    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }
    
    // TODO: Suprascrie metoda "toString" astfel incat reprezentarea sub forma de 
    //       sir de caractere a unei instante de "AnimalTerestru" sa cuprinda toate 
    //       atributele clasei si informatia ca este un animal terestru
}