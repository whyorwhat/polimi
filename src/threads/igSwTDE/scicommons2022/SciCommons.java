package threads.igSwTDE.scicommons2022;

import java.util.Random;

public class SciCommons {

    public static Integer[] sample(Integer[][] matrix) throws InterruptedException{
        int matrixLength = matrix.length;

        // Check if matrix is square
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i].length != matrixLength){
                throw new IllegalArgumentException("Matrix is not square");
            }
        }

        // Initialize variables
        int returnLength = matrixLength * 2 + 2;
        Integer[] returnList = new Integer[returnLength];   //returnList structure: columns (left->right), rows (top->bottom), diagonals (1. left-right, 2. right-left)
        Thread[] threads = new Thread[returnLength];    //Global index for the return list
        Random random = new Random();   //For random indexes

        //*** Columns ***
        for(int i=0; i < matrixLength; i++){
            final int columnIndex = i;  //final to ensure the variable will not be modified after initialization (is not mandatory but ensures a correct usage of the variable)
            threads[columnIndex] = new Thread(new Runnable() {
                @Override
                public void run(){
                    int randomIndex = random.nextInt(matrixLength);
                    returnList[columnIndex] = matrix[randomIndex][columnIndex];
                }
            });
//          SI POTREBBE SCRIVERE COME:
//            threads[columnIndex] = new Thread(() -> {
//                int randomIndex = random.nextInt(matrixLength);
//                returnList[columnIndex] = matrix[randomIndex][columnIndex];
//            });
            threads[columnIndex].start();
        }

        //*** Rows ***
        for(int i=0; i < matrixLength; i++){
            final int rowIndex = i;
            threads[rowIndex + matrixLength] = new Thread(() -> {
                int randomIndex = random.nextInt(matrixLength);
                returnList[rowIndex + matrixLength] = matrix[rowIndex][randomIndex];
            });
            threads[rowIndex + matrixLength].start();
        }

        //*** Left-right diagonal **
        threads[matrixLength * 2] = new Thread(() -> {
           int randomIndex = random.nextInt(matrixLength);
           returnList[matrixLength * 2] = matrix[randomIndex][randomIndex];
       });
        threads[matrixLength * 2].start();

        //*** Right-left diagonal **
        threads[matrixLength * 2 + 1] = new Thread(() -> {
            int randomIndex = random.nextInt(matrixLength);
            returnList[matrixLength * 2 + 1] = matrix[randomIndex][matrixLength - 1 - randomIndex];
        });
        threads[matrixLength * 2 + 1].start();

        for(Thread thread : threads){
            thread.join();
        }

        return returnList;
    }
}
