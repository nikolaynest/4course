package gosy;

/**
 * Created by nikolay on 17.06.14.
 */
public class Algorithm {

    private int n;
    private int m;
    private double[][] array;

    public Algorithm(double[][] array) {
        this.array = array;
        n = array.length;
        m = array[0].length;
    }

    public double sumOfColumnWithZeros() {
        double sum = 0.0;
        for (int column = 0; column < m; column++) {
            for (int row = 0; row < n; row++) {
                if (array[row][column] == 0.0) {
                    for (int k = 0; k < n; k++) {
                        sum += array[k][column];
                    }
                    break;
                }
            }
        }
        return sum;
    }

}
