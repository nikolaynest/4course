package eom.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by nikolay on 3/6/14.
 */
public class UseValues {

    private ArrayList<Double> doubles;
    private int N;

    public UseValues(ArrayList<Double> doubles) {
        this.doubles = doubles;
        N = doubles.size();
    }

    public Double getXPointAverage(){
        Double sum = 0.0;
        for (Double d: doubles){
            sum += d;
        }
        return sum/N;
    }

    /**
     * Viborochnaja dispersija (nesmeshennaja tochechnaja ocenka dispersii)
     * @return
     */
    public Double getS2PointDispersion(){
        double xMiddle = getXPointAverage();
        double sum = 0.0;
        for (Double d: doubles){
            sum += Math.pow(d - xMiddle,2);
        }
        return 1./(N - 1)*sum;
    }

    /**
     * Tochechnaja ocenka srednego kvadraticheskogo otklonenija
     * @return
     */
    public Double standardDeviation(){
        return Math.sqrt(getS2PointDispersion());
    }

    /**
     * Ocenka srednego kvadraticheskogo otklonenija
     * @return
     */
    public Double estimateStandardDeviation(){
        return standardDeviation()/Math.sqrt(N);
    }

    public Double excess(){
        return getCentralStatisticalMoment(4)/Math.pow(standardDeviation(),4);
    }



    public int getIntervals(){
        return (int)(3.33*Math.log10(N)+1);
    }

    public double getH(){
        return ((getMax() - getMin())/getIntervals());
    }

    public Double getMin(){
        double min = doubles.get(0);
        for(Double d: doubles){
            if (min > d){
                min = d;
            }
        }
        return min;
    }

    public Double getMax(){
        double max = doubles.get(0);
        for (Double d: doubles){
            if (max<d){
                max = d;
            }
        }
        return max;
    }

    public double getCentralStatisticalMoment(int degree){
        double sum = 0.0;
        double xAv = getXPointAverage();
        for(Double d: doubles){
            sum += Math.pow((d - xAv), degree);
        }
        return 1./N*sum;
    }


    public void printDoubles(){
        for (Double d: doubles){
            System.out.println(d);
        }
    }
    public void printValues(){
        System.out.println("10^3="+(int) Math.pow(10,3));
        System.out.println("N = "+doubles.size());
        System.out.println("X average = "+getXPointAverage());
        System.out.println("S^2 Dispersion = "+getS2PointDispersion());
        System.out.println("S = "+standardDeviation());
        System.out.println("min x = "+getMin());
        System.out.println("max x = "+getMax());
        System.out.println("m intervals = "+getIntervals());
        System.out.println("gap h = "+ReadValuesFromFile.roundResult(getH(), 4));
        System.out.println("Mju^4 = "+getCentralStatisticalMoment(4));
        System.out.println("Exscess = "+excess());
    }
}
