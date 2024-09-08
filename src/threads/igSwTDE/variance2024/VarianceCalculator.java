package threads.igSwTDE.variance2024;

import java.util.ArrayList;
import java.util.List;

public class VarianceCalculator {

    public static float calculateParallelVariance(List<Integer> data, int subIntervals) throws InterruptedException {
        final int segmentSize = data.size() / subIntervals;
        Object lock = new Object();

        List<Thread> threads = new ArrayList<>(subIntervals);
        List<List<Integer>> segments = new ArrayList<>(subIntervals);

        // *** 1. Calculate average of list ***
        List<Float> averages = new ArrayList<>();
        for(int i = 0; i < subIntervals; i++){
            List<Integer> subInterval = new ArrayList<>(segmentSize);
            final int start = i * segmentSize;    //With subIntervals = 5: 0, 5, 10, 15, 20
            final int end;
            if (i == subIntervals - 1){     //If it is the last segment
                end = data.size();  //If segmentSize returns a result with some reminder, fix the last segment with remaining values
            }else{
                end = start + segmentSize;  //With data.size() = 20 and subIntervals = 5: 0+4=4, 5+4=9, 10+4=14, 15+4=19
            }

            threads.add(new Thread(() -> {
                for(int j = start; j < end; j++){
                    subInterval.add(data.get(j));
                }
                synchronized(lock){
                    // Lock segments list to add the subInterval and calculate the average of the subInterval
                    segments.add(subInterval);
                    float average = 0f;
                    for(Integer element: subInterval){
                        average += element;
                    }
                    average /= subInterval.size();  // average = average / segmentSize BUT for last segment segmentSize = subInterval.size()
                    averages.add(average);
                }
            }));
            threads.get(i).start();
        }

        for(Thread thread: threads){
            thread.join();
        }

        float globalSum = 0;
        for (Float average: averages){
            globalSum += average;
        }
        final float globalAverage = globalSum / data.size();

        // *** 2. Calculate variance ***
        // We can use the lists generated previously
        threads.clear();

        final float[] varianceSum = {0f};
        for(List<Integer> segment: segments){
            threads.add(new Thread(() -> {
                float localVarianceSum = 0f;

                for (Integer value : segment) {
                    float diff = value - globalAverage;
                    localVarianceSum += diff * diff;
                }

                synchronized (lock) {
                    varianceSum[0] += localVarianceSum;
                }
            }));
            threads.get(threads.size() - 1).start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return varianceSum[0] / data.size();
    }
}
