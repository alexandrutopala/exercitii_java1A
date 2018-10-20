public final class Zoo {
    private final static int DIMENSIUNE_INITIALA = 2;
    private Animal[] animale;
    private int count;
    
    public Zoo() {
        animale = new Animal[DIMENSIUNE_INITIALA];        
    }
    
    public boolean adaugaAnimal(Animal a) {
        if (a == null) return false;
        
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
    
    // TODO: creeaza metoda "getAnimale" care sa returneze o copie a array-ului 
    //       "animale"
    // HINT: pentru obtinerea unei copii, foloseste o metoda potrivita a clasei "Utils"
    public Animal[] getAnimale() {
        return Utils.copy(animale);
    }
    
    // TODO: creeaza metoda "findAnimaleByVarsta(int minVarsta, int maxVarsta)" care sa returneze un nou array de "Animal" 
    //       care sa contina numai animalele din array-ul "animale" cu varsta cuprinsa intre "minVarsta" si "maxVarsta", 
    //       inclusiv ambele capete
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
    
    // TODO: creeaza metoda "findAnimaleByGreutate(int minGreutate, int maxGreutate)" care sa returneze un nou array de "Animal" 
    //       care sa contina numai animalele din array-ul "animale" cu greutatea cuprinsa intre "minGreutate" si "maxGreutate", 
    //       inclusiv ambele capete
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
    
    // TODO: creeaza metoda "findAnimaleTerestre()" care sa returneze un nou array de "Animal" 
    //       care sa contina numai instantele de AnimalTerestru din array-ul "animale" 
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
    
    // TODO: creeaza metoda "findAnimaleAcvatice()" care sa returneze un nou array de "Animal" 
    //       care sa contina numai instantele de AnimalAcvatic din array-ul "animale" 
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