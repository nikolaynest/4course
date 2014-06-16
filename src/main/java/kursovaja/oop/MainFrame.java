package kursovaja.oop;

import kursovaja.EmptySentenceException;
import kursovaja.NotAWordException;
import kursovaja.oop.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nikolay on 3/4/14.
 */
public class MainFrame{
    Logic logic;

    JFrame frame;
    JLabel textLabel;
    JTextField textField;
    JButton button;

    Box box;
    JLabel answerLabel;
    JTextField resultTextField;

    public MainFrame() {

        logic = new Logic();
        createGUI();
    }

    private void createGUI(){
        frame = new JFrame("WORD'S FINDING (oop style)");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,0,5));

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
                String sentence = textField.getText();
                ArrayList<String> list = null;
                try {
                    HashMap<String, Integer> map = logic.splitSentence(sentence);
                    list = logic.getMostOftenRepeatedWord(map);
                } catch (NotAWordException e1) {
                    e1.printStackTrace();
                } catch (EmptySentenceException e1) {
                    e1.printStackTrace();
                }
                String result = logic.stringFromArrayList(list);
                resultTextField.setText(result);

            }
        });
        panel.add(button);

        answerLabel = new JLabel("Слово: ");
        answerLabel.setLocation(10,120);
        panel.add(answerLabel);

        resultTextField = new JTextField();
        panel.add(resultTextField);

        frame.setContentPane(panel);
        frame.setSize(500, 200);
        frame.setResizable(false);
        frame.setVisible(true);
    }



}
