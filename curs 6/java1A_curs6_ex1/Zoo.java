public final class Zoo {
    private final static int DIMENSIUNE_INITIALA = 2;
    private Animal[] animale;
    private int count;
    
    private static Zoo singleton;
    
    private Zoo() {
        animale = new Animal[DIMENSIUNE_INITIALA];        
    }
    
    public static Zoo getInstance() {
        if (singleton == null) {
            singleton = new Zoo();
        }
        return singleton;
    }
    
    // TODO: modifica semnatura acestei metode astfel incat ea sa declare aruncarea 
    //       exceptiilor checked : NumeDuplicatException si IdInexistentException
    public boolean adaugaAnimal(Animal a) {
        if (a == null) {
            // TODO: inlocuieste instructiunea de return de mai jos cu aruncarea unei exceptii
            //       NullPointerException, cu urmatorul mesaj dat constructorului : "Animalul nu poate fi null"
            return false;
        }
            
               
        for (int i = 0; i < count; i++) {
            if (animale[i].equals(a)) {
                // TODO: inlocuieste instructiunea de return de mai jos cu aruncarea unei exceptii
                //       NumeDuplicatException
                return false;
            }
        }
        
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
        
        if (index == -1) { 
            // TODO: inlocuieste instructiunea de return de mai jos cu aruncarea unei exceptii
            //       IdInexistentException
            return null;
        }
        
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