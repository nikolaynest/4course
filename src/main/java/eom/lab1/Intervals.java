package eom.lab1;

import java.lang.reflect.Array;
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

    /**
     * min x = -0.334
     * max x = 0.348
     * m intervals = 15
     * gap h = 0.0455
     * val = -0.2885
     * TODO: НЕ РАБОТАЕТ ЛОГИКА
     */
    public ArrayList<ArrayList<Double>> inter() {
        double val = doubles.get(0) + hGap;
        ArrayList<Double> list ;
        ArrayList<ArrayList<Double>> dList = new ArrayList<>();
        int ind = 0;
        for (int j = 0; j < 15; j++) {
            list = new ArrayList<>();
            for (int i = ind; i < doubles.size(); i++) {
                if (doubles.get(i) < 0) {
                    if (val < doubles.get(i)) {
                        list.add(doubles.get(i));
                    }else {
                        val = doubles.get(i)+hGap;
                        ind = i;
                        continue;
                    }
                } else {
                    if (val >= doubles.get(i)){
                        list.add(doubles.get(i));
                    } else {
                        val = doubles.get(i)+hGap;
                        ind = i;
                        continue;
                    }
                }
            }
            dList.add(list);
        }
        return dList;
    }


    public void printList(ArrayList<ArrayList<Double>> list){
        int ind = 0;
        for (ArrayList<Double> l:list){
            System.out.println("list #"+ind++);
            for (Double d: l){
                System.out.println(d);
            }
        }
    }

    public double[][] getIntervals() {
        int[] n = {623, 2217, 1444, 1057, 936, 903, 789, 289, 835, 868, 884, 1067, 1314, 2128, 1072};
        intervals = new double[numIntervals][];
        for (int i = 0; i < intervals.length; i++){
            intervals[i] = new double[n[i]];
        }
        int k = 0;
        for (int i = 0; i < intervals.length; i++){
            for(int j = 0; j < intervals[i].length; j++){
                intervals[i][j] = doubles.get(k++);
            }
        }
        return intervals;
    }

    public void printIntervals(double[][] intervals) {
        System.out.println("INTERVALS:");
        for (int i = 0; i< intervals.length; i++) {
            for (int j = 0; j < intervals[i].length; j++){
                System.out.println(intervals[i][j]);
            }
        }
    }


}
