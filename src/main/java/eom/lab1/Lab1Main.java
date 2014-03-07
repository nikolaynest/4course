package eom.lab1;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by nikolay on 3/6/14.
 */
public class Lab1Main {
    private static final File DATA_FILE = new File("/home/nikolay/Documents/4COURSE-2/EOM/12.TXT");


    public static void main(String[] args) {
        ReadValuesFromFile reads = new ReadValuesFromFile(DATA_FILE);
        ArrayList<Double> doubles = reads.getDoublesAfterSort();

        UseValues useValues = new UseValues(doubles);
        useValues.printDoubles();
        useValues.printValues();
        double hGap = ReadValuesFromFile.roundResult(useValues.getH(), 4);
        int numIntervals = useValues.getIntervals();

        Intervals i = new Intervals(doubles,hGap,numIntervals);
        i.printInterval(i.getIntervals(), 0);

    }
}
