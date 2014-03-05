package sys_prog;

import org.junit.*;
import system_programming.lab1.Lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import static org.junit.Assert.*;
/**
 * Created by nikolay on 2/13/14.
 */
public class LexerTest {

    private Lexer lexer;
    private ArrayList<String> list;

    @Before public void init(){
        lexer = new Lexer();
        list = new ArrayList<String>();
    }

    @Test public void test1(){
        String s = "public class Frame extends JFrame {";
        StringTokenizer token = new StringTokenizer(s," \t\r\n");
        while (token.hasMoreTokens()){
            list.add(token.nextToken());
        }
        assertEquals(6,list.size());
    }

    @Test public void test2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(lexer.KEY_WORDS_FILE));
        String line;
        while ((line = br.readLine())!=null){
            list.add(line);
            System.out.println(line);
        }
        br.close();
        assertEquals(6,list.size());
    }

    @Test public void testWordsFromGrid() throws IOException {
        HashMap<String, String> grid = lexer.wordsFromGrid(1);
        for (String key : grid.keySet()){
            System.out.println("key="+key+" val="+grid.get(key));
        }
        assertEquals(6, grid.size());
    }
}
