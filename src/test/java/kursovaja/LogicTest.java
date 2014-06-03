package kursovaja;

import org.junit.*;

import java.util.ArrayList;
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

    @Test(expected = NullPointerException.class)
    public void testCheckNullString(){
        String str = null;
        logic.checkNotNullString(str);
    }
    @Test
    public void testCheckNullStringNotNull(){
        String str = "";
        boolean b =logic.checkNotNullString(str);
        assertTrue(b);
    }
    @Test(expected = Logic.EmptySentenceException.class)
    public void checkNotNullButEmptySentence() throws Logic.EmptySentenceException {
        String s = "";
        logic.checkNotNullButEmptySentence(s);
    }
    @Test
    public void checkNotNullButEmptySentenceReturnTrue() throws Logic.EmptySentenceException {
        String s = "d";
        assertTrue(logic.checkNotNullButEmptySentence(s));
    }
    @Test
    public void testGetWordsOneDigit(){
        String digit = "2";
        ArrayList<String> words = logic.getWords(digit);
        assertEquals(words.get(0),digit);
    }
    @Test
    public void testGetWordsOneAlphabetChar(){
        String digit = "a";
        ArrayList<String> words = logic.getWords(digit);
        assertEquals(words.get(0),digit);
    }

    @Test
    public void testIsWordSpecSimbolFalse() throws Logic.NotAWordException {
        String notWord = "[\\t\\n\\r!?:;.,\\”'- @#$%^&*(){}[]+=\\|/><~]";
        assertFalse(logic.isWord(notWord));
    }

    @Test public void testIsWordTrueLatinOneChar() throws Logic.NotAWordException {
        String str = "w";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordTrueKirillicOneChar() throws Logic.NotAWordException {
        String str = "с";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordTrueLatin() throws Logic.NotAWordException {
        String str = "word";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordTrueKirillic() throws Logic.NotAWordException {
        String str = "слово";
        assertTrue(logic.isWord(str));
    }

    @Test public void testIsWordTrueLatinAndKirillic() throws Logic.NotAWordException {
        String str = "wordслово";
        assertTrue(logic.isWord(str));
    }
    @Test public void testIsWordFalseWordWithSpace() throws Logic.NotAWordException {
        String str = "wordслово ";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordFalseWordWithSpecSimbol() throws Logic.NotAWordException {
        String str = "word_слово";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordFalseNoChars() throws Logic.NotAWordException {
        String str = "";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordFalseSpace() throws Logic.NotAWordException {
        String str = " ";
        assertFalse(logic.isWord(str));
    }
    @Test public void testIsWordSpecSimbolFalseOne() throws Logic.NotAWordException {
        String str = "@";
        assertFalse(logic.isWord(str));
    }
    @Test (expected = NullPointerException.class)
    public void testIsWordNull() throws Logic.NotAWordException {
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
    @Test public void testSplitSentenceNumOfToken() throws Logic.NotAWordException, Logic.EmptySentenceException {
        HashMap<String, Integer> map = logic.splitSentence("mama mila ramu");
        assertEquals(3, map.size());
    }
}
