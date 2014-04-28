package system_programming.lab3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikolay on 3/24/14.
 */
public class TestDKA {
    private static ArrayList<String> values = new ArrayList<>(30);
    public static ArrayList<String> getValues(){
        values.add("адам<");
        values.add("алік<");
        values.add("анна<");
        values.add("боря<");
        values.add("валя<");
        values.add("вася<");
        values.add("влад<");
        values.add("вова<");
        values.add("галя<");
        values.add("гена<");
        values.add("даша<");
        values.add("діма<");
        values.add("енді<");
        values.add("женя<");
        values.add("зося<");
        values.add("іван<");
        values.add("ірен<");
        values.add("катя<");
        values.add("коля<");
        values.add("лена<");
        values.add("люся<");
        values.add("маша<");
        values.add("мітя<");
        values.add("міша<");
        values.add("ната<");
        values.add("олег<");
        values.add("петя<");
        values.add("саша<");
        values.add("серж<");
        values.add("таня<");
        values.add("ташаіі<");
        values.add("саша");
        values.add("ташаіі");
        return values;
    }

    public static void main(String[] args) throws Exception {
        DKA dka;

//        Set<Character> states = new HashSet<>();
//        states.addAll(Arrays.asList(new Character[]{'S', 'A', 'B', 'C', 'D'}));
//        Set<Character> validChars = new HashSet<>();
//        validChars.addAll(Arrays.asList(new Character[]{'с','т','а','у','и','г','і','ш'}));

        Set<Character> endChars = new HashSet<>();
        endChars.add('<');

//        for (String tape: TestDKA.getValues()){
//            dka = new MyDKA(tape,'S',endChars);
//            dka.checkTape();
//        }

        for(String tape: TestDKA.getValues()){
            dka = new My2DKA(tape, 'S', endChars);
            dka.checkTape();
        }
    }
}
