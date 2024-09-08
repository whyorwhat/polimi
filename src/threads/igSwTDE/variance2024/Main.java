package threads.igSwTDE.variance2024;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60);

        double result = VarianceCalculator.calculateParallelVariance(list, 3);

        System.out.println("The calculated variance is: " + result);
    }
}
