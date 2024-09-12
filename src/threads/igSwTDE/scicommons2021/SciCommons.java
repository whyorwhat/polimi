package threads.igSwTDE.scicommons2021;

import java.util.Arrays;

public class SciCommons {


    public static int minOf(int[] l, int s) throws InterruptedException {
        Thread[] threads = new Thread[s]; // num Threads
        int arrayDimension = l.length;
        int numElement = (arrayDimension + s - 1) / s;
        int[] arrayMin = new int[s];

        for (int i = 0; i < s; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                int start = index * numElement;
                int end = Math.min(start + numElement, arrayDimension);

                int localMin = Integer.MAX_VALUE;
                for (int j = start; j < end; j++) {
                    if (l[j] < localMin) {
                        localMin = l[j];
                    }
                }
                arrayMin[index] = localMin;
                System.out.println("Minimo locale: " + localMin);

            });

            threads[i].start();
        }


        for (Thread t : threads) {
            t.join();
        }
        System.out.println(Arrays.toString(arrayMin));

        int minToReturn = Integer.MAX_VALUE;
        for (int min : arrayMin) {
            if (min < minToReturn) {
                minToReturn = min;
            }
        }

        return minToReturn;
    }
}
