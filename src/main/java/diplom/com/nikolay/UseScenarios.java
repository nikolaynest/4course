package diplom.com.nikolay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by nikolay on 16.06.14.
 */
public class UseScenarios {


    private ArrayList<Scenario> scenarios;
    private double localFmin;

    public Scenario getBetter() {
        getBetterScenario();
        return better;
    }

    private Scenario better;

    public UseScenarios(ArrayList<Scenario> scenarios){
        this.scenarios = scenarios;
        setResultProb();
        setLocalFmin();
    }

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public ArrayList<StringBuilder> getScenariosToString(){
        StringBuilder sb;
        ArrayList<StringBuilder> sbList = new ArrayList<>();
        for (Scenario s: scenarios){
            sb = new StringBuilder("");
            for (Risk risk: s.getList()){
                sb.append("{"+risk.name);
                if (risk.isAcceptable()){
                    sb.append(": +} ");
                }else{
                    sb.append(": -} ");
                }
            }
//            sb.append("Вероятность = \t"+s.getResultProbability());
            StringBuilder resultSB = new StringBuilder("");
            resultSB.append(s.getResultProbability());
            resultSB.append(" = "+sb);
            sbList.add(resultSB);
        }
        return sbList;
    }

    private ArrayList<Scenario>  setResultProb(){
        double result;
        for (Scenario s: scenarios){
            result = 1.0;
            for (Risk r:s.getList()){
                result *= r.probability;
            }
            s.setResultProbability(RiskHelper.round(result, 4));
        }
        return scenarios;
    }

    public void getBetterScenario(){
        sortedScenario();
        for (Scenario s: scenarios){
            if (s.getFmin() > localFmin){
                better = s;
                break;
            }
        }
    }

    public ArrayList<Scenario> sortedScenario(){
        Collections.sort(scenarios, new Comparator<Scenario>() {
            @Override
            public int compare(Scenario o1, Scenario o2) {
                if (o1.getResultProbability()<o2.getResultProbability()){
                    return 1;
                }else if (o1.getResultProbability() > o2.getResultProbability()){
                    return -1;
                }
                return 0;
            }
        });
        return scenarios;
    }

    private void settingFmins(){
        for (Scenario s: scenarios){
            double fmin = 0.0;
            for (Risk risk:s.getList()){
                if (risk.isAcceptable()){
                    fmin += risk.probability;
                } else {
                    fmin -= risk.probability;
                }
            }
            s.setFmin(fmin);
        }
    }

    public void setLocalFmin(){
        settingFmins();
        localFmin = 2.7;
        for (Scenario s: scenarios){
            if (s.getFmin() < localFmin){
                localFmin = s.getFmin();
            }
        }
    }
}
