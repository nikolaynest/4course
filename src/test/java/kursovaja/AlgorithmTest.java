package kursovaja;

import gosy.Algorithm;
import org.junit.Before;
import org.junit.Test;
import system_programming.lab1.Lexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikolay on 17.06.14.
 */
public class AlgorithmTest {
    private Algorithm algorithm;
    private double[][] testArray;
    private final double EPS = 0.001;

    @Test
    public void test1(){
        testArray = new double[][]{{1,2,3},{4,5,6}};
        algorithm = new Algorithm(testArray);
        assertEquals(0.0, algorithm.sumOfColumnWithZeros(), EPS);
    }

    @Test public void test2() {

        testArray = new double[][]{{0,0,0},{4,5,6}};
        algorithm = new Algorithm(testArray);
        assertEquals(15, algorithm.sumOfColumnWithZeros(), EPS);
    }

    @Test public void test3() {
        testArray = new double[][]{{0,1,2},{4,5,6}};
        algorithm = new Algorithm(testArray);
        assertEquals(4, algorithm.sumOfColumnWithZeros(), EPS);
    }

    @Test public void test4() {
        testArray = new double[][]{{1,2,0},{4,5,6}};
        algorithm = new Algorithm(testArray);
        assertEquals(6, algorithm.sumOfColumnWithZeros(), EPS);
    }
    @Test public void test5() {
        testArray = new double[][]{{1,0,2},{4,5,6}};
        algorithm = new Algorithm(testArray);
        assertEquals(5, algorithm.sumOfColumnWithZeros(), EPS);
    }
    @Test public void test6() {
        testArray = new double[][]{{0,0,0},{0,0,0}};
        algorithm = new Algorithm(testArray);
        assertEquals(0, algorithm.sumOfColumnWithZeros(), EPS);
    }

}
