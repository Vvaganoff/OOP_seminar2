package org.example.sem2.hw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Product.NewProduct1(scanner, BD.bdFile);

        HashSet<Product> productHashSet = ReadBD(BD.bdFile);
        FilterProducts(productHashSet, scanner);
        scanner.close();

    }

    public static HashSet<Product> ReadBD(String strFile) throws FileNotFoundException {
        List<String> stringList = Product.ReadData1(BD.bdFile);
        String strItem = stringList.get(0);
        HashSet<Product> productHashSet = new HashSet<>();
        for (int i = 1; i < stringList.size(); i++) {
            if ((i+1) % 9 != 0) {
                strItem = strItem + "," + stringList.get(i);
            } else {
                strItem = strItem + "," + stringList.get(i);
                if (strItem.charAt(0) == ',') {strItem = strItem.substring(1);}

                if (strItem.charAt(0) == '1') {
                    productHashSet.add(PersonalComputer.InputPC(strItem));
                } else {
                    productHashSet.add(Notebook.InputNotebook(strItem));
                }
                strItem = "";
            }

        }
        return productHashSet;
    }

    public static void FilterProducts(Set<Product> productHashSet, Scanner scanner) {
        Set<PersonalComputer> pcHashSet = new HashSet<>();
        Set<Notebook> notebookHashSet = new HashSet<>();
        for (Product product:productHashSet
             ) {
            if (product.getClass().equals(PersonalComputer.class)){
                pcHashSet.add(PersonalComputer.InputPC(product.toString()));
            } else {
                notebookHashSet.add(Notebook.InputNotebook(product.toString()));
            }
        }
        Map<Integer, String> filterMap = Product.InputFilters(scanner);
        System.out.println("=====Компьютеры====");
        for (PersonalComputer pc: PersonalComputer.FilterProducts(pcHashSet, filterMap)
             ) {
            System.out.println(pc.PrintProduct(pc) + '\n');
        }
        System.out.println("=====Ноутбуки====");
        for (Notebook notebook: Notebook.FilterProducts(notebookHashSet, filterMap)
             ) {
            System.out.println(notebook.PrintProduct(notebook)+ '\n');
        }

    }
}
