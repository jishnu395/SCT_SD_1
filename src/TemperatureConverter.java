import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JComboBox<String> fromBox, toBox;
    private JTextField inputField, resultField;
    private JButton convertButton, clearButton;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        String[] units = {"°C", "°F", "K"};
        fromBox = new JComboBox<>(units);
        toBox = new JComboBox<>(units);
        inputField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        convertButton = new JButton("Convert");
        clearButton = new JButton("clear");
        convertButton.setFocusable(false);
        clearButton.setFocusable(false);

        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");
        JLabel inputLabel = new JLabel("Enter Temperature:");
        JLabel resultLabel = new JLabel("Result:");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        add(inputLabel, c);
        c.gridx = 1;
        add(inputField, c);

        c.gridx = 0; c.gridy = 1;
        add(fromLabel, c);
        c.gridx = 1;
        add(fromBox, c);

        c.gridx = 0; c.gridy = 2;
        add(toLabel, c);
        c.gridx = 1;
        add(toBox, c);

        c.gridx = 0; c.gridy = 3;
        add(resultLabel, c);
        c.gridx = 1;
        add(resultField, c);

        c.gridx = 0; c.gridy = 4;
        add(convertButton, c);
        c.gridx = 1;
        add(clearButton, c);

        convertButton.addActionListener(this);
        clearButton.addActionListener(e -> {
            inputField.setText("");
            resultField.setText("");
        });
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        try{
            double input = Double.parseDouble(inputField.getText());
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();
            double result = convertTemperature(input, from, to);
            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid Number!", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    private double convertTemperature(double value, String from, String to) {
        if (from.equals(to)) return value;
        double celsius;

        switch (from) {
            case "°F" -> celsius = (value - 32) * 5/9;
            case "K" -> celsius = value - 273.15;
            default -> celsius = value;
        }

        return switch (to) {
            case "°F" -> (celsius * 9/5) + 32;
            case "K" -> celsius + 273.15;
            default -> celsius;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverter::new);
    }
}