import java.util.*;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        String [] input;
        Loterie loterie = Loterie.getInstance();
        
        while (true) {
            try {
                line = scanner.nextLine();
                input = line.split("\\s+");
                
                switch (input[0].toUpperCase()) {
                    case "JOACA" : joaca(input); break;
                    case "EXIT" : System.exit(0); break;
                    default : System.err.println("Comanda invalida");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private static void joaca(String [] input) {
        int n1 = Integer.parseInt(input[1]);
        int n2 = Integer.parseInt(input[2]);
        int n3 = Integer.parseInt(input[3]);
        int n4 = Integer.parseInt(input[4]);
        int n5 = Integer.parseInt(input[5]);
        int n6 = Integer.parseInt(input[6]);
        
        int[] numereUser = {n1, n2, n3, n4, n5, n6};
        
        int punctaj = Loterie.getInstance().joaca(numereUser);
        System.out.println("Ai castigat: " + punctaj);
        Loterie.getInstance().afiseaza();
    }
}