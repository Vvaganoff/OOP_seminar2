package org.example.sem2.hw;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Notebook extends Product {
    private String type;
    private String ram;
    private String ramSize;
    private String processor;
    private String numberOfCore;
    private String hdd;
    private String hddSize;
    private String monitorSize;
    private String label;

    public Notebook(String type,String ram, String ramSize, String processor, String numberOfCore,
                    String hdd, String hddSize, String monitorSize, String label) {
        this.type = "2";
        this.ram = ram;
        this.ramSize = ramSize;
        this.processor = processor;
        this.numberOfCore = numberOfCore;
        this.hdd = hdd;
        this.hddSize = hddSize;
        this.monitorSize = monitorSize;
        this.label = label;
    }

    public static Notebook InputNewNotebook(Scanner scanner) {
        String line = "";
        System.out.println("Введите данные ноутбука в строку черезапятую (Цифра 2, Тип ОЗУ, размер ОЗУ, " +
                " процессор, количество ядер, тип жесткого диска, размер ЖД, размер дисплея, марка ноутбука):");
        line = scanner.nextLine();

        if (line.equals("")) {
            return null;
        }
        Notebook notebook1 = InputNotebook(line);
        return notebook1;
    }

    public static Notebook InputNotebook(String line) {
        String[] strArray = line.split("[,.;:]");
        Notebook notebook = new Notebook(strArray[0], strArray[1], strArray[2], strArray[3], strArray[4],
                strArray[5], strArray[6], strArray[7], strArray[8]);

        return notebook;
    }

    @Override
    public void WriteBD(String strProduct) throws IOException {
        try (FileWriter fileWriter = new FileWriter(BD.bdFile, true)) {
            fileWriter.append(strProduct + System.getProperty("line.separator"));
            fileWriter.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }


    @Override
    public String toString() {
        return super.type + ","
                + ram + ","
                + ramSize + ","
                + processor + ","
                + numberOfCore + ","
                + hdd + ","
                + hddSize + ","
                + monitorSize + ","
                + label;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getNumberOfCore() {
        return numberOfCore;
    }

    public void setNumberOfCore(String numberOfCore) {
        this.numberOfCore = numberOfCore;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getHddSize() {
        return hddSize;
    }

    public void setHddSize(String hddSize) {
        this.hddSize = hddSize;
    }

    public String getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(String monitorSize) {
        this.monitorSize = monitorSize;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String PrintProduct(Product product) {
        return "Ноутбук: " +
                label + '\n' +
                "ОЗУ: " + ram +
                ", " + ramSize + "MB" + '\n' +
                "Процессор: " + processor +
                ", " + numberOfCore + " ядра" + '\n' +
                "HDD: " + hdd +
                ", " + hddSize + "MB" + '\n' +
                "Размер дисплея: " + monitorSize + " дюймов";
    }
    public static List<Notebook> FilterProducts(Set<Notebook> productHashSet, Map<Integer, String> filterMap) {

        Set<Notebook> resultHashSet = productHashSet;
        for (Map.Entry<Integer, String> entry : filterMap.entrySet()
        ) {
            if (entry.getKey() == 1) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getRam().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 2) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getRamSize().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 3) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getProcessor().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 4) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getNumberOfCore().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 5) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getHdd().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 6) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getHddSize().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 7) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getMonitorSize().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 8) {
                resultHashSet = resultHashSet.stream().filter(notebook -> notebook.getLabel().equals(entry.getValue())).collect(Collectors.toSet());
            }
        } return resultHashSet.stream().toList();
    }
}
