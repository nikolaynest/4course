package diplom.com.nikolay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nikolay on 24.05.14.
 */
public class TreeDraw extends JFrame {


    private int height = 30;
    private static final int GAP = 10;
    private static final int NOT_CORRECT = -1;

    private JTextField riskNameTextField;
    private JButton addRiskButton;
    private JPanel mainPanel;
    private JComboBox<Double> ratingComboBox;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private ButtonGroup buttonGroup;

    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JPanel footerPanel;
    private JTextArea resultText;
    private JTextArea bestResultTextArea;

    private RiskHelper riskHelper;
    private UseScenarios scenarios;
    private double rating = NOT_CORRECT;
    private boolean acceptable = true;

    public TreeDraw(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 650));
        initComponents();
        add(mainPanel);
        setBackground(Color.black);
        setVisible(true);
    }

    private void initComponents() {
        riskHelper = RiskHelper.getInstance();
        headerPanel = new JPanel() {
            //Don't allow us to stretch vertically.
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE, pref.height);
            }
        };
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        headerPanel.add(createEntryFields());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(3, 5));
        mainPanel.add(headerPanel, BorderLayout.NORTH);
//        mainPanel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.CENTER);
        mainPanel.add(createFooterPanel(), BorderLayout.CENTER);
    }

    private JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());
        String[] labelStrings = {"Название риска:", "Вероятность: ", "Приемлемость: ", ""};
        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] components = new JComponent[labelStrings.length];

        int componentNum = 0;

        riskNameTextField = new JTextField(50);
        riskNameTextField.setMaximumSize(riskNameTextField.getPreferredSize());
        components[componentNum++] = riskNameTextField;

        ratingComboBox = new JComboBox<>(new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9});
        ratingComboBox.setMaximumSize(ratingComboBox.getPreferredSize());
        ratingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ratingComboBox = (JComboBox<Double>) e.getSource();
                rating = (Double) ratingComboBox.getSelectedItem();
            }
        });
        components[componentNum++] = ratingComboBox;

        yesRadioButton = new JRadioButton("Да");
        yesRadioButton.addActionListener(listenerForRadio);
        yesRadioButton.setSelected(true);
        noRadioButton = new JRadioButton("Нет");
        noRadioButton.addActionListener(listenerForRadio);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(yesRadioButton);
        buttonGroup.add(noRadioButton);
        JPanel localPanel = new JPanel(new GridLayout(0, 1));
        localPanel.add(yesRadioButton);
        localPanel.add(noRadioButton);

        components[componentNum++] = localPanel;
        components[componentNum++] = createButtonPanel();

        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i], JLabel.TRAILING);
            labels[i].setLabelFor(components[i]);
            panel.add(labels[i]);
            panel.add(components[i]);
        }
        SpringUtilities.makeCompactGrid(panel, labelStrings.length, 2, GAP, GAP, //init x,y
                GAP, GAP / 2);//xpad, ypad
        return panel;
    }

    private JComponent createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addRiskButton = new JButton("Добавить");
        addRiskButton.addActionListener(listenerForButton);
        buttonPanel.add(addRiskButton);
        return buttonPanel;
    }

    private JComponent createFooterPanel() {
        footerPanel = new JPanel(new BorderLayout(20, 20));
        footerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label = new JLabel("Сценарии:");
        JButton button = new JButton("Получить лучший сценарий");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scenario better = new UseScenarios(riskHelper.getScenarios()).getBetter();
                bestResultTextArea.setText(better.toString());

            }
        });
        footerPanel.add(label, BorderLayout.NORTH);

        resultText = new JTextArea(8, 40);

        JScrollPane scenariosScrollPane = new JScrollPane(resultText);


        footerPanel.add(scenariosScrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout(20,30));

        JPanel panel2 = new JPanel(new FlowLayout());
        JLabel label1 = new JLabel("Лучший сценарий:");
        bestResultTextArea = new JTextArea(2, 50);
        bestResultTextArea.setEditable(false);
        Font font = new Font("Verdana", Font.BOLD, 12);
        bestResultTextArea.setFont(font);
        bestResultTextArea.setForeground(Color.GREEN);

        panel2.add(label1);

        JScrollPane bestScrollPane = new JScrollPane(bestResultTextArea);
        panel2.add(bestScrollPane);

        panel.add(button, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.CENTER);
        panel.add(new JLabel(""), BorderLayout.SOUTH);

        footerPanel.add(panel, BorderLayout.SOUTH);
        return footerPanel;
    }

    private final ActionListener listenerForRadio = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            if (radioButton.getText().equals("Да")) {
                acceptable = true;
            } else {
                acceptable = false;
            }
        }
    };

    private final ActionListener listenerForButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultText.setText("");

            String riskName = riskNameTextField.getText();
            if (!checkString(riskName)) {
                JOptionPane.showMessageDialog(null, "Введите название риска!", "Ошибка ввода",
                        JOptionPane.ERROR_MESSAGE);
            } else if (rating == NOT_CORRECT) {
                JOptionPane.showMessageDialog(null, "Выберите значение для вероятности!", "Ошибка ввода",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                riskHelper.addNode(riskHelper.getTreeNode(), riskName, rating, acceptable);
                riskHelper.inOrderTreeWalk(riskHelper.getTreeNode());

                riskNameTextField.setText("");
                yesRadioButton.setSelected(true);
                rating = NOT_CORRECT;
                riskHelper.resetLeafNodes();
                riskHelper.seekLeafNodesAndAdd(riskHelper.getTreeNode());
                riskHelper.getLeafNodes();
                scenarios = new UseScenarios(riskHelper.getScenarios());
                final String newLine = "\n";
                for (StringBuilder s : scenarios.getScenariosToString()) {
                    resultText.append(s.toString());
                    resultText.append(newLine);
                }
            }
        }
    };

    private boolean checkString(String name) {
        if (name == null || name.equals("") || name.equals(" "))
            return false;
        return true;
    }


    @Override
    public void print(Graphics g) {
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        g.setColor(Color.blue);
    }


    public static void main(String[] args) {
        new TreeDraw("Поддержка принятия решений");
    }
}
