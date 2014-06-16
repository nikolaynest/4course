package system_programming.lab5;

import system_programming.lab3.Rules;
import system_programming.lab4.MyRulesLab4;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by nikolay on 03.06.14.
 */
public class Parser {

    private Stack<Character> stack = new Stack<>();
    private ArrayList<Rule> rules;
    private Character currentChar;
    private Rule startRule;



    private String enterString;
    private int index;

    public Parser(String enterString) {
        rules = MyRulesLab5.getRules();
        index = 0;
        this.enterString = enterString;
        currentChar = enterString.charAt(index);
        startRule = rules.get(0);
    }

    public Rule getStartRule() {
        return startRule;
    }
    //
//    public void setEnterString(String enterString) {
//        this.enterString = enterString;
//    }

    public void reset(String newEnterString){
        this.enterString = newEnterString;
        index = 0;
        currentChar = enterString.charAt(index);
    }


    public void function(Rule rule) throws Exception {
        ArrayList<Rule> rulesWithEqualLeftSide = rulesWithEqualsLeftTokens(rule.getLeft());
        //A -> X1, X2,...,Xi перебор по всем продукциям левого символа
        for (int i = 0; i < rulesWithEqualLeftSide.size(); i++) {
            int temp = index;

            //получение текущего правила из "i"
            Rule currentRule = rulesWithEqualLeftSide.get(i);
            // получаем массив символов из правой части правила
            char[] currentRightArray = currentRule.getRight().toCharArray();
            // проход по всем символам правой части
            for (int k = 0; k < currentRightArray.length; k++) {
                char curRightChar = currentRightArray[k];
                if (Character.isUpperCase(curRightChar)) {
                    //находим правило, соответствующее нетерминалу входящей строки
                    // (новой сентенциальной формы)
                    // уходим вглубину
                    function(findRule(curRightChar));

                } else if (curRightChar == this.currentChar) {
                    currentChar = enterString.charAt(++index);
                    continue;
                } else if (i == rulesWithEqualLeftSide.size() - 1) {
                    throw new Exception("not valid string");

                    // todo: и если текущая продукция не подходит - сбросить указатель
                    // todo: входного потока до того момента, когда впервые
                    // todo: столкнулись с данной левой частью продукции
                } else {
                    index = temp;
                    //если мы не достигнем конца продукции, но никакие условия не подходят, значит
                    //нужно перейти к следующей продукции из этой же серии
                    break;
                }
            }

        }
    }

//    public void parser(char ch) throws Exception {
//        if (Character.isUpperCase(ch)) {
//            //
//        } else if (currentChar == ch) {
//
//        } else {
//            throw new Exception("this string not correct");
//        }
//    }

    private ArrayList<Rule> rulesWithEqualsLeftTokens(String leftToken) {
        ArrayList<Rule> rulesWithEqualsLeftPart = new ArrayList<>();
        for (Rule r : rules) {
            if (r.getLeft().equals(leftToken)) {
                rulesWithEqualsLeftPart.add(r);
            }
        }
        return rulesWithEqualsLeftPart;
    }

    Rule findRule(char ch) {
        for (Rule r : rules) {
            char left = r.getLeft().charAt(0);
            if (left == ch) {
                return r;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        String s = "h";
//        char ch = s.charAt(0);
//        System.out.println('h' == ch);
        ArrayList<String> strings = new EnterStrings().getList();
        Parser parser = new Parser(strings.get(0));
        for (String s: strings){
            parser.function(parser.getStartRule());
            parser.reset(s);
        }
    }
}
