package kursovaja;

import javax.swing.*;

/**
 * Created by nikolay on 3/4/14.
 */
public class Window extends JFrame
{
    Window()
    {
        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton button = new JButton("Кнопка");
        button.setSize(80, 30);
        button.setLocation(20,20);
        panel.add(button);
        button = new JButton("Кнопка с длинной надписью");
        button.setSize(120, 40);
        button.setLocation(100,50);
        panel.add(button);
        setContentPane(panel);
        setSize(250, 150);
        setVisible(true);
    }

}
