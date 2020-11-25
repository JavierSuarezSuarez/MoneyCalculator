
package moneycalculator.persistence.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.persistence.CurrencyListLoader;

public class FileCurrencyListLoader implements CurrencyListLoader {

    private final String filename;
    public FileCurrencyListLoader (String filename) {
        this.filename = filename;
    }
    
    @Override
    public List<Currency> currencies() {
        List<Currency> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            while (true) {
                String line = reader.readLine();
                if(line == null) break;
                list.add(currencyOf(line));
            }
               
        }catch (FileNotFoundException e) { 
            System.out.println("Error: Fichero no encontrado");
        }catch (IOException e) {
            System.out.println("Error al abrir o cerrar el fichero");
        }
        return list;
    }
    
    private Currency currencyOf(String line) {
        String[] split = line.split(",");
        return new Currency(split[0],split[1],split[2]);
    }
}
