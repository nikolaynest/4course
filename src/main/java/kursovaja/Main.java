package kursovaja;

import javax.swing.*;

/**
 * Created by nikolay on 3/4/14.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
//                new Window();
            }
        });
    }
}
