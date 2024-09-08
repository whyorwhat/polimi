package threads.igSwTDE.average2024;

import java.util.ArrayList;
import java.util.List;

public class MovingAverageCalculator {

    public static double[] calculateParallelMovingAverage(double[] data, int windowSize) throws InterruptedException {
        if(windowSize >= data.length){
            windowSize = data.length;
        }

        Object lock = new Object();
        double[] result = new double[data.length - windowSize + 1]; // Length of return array

        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            final int startIndex = i;
            final int endIndex = startIndex + windowSize;
            if(endIndex <= data.length){
                int finalWindowSize = windowSize;
                Thread thread = new Thread(() ->{
                    synchronized(lock){
                        double average = 0;
                        for(int j = startIndex; j < endIndex; j++){
                            average += data[j];
                        }
                        average /= finalWindowSize;
                        result[startIndex] = average;
                    }
                });
                thread.start();
                threads.add(thread);
            }
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return result;
    }
}
