package eom.lab1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by nikolay on 2/26/14.
 */
public class First {
    private static final File DATA_FILE = new File("/home/nikolay/Documents/4COURSE-2/EOM/12.TXT");
    private void encoding(){
        System.out.println(Charset.defaultCharset());
    }
    public ArrayList<String> getValuesFromFile(File file){
        String line;
        StringTokenizer token;
        ArrayList<String> list = null;
        BufferedReader br = null;
        try {
//            br = new BufferedReader(new FileReader(file));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            list = new ArrayList<String>();
            while ((line = br.readLine())!=null){
                token = new StringTokenizer(line);
                token.nextToken();
                list.add(token.nextToken());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error when read file");
            e.printStackTrace();
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    public void printValues(List<String> list){
        for(String s : list){
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        First f = new First();
//        List<String> list = f.getValuesFromFile(First.DATA_FILE);
//        f.printValues(list);
          f.encoding();
    }
}
