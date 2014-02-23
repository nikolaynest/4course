package system_programming.lab1;

import java.io.*;
import java.util.*;

/**
 * Created by nikolay on 2/12/14.
 * simplified program lexical analysis of the program text
 * (definition only keywords specified by the user).
 * This will result in a binary(text) file containing keywords in the form of codes,
 * and continue the rest of the program unchanged. Consecutive delimiters
 * (spaces, tabs, translations carriages) are replaced by a single space.
 */
public class Lexer {

    public final File KEY_WORDS = new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/key_words.txt");
    public final File KEY_WORDS_FILE =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/key_words.properties");
    private static final File SOURCE =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/main/java/system_programming/lab1/Lexer.java");
    private static final File DEST =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/lexer.txt");
    private static final File BINARY_DEST =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/binaryLexer.txt");


    /**
     * Create HashMap which will contain key words from file
     * and their position number in ascii representation
     * @param i sets the order of HashMap: if 'i' equals 1 than key is word and value word's number
     *          if 'i' equals 2 than key is number and value is the word
     * @return HashMap
     * @throws IOException
     */
    public HashMap<String, String> wordsFromGrid(int i) throws IOException {
        if (i != 1 && i != 2)throw new IllegalArgumentException("argument 'i' must be 1 or 2");
        HashMap<String, String> grid = new HashMap<String, String>();
        InputStream inputStream = new FileInputStream(KEY_WORDS_FILE);
        Properties properties = new Properties();
        properties.load(inputStream);
        for (String word : properties.stringPropertyNames()) {
            String val = properties.getProperty(word);
            Character ch = val.charAt(0);
            int ascii = (int)ch;
            if(i == 1){
                grid.put(word, Integer.toString(ascii));
            } else {
                grid.put(Integer.toString(ascii), word);
            }
        }
        return grid;
    }

    /**
     * Reads text from SOURCE file, search key words in this text convert
     * key words to digit representation, and rewrite from SOURCE text file
     * to DEST text file with changes, replacing all tabulation signs to spaces.
     *
     * @param source Text File which need to be changed
     * @param dest   Text File with changes
     * @throws IOException when reading/writing file occur one
     */

    public void rewrite(File source, File dest, int variant) throws IOException {
        HashMap<String, String> keyWords = wordsFromGrid(variant);
        StringTokenizer token;
        BufferedReader br = new BufferedReader(new FileReader(source));
        PrintWriter pw = new PrintWriter(dest);
        String line;
        while ((line = br.readLine()) != null) {
            token = new StringTokenizer(line, " \t\r\n");
            while (token.hasMoreTokens()) {
                String word = token.nextToken();
                if (keyWords.containsKey(word)) {
                    word = keyWords.get(word);
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
     *
     * @param source text file
     * @param dest   binary file
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
     *
     * @param word any word
     * @return String which contain all integer values of the word
     */
    @Deprecated
    private String convertWordToInt(String word) {
        StringBuilder convert = new StringBuilder("");
        for (int i = 0; i < word.length(); i++) {
            convert.append(Character.getNumericValue(word.charAt(i)));
        }
        return String.valueOf(convert);
    }

    /**
     * Reads file line by line, which line must contain only
     * one key word and add all lines to ArrayList
     *
     * @return ArrayList of key words
     * @throws IOException when reading file occur one
     */
    @Deprecated
    private ArrayList<String> getKeyWordsFromFile() throws IOException {
        ArrayList<String> keyWords = new ArrayList<String>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(KEY_WORDS));
        while ((line = br.readLine()) != null) {
            keyWords.add(line);
        }
        br.close();
        return keyWords;
    }
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        lexer.rewrite(SOURCE, DEST, 1);
        lexer.rewrite(DEST, BINARY_DEST,2);
    }
}
