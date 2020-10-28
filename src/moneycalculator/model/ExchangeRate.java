
package moneycalculator.model;

public class ExchangeRate {

    private final Currency from;
    private final Currency to;
    private final double rate;
    
    public ExchangeRate(Currency from, Currency to) {
        this.from = from;
        this.to = to;
        this.rate = getExchangeRate(from.getIsoCode(), to.getIsoCode());
    }
    
    public Currency getFrom() {
        return from;
    }
    
    public Currency getTo() {
        return to;
    }
    
    public double getRate() {
        return rate;
    }
    
    private static Double getExchangeRate(String from, String to) {
       String line = "('USD_EUR':{'val':0.860155} 'GBP_EUR':{'val':0.860155} 'CMF_EUR':{'val':0.860155})";
       line = "{'".concat(line.substring(line.indexOf(from)));
       String processed_line = line.substring(line.indexOf(to) + 12, line.indexOf("}"));
       return Double.parseDouble(processed_line);
    }

}
