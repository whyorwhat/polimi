package threads.igSwTDE.scicommons2022;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Integer[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Integer[] result = SciCommons.sample(matrix);

        for (Integer num : result) {
            System.out.print(num + " ");
        }
    }
}
