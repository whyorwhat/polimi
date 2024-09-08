package threads.igSwTDE.average2024;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        double[] data = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int windowSize = 4;

        try {
            // Chiamo il metodo per calcolare la media mobile in parallelo
            double[] movingAverages = MovingAverageCalculator.calculateParallelMovingAverage(data, windowSize);

            // Stampo i risultati
            System.out.println("Risultati delle medie mobili:");
            for (double avg : movingAverages) {
                System.out.println(avg);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
