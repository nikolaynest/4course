package system_programming.lab5;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by nikolay on 03.06.14.
 */
public class Parser {

    private Stack<Character> stack = new Stack<>();
    private ArrayList<String> strings;

    public Parser() {
        initList();
    }

    private void initList(){
        strings = new ArrayList<>();
        strings.add("aaaeeeddcb$");
        strings.add("aaaeeeddccb$");
        strings.add("aaaeeeddcccb$");
        strings.add("adbdd$");
        strings.add("adbddbc$");
        strings.add("adbddbd$");
        strings.add("adcdcdcaccdc$");
        strings.add("adcdcdcadccc$");
        strings.add("adcdcdcadcdc$");
        strings.add("adddcbaddc$");
        strings.add("adddcdddca$");
        strings.add("bbbbaaaecd$");
        strings.add("bbbbaaebcd$");
        strings.add("bbbbaebbcd$");
        strings.add("bdcacdc$");
        strings.add("bdcadcc$");
        strings.add("bdcadcdc$");
        strings.add("bdccdccacdcdcc$");
        strings.add("bdccdccadcccdc$");
        strings.add("bdccdccadccdcc$");
        strings.add("bedcbdcaadccddc$");
        strings.add("bedcbdcaadcdccd$");
        strings.add("bedcbdcaadcdcdc$");
        strings.add("dabdebcebd$");
        strings.add("dabdebdebc$");
        strings.add("dabdebdebd$");
        strings.add("eaaaabbdace$");
        strings.add("eaaaabbdaec$");
        strings.add("eaaaabbdaee$");
        strings.add("eebaddcdddd");
    }
    public void checkString(String str){

    }

}
