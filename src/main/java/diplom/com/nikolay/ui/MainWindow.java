package diplom.com.nikolay.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by nikolay on 23.05.14.
 */
public class MainWindow extends JFrame{

    private Container c;
    private CardLayout c1;
    private JPanel p;
    private JButton button1;
    public MainWindow (String s) {
        super(s);
        c = getContentPane();

        p = new JPanel();
        c1 = new CardLayout ();
        p.setLayout(c1);
        p.add(new JButton ("1"), "page1");
        p.add(new JButton ("2"), "page2");
        p.add(new JButton ("3"), "page3");

        c.add(p);
        c1.next(p);
        c1.show(p, "page1");
        JMenuBar jmb = new JMenuBar();
        JMenu jm1 = new JMenu("Пуск");
        JMenuItem mi1 = new JMenuItem("Первая страница");
        JMenuItem mi2 = new JMenuItem("Вторая страница");
        JMenuItem mi3 = new JMenuItem("Третья страница");
        JMenuItem mi4 = new JMenuItem("Выход");
        mi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                c1.show(p, "page1");
            }});

        mi2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                c1.show(p, "page2");
            }});

        mi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                c1.show(p, "page3");
            }});

        jmb.add(jm1);
        jm1.add(mi1);
        jm1.add(mi2);
        jm1.add(mi3);
        jm1.addSeparator();
        jm1.add(mi4);
        c.add(jmb, BorderLayout.NORTH);
        setSize (400,300);
        setVisible(true);
    }

    public static void main (String [] args) {
        JFrame f = new MainWindow (" Простенькая програ");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
