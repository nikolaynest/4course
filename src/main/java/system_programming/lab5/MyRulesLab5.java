package system_programming.lab5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 04.06.14.
 */
public class MyRulesLab5 {

    public static ArrayList<Rule> getRules() {
        ArrayList<Rule> rules = new ArrayList<>(10);
        rules.add(new Rule("S'","S$"));
        rules.add(new Rule("S","ACBA"));
        rules.add(new Rule("S","BACA"));
        rules.add(new Rule("A",null));
        rules.add(new Rule("A","bCA"));
        rules.add(new Rule("B","eB"));
        rules.add(new Rule("B","aB"));
        rules.add(new Rule("C","c"));
        rules.add(new Rule("C","d"));

        return rules;
    }
}
