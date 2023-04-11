package org.example.sem2.hw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public interface BD {
    String bdFile = "D:\\Обучение Разработчик\\ООП\\Vvedenie_v_oop\\src\\main\\java\\org\\example\\sem2\\hw\\BD.txt";

    void ReadData(String bdFile, List<String> strList) throws FileNotFoundException;

    void WriteBD(String strProduct) throws IOException;
}
