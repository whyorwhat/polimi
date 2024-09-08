package threads.igSwTDE.variance2024;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            data.add(i);
        }

        int subIntervals = 4;  // Numero di sottointervalli/thread
        float variance = VarianceCalculator.calculateParallelVariance(data, subIntervals);
        System.out.println("Varianza: " + variance);
    }
}
