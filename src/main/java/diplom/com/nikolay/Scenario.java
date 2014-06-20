package diplom.com.nikolay;

import java.util.ArrayList;

/**
 * Created by nikolay on 16.06.14.
 */
public class Scenario {

    private ArrayList<Risk> list = new ArrayList<>();
    private double resultProbability;
    private double fmin;

    public ArrayList<Risk> getList() {
        return list;
    }

    public double getFmin() {
        return fmin;
    }

    public void setFmin(double fmin) {
        this.fmin = fmin;
    }

    public void addRiskToList(Risk risk) {
        list.add(risk);
    }

    public double getResultProbability() {
        return resultProbability;
    }

    public void setResultProbability(double resultProbability) {
        this.resultProbability = resultProbability;
    }

    public void setList(ArrayList<Risk> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("").append(resultProbability).append(" = ");
        for (Risk risk:list){
            sb.append("{").append(risk.name);
            if (risk.isAcceptable()){
                sb.append(" +} ");
            }else {
                sb.append(" -} ");
            }
        }

        return sb.toString();
    }
}
