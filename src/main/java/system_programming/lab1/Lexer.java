package system_programming.lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by nikolay on 2/12/14.
 */
public class Lexer {

    private final File KEY_WORDS_FILE = new File("system_programming/key_words.txt");

    private ArrayList<String> getKeyWordsFromFile() throws IOException {
        ArrayList<String> keyWords = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(KEY_WORDS_FILE));
        while (br.readLine() != null) {
            keyWords.add(br.readLine());
        }
        return keyWords;
    }

    public void some(File fileName) throws IOException {
        StringTokenizer token;
        ArrayList<String> keyWords = getKeyWordsFromFile();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while (br.readLine() != null) {
            token = new StringTokenizer(br.readLine());
            while (token.hasMoreTokens()) {
                String word = token.nextToken();
                if (keyWords.contains(word)) {
                    convertWordToInt(word);
                }
            }
        }
    }

    private String convertWordToInt(String word) {
        StringBuilder convert = new StringBuilder("");
        for (int i = 0; i < word.length(); i++) {
            convert.append(Character.getNumericValue(word.charAt(i)));
        }
        return String.valueOf(convert);
    }


    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        String s = lexer.convertWordToInt("Hello world");
        System.out.println(s);
    }
}
