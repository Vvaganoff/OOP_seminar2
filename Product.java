package org.example.sem2.hw;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class Product implements NewProduct, BD, ToPrint {
    protected static String type;
    protected String ram;
    protected String ramSize;
    protected String processor;
    protected String numberOfCore;
    protected String hdd;
    protected String hddSize;
    protected String monitorSize;
    protected String label;

    @Override
    public void NewProduct(Scanner scanner, String fileBD) throws IOException {
        NewProduct1(scanner, fileBD);
    }

    public static void NewProduct1(Scanner scanner, String fileBD) throws IOException {
        Boolean readNewLine = true;
        String newline = "";
        while (readNewLine) {
            System.out.println("Хотите ввести новый товар? (Если нет введите пустую строку)");
            newline = scanner.nextLine();
            if (!newline.equals("")) {
                System.out.println("""
                        Введите тип продукта:
                        1 - Персональный компьютер,
                        2 - Ноутбук.""");
                type = scanner.nextLine();
                if (type.strip().equals("2")) {
                    Product product = Notebook.InputNewNotebook(scanner);
                    product.WriteBD(product.toString());
                } else if (type.strip().equals("1")) {
                    Product product = PersonalComputer.InputNewPC(scanner);
                    product.WriteBD(product.toString());
                } else System.out.println("Такое мы не покупаем!");

            } else readNewLine = false;
        }
    }

    @Override
    public abstract void WriteBD(String strProduct) throws IOException;

    @Override
    public void ReadData(String bdFile, List<String> stringList) throws FileNotFoundException {
        ReadData1(bdFile);
    }

    public static List<String> ReadData1(String bdFile) throws FileNotFoundException {
        List<String> stringList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(bdFile);
            char[] charArray = new char[10000];
            try {
                fileReader.read(charArray);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String strItem = "";
            for (char item : charArray) {
                if ((item != ' ') && (item != '\n') && (item != '\u0000')) {
                    if ((item != ',') && (item != '\r')) {
                        strItem = strItem + item;
                    } else {
                        stringList.add(strItem);
                        strItem = "";
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }

    public static Map<Integer, String> InputFilters(Scanner scanner) {
        Map<Integer, String> resultMap = new HashMap<>();
        System.out.println("Введите индекс фильтра:" + '\n' +
                "1 - Тип ОЗУ" + '\n' +
                "2 - Размер ОЗУ" + '\n' +
                "3 - Процессор" + '\n' +
                "4 - Количество ядер" + '\n' +
                "5 - Тип жесткого диска" + '\n' +
                "6 - Размер жесткого диска" + '\n' +
                "7 - Размер дисплея" + '\n' +
                "8 - Производитель");
        String strFilter = scanner.nextLine();
        while (strFilter != "") {
            System.out.println("Введите значение фильтра:");
            String filterVolume = scanner.nextLine();
            resultMap.put(Integer.parseInt(strFilter), filterVolume);
            System.out.println("Введите индекс фильтра (для окончания введите пустую строку):");
            strFilter = scanner.nextLine();
        }
        return resultMap;
    }

    public String getType() {
        return type;
    }

    public String getRam() {
        return ram;
    }

    public String getRamSize() {
        return ramSize;
    }

    public String getProcessor() {
        return processor;
    }

    public String getNumberOfCore() {
        return numberOfCore;
    }

    public String getHdd() {
        return hdd;
    }

    public String getHddSize() {
        return hddSize;
    }

    public String getMonitorSize() {
        return monitorSize;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return type + ","
                + ram + ","
                + ramSize + ","
                + processor + ","
                + numberOfCore + ","
                + hdd + ","
                + hddSize + ","
                + monitorSize + ","
                + label;
    }
}
