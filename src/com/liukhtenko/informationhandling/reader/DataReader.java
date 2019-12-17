package com.liukhtenko.informationhandling.reader;

import com.liukhtenko.informationhandling.exeption.CustomExeption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReader {
    private final static String DEFAULT_PATH = "data/data.txt";

    public DataReader() {
    }

    public String read() throws CustomExeption {
        List<String> list;
        try (Stream<String> stream = Files.lines(Paths.get(DEFAULT_PATH))) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new CustomExeption("File cannot be read");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
