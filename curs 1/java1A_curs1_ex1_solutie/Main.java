import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args) throws IOException {
        
        System.out.print(
            "Introdu una dintre urmatoarele comenzi: \n" + 
            "par <numar>\n" + 
            "prim <numar>\n" +
            "suma_cifre <numar>\n" + 
            "numar_cifre <numar>\n" +
            "numar_cifre_pare <numar>\n" + 
            "exit\n"
        );
        
        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(isr)) {
            
            String line;
            String [] input;
                 
            while (true) {
                line = br.readLine();
                input = line.split("\\s+");
                
                try {
                    switch (input[0].toUpperCase()) {
                        case "PAR" : 
                            int x1 = Integer.parseInt(input[1]);
                            System.out.println(Utils.isPar(x1));
                            break;
                        case "PRIM" : 
                            int x2 = Integer.parseInt(input[1]);
                            System.out.println(Utils.isPrim(x2));
                            break;
                        case "NUMAR_CIFRE" : 
                            int x3 = Integer.parseInt(input[1]);
                            System.out.println(Utils.getNumarCifre(x3));
                            break;
                        case "NUMAR_CIFRE_PARE" : 
                            int x4 = Integer.parseInt(input[1]);
                            System.out.println(Utils.getNumarCifrePare(x4));
                            break;
                        case "SUMA_CIFRE" : 
                            int x5 = Integer.parseInt(input[1]);
                            System.out.println(Utils.getSumaCifre(x5));
                            break;
                        case "EXIT" : 
                            System.exit(0);
                            break;
                        default :
                            System.err.println("Comanda invalida : " + input[0]);
                    }
                    
                } catch (Exception e) {
                    System.err.println(e);
                }
            }           
            
        } 
    }
}