
package moneycalculator;

import java.util.Scanner;
import moneycalculator.model.*;

public class MoneyCalculator {
    private static Money money;
    private static ExchangeRate exchangeRate;
    private static Currency currency;
    
    public MoneyCalculator() {}
    
    public static void control() {
        input();
        process();
        output();
    }
    
    private static void input() {
       System.out.println("Introduce una cantidas en dolares: ");
       Scanner scanner = new Scanner(System.in);
       double amount = Double.parseDouble(scanner.next());
       
        System.out.println("Introduce una divisa: ");
        String isoCode = scanner.next();
        
        currency = new Currency(isoCode, "...","...");
        money = new Money(amount, currency);
       
    }
    
    private static void process() {
        exchangeRate = new ExchangeRate(currency, new Currency("EUR","...","..."));
        
    }
    
    private  static void output() {
        System.out.println(money.getAmount() + " " + currency.getIsoCode() + " equivalen a " + money.getAmount() * exchangeRate.getRate() + " EUR");
    }
    
    public static void main(String[] args) {
        control();
    }
   
}
