package system_programming.lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by nikolay on 2/12/14.
 * simplified program lexical analysis of the program text
 * (definition only keywords specified by the user).
 * This will result in a binary(text) file containing keywords in the form of codes,
 * and continue the rest of the program unchanged. Consecutive delimiters
 * (spaces, tabs, translations carriages) are replaced by a single space.
 */
public class Lexer {

    public final File KEY_WORDS_FILE =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/key_words.txt");
    private static final File source =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/main/java/system_programming/lab1/Lexer.java");
    private static final File dest =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/lexer.txt");
    private static final File binaryDest =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/binaryLexer.txt");

    /**
     * Reads file line by line, which line must contain only
     * one key word and add all lines to ArrayList
     * @return ArrayList of key words
     * @throws IOException when reading file occur one
     */
    private ArrayList<String> getKeyWordsFromFile() throws IOException {
        ArrayList<String> keyWords = new ArrayList<String>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(KEY_WORDS_FILE));
        while ((line = br.readLine()) != null) {
            keyWords.add(line);
        }
        br.close();
        return keyWords;
    }

    /**
     * Reads text from source file, search key words in this text convert
     * key words to digit representation, and rewrite from source text file
     * to dest text file with changes, replacing all tabulation signs to spaces.
     * @param source Text File which need to be changed
     * @param dest Text File with changes
     * @throws IOException when reading/writing file occur one
     */
    public void rewrite(File source, File dest) throws IOException {
        StringTokenizer token;
        ArrayList<String> keyWords = getKeyWordsFromFile();

        BufferedReader br = new BufferedReader(new FileReader(source));
        PrintWriter pw = new PrintWriter(dest);
        String line;
        while ((line = br.readLine()) != null) {
            token = new StringTokenizer(line, " \t\r\n");
            while (token.hasMoreTokens()) {
                String word = token.nextToken();
                if (keyWords.contains(word)) {
                    word = convertWordToInt(word);
                }
                pw.print(word);
                pw.print(" ");
            }
        }
        pw.close();
        br.close();
    }

    /**
     * Same as rewrite() method, but write to binary file, not to text file
     * @param source text file
     * @param dest binary file
     */
    public void rewriteToBinary(File source, File dest) throws IOException {
        StringTokenizer token;
        ArrayList<String> keyWords = getKeyWordsFromFile();
        BufferedReader br = new BufferedReader(new FileReader(source));
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(dest));
        String line;
        while ((line = br.readLine()) != null) {
            token = new StringTokenizer(line, " \t\r\n");
            while (token.hasMoreTokens()) {
                String word = token.nextToken();
                if (keyWords.contains(word)) {
                    word = convertWordToInt(word);
                }
                outputStream.writeBytes(word);
                outputStream.writeBytes(" ");
            }
        }
        outputStream.close();
        br.close();
    }

    /**
     * Convert every char of the word to its integer value
     * @param word any word
     * @return String which contain all integer values of the word
     */
    private String convertWordToInt(String word) {
        StringBuilder convert = new StringBuilder("");
        for (int i = 0; i < word.length(); i++) {
            convert.append(Character.getNumericValue(word.charAt(i)));
        }
        return String.valueOf(convert);
    }


    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        lexer.rewrite(source, dest);
        lexer.rewriteToBinary(source, binaryDest);
    }
}
