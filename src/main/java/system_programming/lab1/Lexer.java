package system_programming.lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by nikolay on 2/12/14.
 */
public class Lexer {

    public final File KEY_WORDS_FILE =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/key_words.txt");
    private static final File source =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/main/java/system_programming/lab1/Lexer.java");
    private static final File dest =
            new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/lexer.txt");

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

    public void rewrite(File source, File dest) throws IOException {
        StringTokenizer token;
        ArrayList<String> keyWords = getKeyWordsFromFile();
//        String space = " ";

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
                writeToFileByWord(pw, word);
            }
        }
        pw.close();
        br.close();
    }

    public void writeToFileByWord(PrintWriter pw, String word) throws IOException {
        pw.print(word);
        pw.print(" ");
    }

    private String convertWordToInt(String word) {
        StringBuilder convert = new StringBuilder("");
        for (int i = 0; i < word.length(); i++) {
            convert.append(Character.getNumericValue(word.charAt(i)));
        }
        return String.valueOf(convert);
    }


    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        String s = lexer.convertWordToInt("Hello world");
        System.out.println(s);

        lexer.rewrite(source, dest);
//        PrintWriter pw = new PrintWriter(dest);
//        pw.print("Hello world");
//        pw.print(" ");
//        pw.println("second");
//        pw.close();
    }
}
