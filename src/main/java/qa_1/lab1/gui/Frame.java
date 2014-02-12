package qa_1.lab1.gui;


import qa_1.lab1.Food;
import qa_1.lab1.Fridge;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

/**
 * Created by nikolay on 2/8/14.
 */
public class Frame extends JFrame {

    private Fridge fridge;
    private JToggleButton button;
    private JLabel label;
    JLabel hasFood;

    public Frame() {
        super("ХОЛОДИЛЬНИК");
        initFridge();

        ImageIcon green = new ImageIcon("images/green.jpg");
        ImageIcon red = new ImageIcon("images/red.jpg");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel header = new JPanel();
//        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
//        header.setAlignmentX(header.LEFT_ALIGNMENT);

        label = new JLabel("Выключен");
        button = new JToggleButton("ON/OFF");
        button.setSize(200, 200);
        button.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (button.isSelected()) {
                    fridge.turnOn();
                    label.setText("Включен");
                } else {
                    fridge.turnOff();
                    label.setText("Выключен");
                }
            }
        });
        header.add(button);
        header.add(label);
//        header.setAlignmentX(header.CENTER_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        hasFood = new JLabel("Холодильник пуст");
        infoPanel.add(hasFood);

        JPanel foodPanel = new JPanel();
        JLabel jlb = new JLabel("Введите название продукта");
        final JTextField foodName = new JTextField(18);
        JButton foodButton = new JButton("Положить в холодильник");
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fridge.putFoodIn(new Food(foodName.getText(),
                        Calendar.getInstance().get(Calendar.DAY_OF_YEAR)));
                if (!fridge.isEmpty()) {
                    hasFood.setText("В холодильнике есть продукты");
                } else {
                    hasFood.setText("Холодильник пуст");
                }

            }
        });
        foodPanel.add(jlb);
        foodPanel.add(foodName);
        foodPanel.add(foodButton);

        JPanel foodListPanel = new JPanel();

        JTable listTable = new JTable(fridge.getFood(), new String[]{"Продукт", "Испорченный"});
        JScrollPane jScrollPane = new JScrollPane(listTable);
        listTable.setPreferredScrollableViewportSize(new Dimension(200, 50));

        foodListPanel.add(jScrollPane);


        panel.add(header);
        panel.add(infoPanel);
        panel.add(foodPanel);
        panel.add(foodListPanel);

        add(panel);
        setVisible(true);
    }

    private class MyTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return fridge.getNumOfProducts();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Food food = fridge.getProducts().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return food.getFoodName();
                case 1:
                    return food.isSpoiled();
            }
            return null;
        }
    }

    private void initFridge() {
        if (fridge == null) {
            fridge = new Fridge();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame();
            }
        });
    }

}
