package system_programming.lab5;

import system_programming.lab3.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 29.05.14.
 */
public class Info {

    private ArrayList<Character> T;
    private ArrayList<Character> N;
    private ArrayList<Character> TT;
    private ArrayList<Character> NN;
    private List<Production> productions;


    public Info() {
        initN();
        initT();
        initTTAndNN();
    }

    private void initT(){
        T = new ArrayList<>();
        T.add('a'); T.add('b'); T.add('c'); T.add('d'); T.add('e');

    }

    private void initN(){
        N = new ArrayList<>();
        N.add('S'); N.add('A'); N.add('B'); N.add('C');
    }

    /** Q is the S' from lecture. I change it because in Character I can use only one character.*/
    private void initTTAndNN(){
        TT = T;
        TT.add('$');
        NN = N;
        NN.add('Q');
    }

    private void initProductions(){
        this.productions = new ArrayList<>();
        productions.add(new Production('Q', 'S', '$'));

    }

    public ArrayList<Character> getTT() {
        return TT;
    }

    public ArrayList<Character> getNN() {
        return NN;
    }

    public static void main(String[] args) {
        Info info = new Info();
        ArrayList<Character> list = info.getNN();
        for (Character c:list){
            System.out.println(c);
        }

    }


}
