package system_programming.lab2;

import java.io.*;
import java.nio.charset.Charset;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nikolay on 2/22/14.
 */
public class RealNumbersRegex {

    public void showMatchesFromFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), Charset.forName("UTF-8")));
        StringTokenizer token;
        String line;
        int lineNumber = 1;
        while ((line = br.readLine()) != null){
            token = new StringTokenizer(line," \t\r\n{}();,");
            while (token.hasMoreTokens()){
                String word = token.nextToken();
                if (printMatches(word)){
                    System.out.println("line: "+lineNumber+"  "+word);
                }
            }
            lineNumber++;
        }
        br.close();
    }

    public boolean printMatches(String str){
        Pattern pattern = Pattern.compile("^-?[0-9]+\\.[0-9]+[FfDd]?$");
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    public static void main(String[] args) throws IOException {
        RealNumbersRegex rnr = new RealNumbersRegex();
        File file = new File("/home/nikolay/IdeaProjects/4COURSE/src/resources/sys_prog/regexTest.txt");
        rnr.showMatchesFromFile(file);
    }
}
