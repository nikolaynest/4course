package system_programming.lab4;

import system_programming.lab3.Production;
import system_programming.lab3.Rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 03.05.14.
 */
public class MyRulesLab4 implements Rules {
    @Override
    public List<Production> getRules() {
        ArrayList<Production> rules = new ArrayList<>(10);
        rules.add(new Production('S', 'с', 'B'));
        rules.add(new Production('S', 'т', 'B'));
        rules.add(new Production('A', 'а', 'E'));
        rules.add(new Production('B', 'а', 'D'));
        rules.add(new Production('B', 'у', 'C'));
        rules.add(new Production('C', 'і', 'C'));
        rules.add(new Production('C', '<', null));
        rules.add(new Production('D', 'ш', 'F'));
        rules.add(new Production('E', 'і', 'C'));
        rules.add(new Production('E', 'с', 'B'));
        rules.add(new Production('E', 'т', 'B'));
        rules.add(new Production('E', '<', null));
        rules.add(new Production('G', 'т', 'B'));
        rules.add(new Production('G', 'с', 'B'));
        rules.add(new Production('G', 'ш', 'F'));
        rules.add(new Production('F', 'а', 'E'));
        rules.add(new Production('F', 'і', 'C'));
        rules.add(new Production('F', '<', null));
        rules.add(new Production('H', 'і', 'C'));
        rules.add(new Production('H', 'ш', 'F'));
        rules.add(new Production('H', '<', null));
        rules.add(new Production('I', 'і', 'C'));
        rules.add(new Production('I', 'с', 'B'));
        rules.add(new Production('I', 'т', 'B'));
        rules.add(new Production('I', 'ш', 'F'));
        rules.add(new Production('I', '<', null));
        return rules;
    }
}
