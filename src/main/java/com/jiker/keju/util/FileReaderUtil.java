package com.jiker.keju.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
    public List<String> readFile(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        String tempString;
        List<String> stringLines = new ArrayList<>();
        while ((tempString = reader.readLine()) != null) {
            stringLines.add(tempString);
        }
        reader.close();
        return stringLines;
    }
}
