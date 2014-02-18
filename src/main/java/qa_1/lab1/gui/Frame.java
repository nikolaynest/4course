package qa_1.lab1.gui;


import qa_1.lab1.Food;
import qa_1.lab1.Fridge;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    JLabel hasFoodLabel;
    final MyTableModel myTableModel = new MyTableModel();

    public JPanel firstPanel() {
        JPanel header = new JPanel();
        label = new JLabel("Включен");
        button = new JToggleButton("ON/OFF");
        button.setSelected(true);
//        button.setSize(200, 400);
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
        return header;
    }

    public JPanel secondPanel() {
        JPanel infoPanel = new JPanel();
        hasFoodLabel = new JLabel("Холодильник пуст");
        JButton updateButton = new JButton("Проверить продукты");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTableModel.fireTableDataChanged();
            }
        });
        infoPanel.add(hasFoodLabel);
        infoPanel.add(updateButton);
        return infoPanel;
    }

    public JPanel thirdPanel() {
        JPanel foodPanel = new JPanel();
        final JButton delete = new JButton("Вынуть из холодильника");
        delete.setEnabled(false);
        final JTable listTable = new JTable(myTableModel);
        listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel lsMode = listTable.getSelectionModel();
        lsMode.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                delete.setEnabled(true);
                final int row = listTable.getSelectedRow();
                String name = (String) listTable.getValueAt(row, 0);
                final Food f = new Food(name, 2);
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (listTable.isRowSelected(row) && row >= 0) {
                            myTableModel.fireTableRowsDeleted(row, row);
                            fridge.deleteFood(f);
                            delete.setEnabled(false);

                        }
                    }
                });
            }
        });

        JScrollPane jScrollPane = new JScrollPane(listTable);
        listTable.setPreferredScrollableViewportSize(new Dimension(250, 100));

        JLabel jlb = new JLabel("Введите название продукта:");
        final JTextField foodName = new JTextField(18);

        JButton foodButton = new JButton("Положить в холодильник");
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (foodName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Введите название продукта", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                } else {
                    fridge.putFoodIn(new Food(foodName.getText(),
                            Calendar.getInstance().get(Calendar.DAY_OF_YEAR)));
                    foodName.setText("");
                    if (!fridge.isEmpty()) {
                        hasFoodLabel.setText("В холодильнике есть продукты");
                    } else {
                        hasFoodLabel.setText("Холодильник пуст");
                    }
                    myTableModel.fireTableDataChanged();
                }
            }
        });
        Box box = Box.createVerticalBox();
        box.setAlignmentX(CENTER_ALIGNMENT);
        box.add(jlb);
        box.add(Box.createVerticalStrut(5));
        box.add(foodName);
        box.add(Box.createVerticalStrut(5));
        box.add(foodButton);
        box.add(Box.createVerticalStrut(20));
        box.add(jScrollPane);
        box.add(Box.createVerticalStrut(10));
        box.add(delete);
        foodPanel.add(box);
        return foodPanel;
    }

    public Frame() {
        super("ХОЛОДИЛЬНИК");
        initFridge();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(firstPanel());
        panel.add(secondPanel());
        panel.add(thirdPanel());
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
            if (rowIndex >= 0) {
                Food food = fridge.getProducts().get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return food.getFoodName();
                    case 1: {
                        if (food.isSpoiled()) {
                            return "нет";
                        } else return "Да";
                    }

                }
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            String result = "";
            switch (column) {
                case 0:
                    result = "Продукт";
                    break;
                case 1:
                    result = "Свежий";
                    break;
            }
            return result;
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
