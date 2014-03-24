package system_programming.lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 3/24/14.
 */
public class MyRules implements Rules {

    @Override
    public List<Production> getRules() {
        ArrayList<Production> rules = new ArrayList<>(10);
        rules.add(new Production('S', 'с', 'B'));
        rules.add(new Production('S', 'т', 'B'));
        rules.add(new Production('A', 'а', 'C'));
        rules.add(new Production('A', 'г', 'C'));
        rules.add(new Production('B', 'а', 'D'));
        rules.add(new Production('B', 'у', 'C'));
        rules.add(new Production('C', '<', null));
        rules.add(new Production('C', 'і', 'C'));
        rules.add(new Production('D', 'ш', 'A'));
        rules.add(new Production('D', 'и', 'C'));
        return rules;
    }
}
