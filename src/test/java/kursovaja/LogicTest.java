package kursovaja;

import org.junit.*;

import java.util.HashMap;
import java.util.StringTokenizer;

import static org.junit.Assert.*;
/**
 * Created by nikolay on 3/4/14.
 */
public class LogicTest {

    private Logic logic;

    @Before
    public void init(){
        logic = new Logic();
    }

    /**
     * isWord:
     */
    @Test public void testIsWordTrueLatinOneChar(){
        String str = "w";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordTrueKirillicOneChar(){
        String str = "с";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordTrueLatin(){
        String str = "word";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordTrueKirillic(){
        String str = "слово";
        assertTrue(logic.isWord(str));
    }

    @Test public void testIsWordTrueLatinAndKirillic(){
        String str = "wordслово";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordFalseWordWithSpace(){
        String str = "wordслово ";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordFalseWordWithSpecSimbol(){
        String str = "word_слово";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordFalseNoChars(){
        String str = "";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordFalseSpace(){
        String str = " ";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordFalseSpecSimpol(){
        String str = "@";
        assertFalse(logic.isWord(str));
    }
    @Test (expected = NullPointerException.class)
    public void testIsWordNull(){
        String str = null;
        logic.isWord(str);
    }

    /**
     * StringTokenizer test
     */
    @Test public void testTokenLength(){
        StringTokenizer tokenizer = logic.getTokenizer("mama   ?\"'mila'\"!ramu");
        assertEquals(3, tokenizer.countTokens());
    }
    /**
     * test splitSentence
     */
    @Test public void testSplitSentenceNumOfToken(){
        HashMap<String, Integer> map = logic.splitSentence("mama mila ramu");
        assertEquals(3, map.size());
    }
}
