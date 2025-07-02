package com.example.productapi.util;

import com.example.productapi.model.Category;
import com.example.productapi.model.Product;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
                String description = line[3];
                String image = line[4];
                List<String> socialMediaLinks = Arrays.asList(line[5].split(";"));
                List<String> highlights = Arrays.asList(line[6].split(";"));
                String manufacturerInfo = line[7];
                String technicalSpecs = line[8];
                products.add(new Product(id, name, price, description, image, socialMediaLinks, highlights, manufacturerInfo, technicalSpecs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Category> readCategoriesFromCsv(String filePath) {
        List<Category> categories = new ArrayList<>();
        try (Reader r = new FileReader(filePath);
             CSVReader csvReader = new CSVReader(r)) {
            String[] line;
            boolean header = true;
            while ((line = csvReader.readNext()) != null) {
                if (header) { header = false; continue; }
                Long id = Long.parseLong(line[0]);
                String name = line[1];
                String description = line[2];
                categories.add(new Category(id, name, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }


    public static void writeProductsToCsv(String filePath, List<Product> products) {
        try (Writer w = new FileWriter(filePath, false);
             CSVWriter csvWriter = new CSVWriter(w)) {
            csvWriter.writeNext(new String[]{"id", "name", "price", "description", "image", "socialMediaLinks", "highlights", "manufacturerInfo", "technicalSpecs"});
            for (Product p : products) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(p.getId()),
                        p.getName(),
                        p.getPrice().toString(),
                        p.getDescription(),
                        p.getImage(),
                        String.join(";", p.getSocialMediaLinks()),
                        String.join(";", p.getHighlights()),
                        p.getManufacturerInfo(),
                        p.getTechnicalSpecs()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeCategoriesToCsv(String filePath, List<Category> categories) {
        try (Writer w = new FileWriter(filePath, false);
             CSVWriter csvWriter = new CSVWriter(w)) {
            csvWriter.writeNext(new String[]{"id", "name", "description"});
            for (Category c : categories) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(c.getId()),
                        c.getName(),
                        c.getDescription()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}