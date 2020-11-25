
package moneycalculator.persistence.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

public class RestExchangeRateLoader implements ExchangeRateLoader{
    
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(from,to,read(from.getIsoCode(), to.getIsoCode()));
        }catch (MalformedURLException ex) {
            return null;
        }catch (IOException e) {
            return null;
        }
    }
    
    private double read (String from, String to) throws IOException {
        String line = read(new URL("http://data.fixer.io/api/latest?access_key=fd1c12be2fb99cd3e070448f81731cb4&symbols="+from+","+to));
        return Double.parseDouble(line.substring(line.indexOf(from)+5, line.lastIndexOf(",")));
    }

    private String read(URL url) throws IOException {
        InputStream is = url.openStream();
        byte[] buffer = new byte[4096];
        is.read(buffer);
        int length = is.read(buffer);
        String res = new String(buffer,0,length);
        return res;
    }
    
    
    
}
