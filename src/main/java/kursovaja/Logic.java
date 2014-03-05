package kursovaja;

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

    public HashMap<String,Integer> splitSentence(String sentence){
        StringTokenizer tokenizer = getTokenizer(sentence);
        HashMap<String, Integer> wordsMap = new HashMap<>();

        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if (isWord(token)){
                if (wordsMap.containsKey(token)){
                    wordsMap.put(token,wordsMap.get(token)+1);
                }else {
                    wordsMap.put(token,1);
                }
            }
        }
        return wordsMap;
    }

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


}
