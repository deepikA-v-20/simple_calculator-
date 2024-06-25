import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame {
    private JTextField value1Field;
    private JTextField value2Field;
    private JComboBox<String> operationBox;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JTextField resultField;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel value1Label = new JLabel("Value 1:");
        value1Field = new JTextField(10);
        JLabel value2Label = new JLabel("Value 2:");
        value2Field = new JTextField(10);

        JLabel operationLabel = new JLabel("Operation:");
        String[] operations = {"Select Operation", "Percentage", "Percent Increase", "Percent Decrease", "Find Full Value"};
        operationBox = new JComboBox<>(operations);

        calculateButton = new JButton("Calculate");
        resultLabel = new JLabel("Result: ");
        resultField = new JTextField(10);
        resultField.setEditable(false); // Make the result field non-editable

        panel.add(value1Label);
        panel.add(value1Field);
        panel.add(value2Label);
        panel.add(value2Field);
        panel.add(operationLabel);
        panel.add(operationBox);
        panel.add(new JLabel(""));
        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(resultField);

        add(panel);

        calculateButton.addActionListener(new CalculateButtonListener());
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double value1 = Double.parseDouble(value1Field.getText());
                double value2 = Double.parseDouble(value2Field.getText());
                String operation = (String) operationBox.getSelectedItem();

                double result = 0;

                switch (operation) {
                    case "Percentage":
                        result = (value1 * value2) / 100;
                        break;
                    case "Percent Increase":
                        result = value1 + ((value1 * value2) / 100);
                        break;
                    case "Percent Decrease":
                        result = value1 - ((value1 * value2) / 100);
                        break;
                    case "Find Full Value":
                        result = (value1 * 100) / value2;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Please select a valid operation.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                }

                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}
