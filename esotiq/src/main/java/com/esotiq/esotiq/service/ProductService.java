package com.esotiq.esotiq.service;

import com.esotiq.esotiq.model.Product;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        return getAllProductsFromFile();
    }

    private File file;
    private Scanner scanner;
    private List<Product> products = new ArrayList<>();

    public ProductService() throws FileNotFoundException {
        this.file = new File("C:\\Users\\48665\\Downloads\\esotiq\\esotiq\\src\\main\\resources\\plik.txt");
        this.scanner = new Scanner(file);
        getAllProductsFromFile();
    }

    public List<Product> getModels() {
        return products;
    }

    private List<Product> getAllProductsFromFile() {

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String example = line;
            if (example.startsWith("Indeks tow.Nazwa kat. indeksuSuma IloœciSuma Wart. C.S.N.Suma Wart. C.S.B.")) {
                example = example.replaceAll("Indeks tow.Nazwa kat. indeksuSuma IloœciSuma Wart. C.S.N.Suma Wart. C.S.B.", "");
            }
            if (example.contains("Wydrukowano")) {
                example = example.replaceAll("(Wydrukowano).*", "");
            }


            Product modelExample = new Product();

            String[] examples;
            examples = example.split("-");
            // model

            modelExample.setModel(examples[0]);
            //kolor

            modelExample.setColour(examples[1]);
            example = examples[2];
// rozmiar
            if (example.startsWith("S") || example.startsWith("M") || example.startsWith("L")) {
                modelExample.setSize(example.substring(0, 1));
                example = example.substring(1);
            }
            if (example.startsWith("XL")) {
                modelExample.setSize(example.substring(0, 2));
                example = example.substring(2);
            }
            if (example.startsWith("XXL")) {
                modelExample.setSize(example.substring(0, 3));
                example = example.substring(3);
            }
// pattern na rozmiar stanika
            Pattern patternSize = Pattern.compile("[6-9]{1}[05]{1}[A-H]{1}.*");
            Matcher matcher = patternSize.matcher(example);
            if (matcher.matches()) {
                modelExample.setSize(example.substring(0, 3));
                example = example.substring(3);
            }
            // kategoria
            examples = example.split(" ");

            modelExample.setCategory(examples[0]);
            // ceny ilosc
            Pattern patternPrice = Pattern.compile(".*( )([A-Z]+)([0-9]+)[,]{1}[0]{2}[0-9]+[,]{1}[0-9]{2}[0-9]+[,]{1}[0-9]{2}");
            Matcher matcher1 = patternPrice.matcher(example);
            examples = example.split(",");


// ustwienie ceny brutto
            String a = examples[examples.length - 2];
            String b = examples[examples.length - 1];
            a = a.substring(2);
            b = b.substring(0, 2);
            String price = a + "." + b;
            modelExample.setPriceBrutto(Double.valueOf(price));

            a = examples[examples.length - 3];
            b = examples[examples.length - 2];

            // cena netto
            a = a.substring(2);
            b = b.substring(0, 2);
            price = a + "." + b;
            modelExample.setPriceNetto(Double.valueOf(price));

            // ilosc
            examples = examples[0].split("[^0-9]+");
            String quantity = examples[examples.length - 1];
            modelExample.setQuantity(Integer.valueOf(quantity));
            products.add(modelExample);
        }
//        System.out.println("Co zostało: " + example);
//
//        System.out.println("Wracamy do początku: \n" + line);
//        example = example.substring(0,example.length()-5);
//        System.out.println("Example: " + example);
//        examples = line.split(example);
//        for (String s : examples) {
//            System.out.println("==============");
//            System.out.println(s);
        return products;
    }
}
