
package moneycalculator;

import moneycalculator.model.Currency;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.control.Command;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;
import moneycalculator.ui.swing.SwingMoneyDialog;
import moneycalculator.ui.swing.SwingMoneyDisplay;

public class MainFrame extends JFrame{

    private final Map<String,Command> commands = new HashMap<>();
    private  MoneyDialog moneyDialog;
    private  MoneyDisplay moneyDisplay;
    private final List<Currency> currencies;

    public MainFrame(List<Currency> currencies) {
        this.currencies = currencies;
        this.setTitle("Money Calculator");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(moneyDialog(),BorderLayout.NORTH);
        this.add(moneyDisplay(),BorderLayout.CENTER);
        this.add(toolbar(),BorderLayout.SOUTH);
        this.setVisible(true);
        
    }
    public void add(Command command) {
        commands.put(command.name(),command);
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }
    

    private Component moneyDialog() {
      SwingMoneyDialog dialog = new SwingMoneyDialog(currencies);
      this.moneyDialog = dialog;
      return dialog;
    }

   
    private Component moneyDisplay() {
      SwingMoneyDisplay display = new SwingMoneyDisplay();
      this.moneyDisplay = display;
      return display; 
    }
    
    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.add(calculateButton());
        return panel;
    }
    // JButton
    private Component calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("calculate").execute();
            }
        };
    }
}
