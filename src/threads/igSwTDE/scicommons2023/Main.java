package threads.igSwTDE.scicommons2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Creazione delle matrici A e B
            List<List<Integer>> A = new ArrayList<>();
            List<List<Integer>> B = new ArrayList<>();

            // Popolazione della matrice A
            A.add(Arrays.asList(5, 3, 9));
            A.add(Arrays.asList(2, 8, 7));
            A.add(Arrays.asList(6, 4, 1));

            // Popolazione della matrice B
            B.add(Arrays.asList(3, 4, 9));
            B.add(Arrays.asList(1, 7, 6));
            B.add(Arrays.asList(6, 5, 2));

            // Chiamata al metodo isGreater per testare A > B
            List<List<Boolean>> resultGreater = SciCommons.isGreater(A, B);

            // Stampa del risultato del confronto A > B
            System.out.println("Risultato del confronto tra A e B (A > B):");
            for (List<Boolean> row : resultGreater) {
                System.out.println(row);
            }

            // Chiamata al metodo isGreater per testare B > A
            List<List<Boolean>> resultLower = SciCommons.isGreater(B, A);

            // Stampa del risultato del confronto B > A
            System.out.println("Risultato del confronto tra B e A (B > A):");
            for (List<Boolean> row : resultLower) {
                System.out.println(row);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
