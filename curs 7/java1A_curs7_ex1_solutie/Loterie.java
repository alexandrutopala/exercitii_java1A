import java.util.*;

public class Loterie {
    private static Loterie singleton;
    
    private int[] numereLoterie;
    private Random random = new Random();
    
    private Loterie() {
    }
    
    public static Loterie getInstance() {
        if (singleton == null) {
            singleton = new Loterie();
        }
        return singleton;
    }
   
    public int joaca(int [] numereUser) {
        genereazaNumereAleatoare();
        
        int punctaj = 0;
        
        for (int i = 0; i < numereLoterie.length; i++) {
            int dif = Math.abs(numereUser[i] - numereLoterie[i]);
            
            switch (dif) {
                case 0 : punctaj += 100; break;
                case 1 : punctaj += 50; break;
                case 2 :
                case 3 :
                case 4 : punctaj += 25; break;
            }
        }
        
        return punctaj;
    }
    
    private void genereazaNumereAleatoare() {
        numereLoterie = new int[6];
        
        for (int i = 0; i < numereLoterie.length; i++) {
            numereLoterie[i] = random.nextInt(50) + 1;
        }
    }
    
    public void afiseaza() {
        for (int x : numereLoterie) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
    
    
}