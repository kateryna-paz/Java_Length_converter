package com.example.lab_swing_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class Calculator extends JFrame {

    private String[] itemsCI = {
            "кілометр(км)",
            "метр(м)",
            "дециметр(дм)",
            "сантиметр(см)",
            "міліметр(мм)"
    };
    private String[] itemsUS = {
            "миля(mi)",
            "ядр(yd)",
            "фут(ft)",
            "дюйм(in)",
            "болт(blt)"
    };
    private LengthConverter converter;
    private JTextField textFieldCI, textFieldUS;
    private JLabel label1, label2, labelError;
    private JComboBox comboBoxCI, comboBoxUS;
    private JButton btnToCI, btnToUS;
    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(null);
        this.setBounds(500,200,350,300);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if (btn == btnToCI) {
                    textFieldUS.setText(String.valueOf(convert(textFieldCI.getText(), textFieldCI.getName())));
                } else {
                    textFieldCI.setText(String.valueOf(convert(textFieldUS.getText(), textFieldUS.getName())));
                }
            }
        };
        ActionListener changeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField field = (JTextField)e.getSource();
                String name = field.getName();
                String text = field.getText();
                if (field == textFieldCI) {
                    textFieldUS.setText(String.valueOf(convert(text, name)));
                } else {
                    textFieldCI.setText(String.valueOf(convert(text, name)));
                }

            }
        };

        label1 = new JLabel("Напишіти число та оберіть обиницю виміру довжини");
        label1.setBounds(10,10,320,20);
        this.add(label1);

        textFieldCI = new JTextField("1");
        textFieldCI.setName("textFieldCI");
        textFieldCI.setBounds(20,40,170,20);
        textFieldCI.addActionListener(changeListener);
        this.add(textFieldCI);

        comboBoxCI = new JComboBox(itemsCI);
        comboBoxCI.setBounds(200,40,110,20);
        this.add(comboBoxCI);

        label2 = new JLabel("🔄️");
        label2.setBounds(100,70,320,20);
        this.add(label2);

        textFieldUS = new JTextField();
        textFieldUS.setName("textFieldUS");
        textFieldUS.setBounds(20,100,170,20);
        textFieldUS.addActionListener(changeListener);
        this.add(textFieldUS);

        comboBoxUS = new JComboBox(itemsUS);
        comboBoxUS.setBounds(200,100,110,20);
        this.add(comboBoxUS);

        btnToCI = new JButton("До US-системи");
        btnToCI.setBounds(35, 150, 125, 25);
        btnToCI.addActionListener(actionListener);
        this.add(btnToCI);

        btnToUS = new JButton("До CI-системи");
        btnToUS.setBounds(180, 150, 125, 25);
        btnToUS.addActionListener(actionListener);
        this.add(btnToUS);

        labelError = new JLabel("Невірний формат числа");
        labelError.setBounds(90, 190, 200, 20);
        labelError.setForeground(Color.red);
        labelError.setVisible(false);
        this.add(labelError);

        converter = new LengthConverter(Double.parseDouble(textFieldCI.getText()), "кілометр(км)", "миля(mi)");

    }

    public Double isNumber(String str) {
        try {
            Double num = Double.parseDouble(str);
            return num;
        } catch (NumberFormatException e) {
            System.out.println("Невірний формат числа");
            return null;
        }
    }

    public BigDecimal convert(String str, String name) {
        labelError.setVisible(false);
        Double num = isNumber(str);
        if (num == null) {
            labelError.setVisible(true);
            return null;
        }
        Double result;
        String valueCI = comboBoxCI.getSelectedItem().toString();
        String valueUS = comboBoxUS.getSelectedItem().toString();
        converter.set_num(num);
        converter.set_valueCI(valueCI);
        converter.set_valueUS(valueUS);
        if (name == "textFieldCI") {
            result = converter.convertToUS();
        } else {
            result = converter.convertToCI();
        }
        BigDecimal formattedResult = BigDecimal.valueOf(result);

        return formattedResult;

    }

    public static void main(String[] args) {
        Calculator app = new Calculator();
        app.setVisible(true);
    }
}


