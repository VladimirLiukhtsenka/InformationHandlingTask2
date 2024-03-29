package com.liukhtenko.informationhandling.writer;

import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.exeption.CustomException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomWriter {
    private final static String DEFAULT_PATH = "data/dataOut.txt";

    public CustomWriter() {
    }

    public void write(TextComposite textComposite) throws CustomException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(DEFAULT_PATH))) {
            printWriter.write(textComposite.operation());
        } catch (IOException e) {
            throw new CustomException("File cannot be write ");
        }
    }
}
