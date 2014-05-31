package diplom.com.nikolay.ui;

import diplom.com.nikolay.Rating;
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

    private JLabel label;
    private JTextField riskNameLabel;
    private JButton button;
    private JPanel panel;
    private JComboBox<Double> comboBox;

    private RiskHelper riskHelper;

    private final ActionListener listenerForButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    private final ActionListener listenerForCombo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            comboBox = (JComboBox<Double>) e.getSource();
            Double item = (Double) comboBox.getSelectedItem();
            riskNameLabel.setText(String.valueOf(item));
        }
    };
    private void initComponents(){
        riskHelper = new RiskHelper();

        label = new JLabel("Введите название риска: ");
        riskNameLabel = new JTextField(15);
        riskNameLabel.setText("hello");

        button = new JButton("Добавить");
        button.addActionListener(listenerForButton);

        panel = new JPanel();
        panel.setSize(new Dimension(500,300));
//        TODO: panel.setLayout(new );

        comboBox = new JComboBox<>(new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9});
        comboBox.addActionListener(listenerForCombo);

        panel.add(label);
        panel.add(riskNameLabel);
        panel.add(button);
        panel.add(comboBox);
    }
    public TreeDraw(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 800));
        initComponents();
        add(panel);
        setVisible(true);
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
