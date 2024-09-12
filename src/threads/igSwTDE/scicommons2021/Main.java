package threads.igSwTDE.scicommons2021;

import static threads.igSwTDE.scicommons2021.SciCommons.minOf;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] array = { 1, 4, 5, 6, 33, 2, 6, 7, 84, 112, 4322 };

        System.out.println("Minimo: " + minOf(array, 3));
    }
}
