
/*
 * TODO: aplica Singleton Pattern acestei clase, dupa care modifica in mod 
 *       corespunzator codul din restul aplicatiei astfel incat programul sa
 *       compileze si sa ruleze cu succes in urma modificarilor facute acestei
 *       clase
 */
public final class Zoo {
    private final static int DIMENSIUNE_INITIALA = 2;
    private Animal[] animale;
    private int count;
    
    public Zoo() {
        animale = new Animal[DIMENSIUNE_INITIALA];        
    }
    
    public boolean adaugaAnimal(Animal a) {
        if (a == null) return false;
 
        // TODO: scrie mai jos o secventa de cod care sa asigure faptul ca animalul "a" dat prin parametru
        //       NU va fi adaugat in array-ul "animale" daca este EGAL cu un animal deja adaugat. (caz in care 
        //       se va returna valoarea false)
        
        if (count == animale.length) {
            Animal [] aux = new Animal[animale.length * 2];
            System.arraycopy(animale, 0, aux, 0, animale.length);
            animale = aux;
        }
        
        animale[count++] = a;
        return true;
    }
    
    public Animal stergeAnimal(int id) {
        Animal aux = null;
        int index = -1;
        
        A: for (int i = 0; i < count; i++) {
            if (animale[i].getId() == id) {
                aux = animale[i];
                index = i;
                break A;
            }
        }
        
        if (index == -1) return null;
        
        for (int i = index; i < count - 1; i++) {
            animale[i] = animale[i+1];
        }
        
        count--;
        return aux;
    }
    
    public Animal[] getAnimale() {
        return Utils.copy(animale);
    }
    
    public Animal[] findAnimaleByVarsta(int minVarsta, int maxVarsta) {
        Animal[] aux = new Animal[animale.length];
        int i = 0;
        
        for (int j = 0; j < count; j++) {
            Animal a = animale[j];
            if (a.getVarsta() >= minVarsta && a.getVarsta() <= maxVarsta) {
                aux[i++] = a;
            }
        }
        
        return aux;
    }
    
    public Animal[] findAnimaleByGreutate(int minGreutate, int maxGreutate) {
        Animal[] aux = new Animal[animale.length];
        int i = 0;
        
        for (int j = 0; j < count; j++) {
            Animal a = animale[j];
            if (a.getGreutate() >= minGreutate && a.getGreutate() <= maxGreutate) {
                aux[i++] = a;
            }
        }
        
        return aux;
    }    
    
    public Animal[] findAnimaleByNume(String nume) {
        Animal[] aux = new Animal[animale.length];
        int i = 0;
        
        for (int j = 0; j < count; j++) {
            Animal a = animale[j];
            if (a.getNume().toLowerCase().contains(nume.toLowerCase())) {
                aux[i++] = a;
            }
        }
        
        return aux;
    }
    
    public Animal[] findAnimaleTerestre() {
        
        Animal[] aux = new Animal[animale.length];
        int i = 0;
        
        for (int j = 0; j < count; j++) {
            Animal a = animale[j];
            if (a instanceof AnimalTerestru) {
                aux[i++] = a;
            }
        }
        
        return aux;
    }
     
    public Animal[] findAnimaleAcvatice() {
        
        Animal[] aux = new Animal[animale.length];
        int i = 0;
        
        for (int j = 0; j < count; j++) {
            Animal a = animale[j];
            if (a instanceof AnimalAcvatic) {
                aux[i++] = a;
            }
        }
        
        return aux;
    }
}