package com.example.productapi.util;

import com.example.productapi.model.Product;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<Product> readProductsFromCsv(String filePath) {
        List<Product> products = new ArrayList<>();
        try (Reader r = new FileReader(filePath);
             CSVReader csvReader = new CSVReader(r)) {
            String[] line;
            boolean header = true;
            while ((line = csvReader.readNext()) != null) {
                if (header) { header = false; continue; }
                Long id = Long.parseLong(line[0]);
                String name = line[1];
                BigDecimal price = new BigDecimal(line[2]);
                products.add(new Product(id, name, price));
            }
        } catch (FileNotFoundException e) {
            // File not found; treat as empty
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void writeProductsToCsv(String filePath, List<Product> products) {
        try (Writer w = new FileWriter(filePath, false);
             CSVWriter csvWriter = new CSVWriter(w)) {
            csvWriter.writeNext(new String[]{"id", "name", "price"});
            for (Product p : products) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(p.getId()),
                        p.getName(),
                        p.getPrice().toString()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}