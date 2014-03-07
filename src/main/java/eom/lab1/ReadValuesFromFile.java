package eom.lab1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by nikolay on 2/26/14.
 */
public class ReadValuesFromFile {

    ArrayList<Double> doubles;
    private File fileWithData;
    private int N;

    public ReadValuesFromFile(File fileWithData) {
        this.fileWithData = fileWithData;
    }

    public static double roundResult(Double d, int precise){
        precise = (int) Math.pow(10,precise);
        d = d * precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }

    public void encoding() {
        System.out.println(Charset.defaultCharset());
    }
    public ArrayList<Double> getDoublesBeforeSort(){
        return convertStringToDouble();
    }

    public ArrayList<Double> getDoublesAfterSort(){
        convertStringToDouble();
        sortByAscending();
        return doubles;
    }

    private ArrayList<String> getValuesFromFile() {
        String line;
        StringTokenizer token;
        ArrayList<String> list = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileWithData),StandardCharsets.UTF_8)))
        {
            list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                token = new StringTokenizer(line);
                token.nextToken();
                list.add(token.nextToken());
            }
        }catch(FileNotFoundException e){
            System.err.println("Error file not found");
            e.printStackTrace();
        }catch(IOException e){
            System.err.println("Error when read file");
            e.printStackTrace();
        }
        return list;
    }

    private ArrayList<Double> convertStringToDouble(){
        ArrayList<String> beginValues = getValuesFromFile();
        N = beginValues.size();
        doubles = new ArrayList<>(N);
        for(String val : beginValues){
            doubles.add(roundResult(Double.parseDouble(val), 4));
        }
        return doubles;
    }

    private void sortByAscending(){
        Collections.sort(doubles, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
