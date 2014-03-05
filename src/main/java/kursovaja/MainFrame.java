package kursovaja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nikolay on 3/4/14.
 */
public class MainFrame{
    JFrame frame;
    JLabel textLabel;
    JTextField textField;
    JButton button;

    Box box;
    JLabel answerLabel;

    public MainFrame() {
        createGUI();
    }

    private void createGUI(){
        frame = new JFrame("WORD'S FINDING");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,0,5));

        textLabel = new JLabel("Введите текст:", SwingConstants.LEFT);
        textLabel.setLocation(10,40);
        panel.add(textLabel);

        textField = new JTextField(50);
        textField.setLocation(10,40);
        textField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(textField);

        button = new JButton("Поиск");
        button.setPreferredSize(new Dimension(100, 30));
        button.setLocation(10, 80);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerLabel.setText("hello");

            }
        });
        panel.add(button);

        answerLabel = new JLabel("Слово: ");
        answerLabel.setLocation(10,120);
        panel.add(answerLabel);


        frame.setContentPane(panel);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }



}
