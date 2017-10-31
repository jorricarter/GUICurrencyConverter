import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static com.sun.glass.ui.Cursor.setVisible;
import static java.nio.file.Paths.get;

/**
 * Created by fk1836ql on 10/24/2017.
 */
public class CurrencyConverter extends JFrame {
    private JTextField dollarsTextField;
    private JButton convertButton;
    private JLabel eurosResultLabel;
    private JPanel mainPanel;
    private JComboBox<String> currencyComboBox;

    private double dollarsToEurosExchangeRate = 0.84;
    private double dollarsToPoundsExchangeRate = 0.74;

    private final String EUROS = "Euros";
    private final String POUNDS = "Pounds";

    private HashMap<String, Double> exchangeRates = new HashMap<>();


    protected CurrencyConverter(){
        setContentPane(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        exchangeRates.put(EUROS, dollarsToEurosExchangeRate);
        exchangeRates.put(POUNDS, dollarsToPoundsExchangeRate);

        currencyComboBox.addItem(EUROS);
        currencyComboBox.addItem(POUNDS);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dollarString = dollarsTextField.getText();

                try {
                    double dollars = Double.parseDouble(dollarString);
                    String toCurrency = (String) currencyComboBox.getSelectedItem();

                    double exchangeRate = exchangeRates.get(toCurrency);
                    double converted = dollars * exchangeRate;

                    String resultString = String.format("%.2f dollars is equivalent to %.2f %s", dollars, converted, toCurrency);
                    eurosResultLabel.setText(resultString);
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(CurrencyConverter.this, "Please enter a number without $ symbols or other characters");
                }
            }
        });
    }
}
