package kursovaja.oop;

import kursovaja.EmptySentenceException;
import kursovaja.NotAWordException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nikolay on 3/4/14.
 */
public class Logic {

    public boolean isWord(String str){
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Я]+$");
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    public StringTokenizer getTokenizer(String str){
        return new StringTokenizer(str, " \t\r\n,.!?;:-\"'");
    }

    public HashMap<String,Integer> splitSentence(String sentence) throws NotAWordException, EmptySentenceException {
        //module 1 - получить данные - вернуть предложение
        getSentence(sentence);

        // модуль 2 - разбиение на слова
        StringTokenizer tokenizer = getTokenizer(sentence);
        HashMap<String, Integer> wordsMap = new HashMap<>();
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if (isWord(token) && !isSerial(token)){
                if (wordsMap.containsKey(token)){
                    wordsMap.put(token,wordsMap.get(token)+1);
                }else {
                    wordsMap.put(token,1);
                }
            }
        }
        return wordsMap;
    }
    public boolean isSerial(String word){
        char[] chars = word.toCharArray();
        char first = chars[0];
        for (char ch:chars){
            if (ch != first){
                return false;
            }
        }
        return true;
    }
    public String getSentence(String sentence) throws EmptySentenceException {
        checkNotNullString(sentence);
        checkNotNullButEmptySentence(sentence);
        return sentence;
    }


    public boolean checkNotNullString(String str){
        if (str == null){
            throw new NullPointerException("You don't enter the string!");
        }
        return true;
    }

    public boolean checkNotNullButEmptySentence(String str) throws EmptySentenceException {
        if (str.equals("")){
            throw new EmptySentenceException("Your sentence has no any word!");
        }
        return true;
    }
    //модуль 3 - получить слова, чаще использующиеся, и их количество
    public ArrayList<String> getMostOftenRepeatedWord(HashMap<String, Integer> map){
        ArrayList<String> list = new ArrayList<>();
        int max = 0;
        for(Integer i : map.values()){
            if (i>max){
                max = i;
            }
        }
        for(HashMap.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue()==max){
                list.add(entry.getKey());
            }
        }
        return list;
    }

     //module 4 - print results
     public String stringFromArrayList(ArrayList<String> list){
         StringBuilder sb = new StringBuilder("");
         for (String s: list){
             sb.append(s).append(" ");
         }
         return sb.toString();
     }
//    public void printResults(ArrayList<String> words){
//        for (String s:words){
//            System.out.println(s);
//        }
//    }

    public static void main(String[] args) throws NotAWordException, EmptySentenceException {
        Logic logic = new Logic();
        String sentence = "mama mila ramu mama mila";
        HashMap<String, Integer> map = logic.splitSentence(sentence);
        ArrayList<String> list = logic.getMostOftenRepeatedWord(map);
//        logic.printResults(list);
    }
}
