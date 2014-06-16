package kursovaja;

import kursovaja.oop.Logic;
import kursovaja.oop.MainFrame;
import kursovaja.structural.StructuralArchLogic;
import kursovaja.structural.StructuralMainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nikolay on 3/4/14.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new MainFrame();
//                new StructuralMainFrame();

                System.out.println(Pattern.quote("aaa"));
                System.out.println(Pattern.quote("aaaaaa"));
                System.out.println(Pattern.quote("aaabcd"));
                System.out.println(Pattern.quote("ccccc"));
                HashMap<String, Integer> map = new HashMap();
                System.out.println(map.size());
                System.out.println(map.get(""));


                Pattern p = Pattern.compile("^[a-zA-Zа-яА-Я]+$");
                Matcher m = p.matcher(" ");
                System.out.println(m.matches());
                m = p.matcher(" ");
                System.out.println(m.matches());
                m = p.matcher("\t");
                System.out.println(m.matches());
                m = p.matcher("\n");
                System.out.println(m.matches());
                m = p.matcher(" \r");
                System.out.println(m.matches());
                m = p.matcher("a");
                System.out.println(m.matches());
                m = p.matcher("abba");
                System.out.println(m.matches());
                m = p.matcher("привет");
                System.out.println(m.matches());
            }
        });
    }
//    public static void main(String[] args) throws NotAWordException, EmptySentenceException {
//        Logic logic = new Logic();
//        String sentence = "mama mila ramu mama mila";
//        HashMap<String, Integer> map = logic.splitSentence(sentence);
//        ArrayList<String> list = logic.getMostOftenRepeatedWord(map);
//        logic.printResults(list);
//    }
}
