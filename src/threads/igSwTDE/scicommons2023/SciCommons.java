package threads.igSwTDE.scicommons2023;

import java.util.ArrayList;
import java.util.List;

public class SciCommons {

    public static List<List<Boolean>> isGreater(List<List<Integer>> A, List<List<Integer>> B) throws InterruptedException {
        final int matrixSize = A.size();
        List<List<Boolean>> returnMatrix = new ArrayList<>(matrixSize); //Create return matrix with matrixSize size
        // Populate the list with null values that will be overwritten from the threads (additional control for errors)
        for (int i = 0; i < matrixSize; i++) {
            returnMatrix.add(null);
        }

        List<Thread> threads = new ArrayList<>();   //Create list of threads

        // *** Define an order for acquisition lock order ***
        // The scope is to set a DEFAULT ORDER: if another static will be added and the same two lists are passed in opposite order (isGreater(A, B) and isLower(B, A)) there could be a deadlock
        final List<List<Integer>> first;
        final List<List<Integer>> second;
        // Note: every Object can be a locker

        if (System.identityHashCode(A) < System.identityHashCode(B)) {
            first = A;
            second = B;
        } else {
            first = B;
            second = A;
        }

        for (int i = 0; i < A.size(); i++){     //Scroll all matrix horizontally
            final int colNumber = i;

            threads.add(new Thread(() ->{

                List<Boolean> returnColumn = new ArrayList<>(matrixSize);   // Create column and insert into boolean values
                boolean returnValue;

                synchronized (first) {
                    synchronized (second) {

                        for (int j = 0; j < B.size(); j++){ //Scroll column vertically

                            //Check which element is greater
                            if(A.get(colNumber).get(j) > B.get(colNumber).get(j)){
                                returnValue = true;
                            }else{
                                returnValue = false;
                            }

                            returnColumn.add(j, returnValue);
                        }
                    }
                }

                synchronized (returnMatrix) {
                    // Replace null values with the new column. The column will be placed in a specific position
                    returnMatrix.set(colNumber, returnColumn);
                }

            }));
            threads.get(i).start();
        }

        for (Thread thread : threads){
            thread.join();
        }
        return returnMatrix;
    }
}
