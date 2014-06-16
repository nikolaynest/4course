package diplom.com.nikolay.ui;

import diplom.com.nikolay.RiskHelper;

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

    private JLabel label;
    private JTextField riskNameTextField;
    private JButton addRiskButton;
    private JPanel mainPanel;
    private JComboBox<Double> ratingComboBox;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private ButtonGroup buttonGroup;

    private JPanel headerPanel;
    private JPanel buttonPanel;

    private RiskHelper riskHelper;
    private double rating = NOT_CORRECT;
    private boolean acceptable = true;

    public TreeDraw(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 720));
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
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.setLayout(new BorderLayout(3,5));

//        mainPanel.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
//        mainPanel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.add(footer, BorderLayout.CENTER);
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
        SpringUtilities.makeCompactGrid(panel,
                labelStrings.length, 2,
                GAP, GAP, //init x,y
                GAP, GAP / 2);//xpad, ypad
        return panel;
    }

    private JComponent createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        addRiskButton = new JButton("Добавить");
        addRiskButton.addActionListener(listenerForButton);
        buttonPanel.add(addRiskButton);
//        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        return buttonPanel;
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

            String riskName = riskNameTextField.getText();
            if (!checkString(riskName)) {
                JOptionPane.showMessageDialog(null, "Введите название риска!", "Ошибка ввода",
                        JOptionPane.ERROR_MESSAGE);
            } else if (rating == NOT_CORRECT) {
                JOptionPane.showMessageDialog(null, "Выберите значение для вероятности!", "Ошибка ввода",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                riskHelper.addNode(riskHelper.getTreeNode(), riskName, rating, acceptable);
                riskNameTextField.setText("");
                yesRadioButton.setSelected(true);
                rating = NOT_CORRECT;
                JOptionPane.showMessageDialog(null, "Риск успешно добавлен", "!", JOptionPane.INFORMATION_MESSAGE);
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
