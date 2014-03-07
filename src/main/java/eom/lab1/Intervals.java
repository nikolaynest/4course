package eom.lab1;

import java.util.ArrayList;

/**
 * Created by nikolay on 3/7/14.
 */
public class Intervals {

    private double[][] intervals;
    private ArrayList<Double> doubles;
    private double hGap;
    private int numIntervals;

    public Intervals(ArrayList<Double> doubles, double hGap, int numIntervals) {
        this.doubles = doubles;
        this.hGap = hGap;
        this.numIntervals = numIntervals;
    }

    public double[][] getIntervals() {
        intervals = new double[numIntervals][];
        int ind = 0;
        double borderValue;
        for (int j = 0; j < intervals.length; j++) {
            borderValue = doubles.get(ind) + hGap;
            int k = 0;
            for (int i = ind; i < doubles.size(); i++) {
                if (borderValue <= doubles.get(i)) {
                    intervals[j][k++] = doubles.get(i);
                } else {
                    ind = i;
                    break;
                }
            }
        }
        return intervals;
    }

    public void printInterval(double[][] intervals, int ind) {
        System.out.println("INTERVAL #" + ind);
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(intervals[ind][ind]);
        }
    }


}
