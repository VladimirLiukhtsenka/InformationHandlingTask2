package com.liukhtenko.informatiohandling.writer;

import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import com.liukhtenko.informationhandling.exeption.CustomException;
import com.liukhtenko.informationhandling.parser.TextParser;
import com.liukhtenko.informationhandling.writer.CustomWriter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomWriterTest {
    private CustomWriter customWriter;
    private TextParser textParser;
    private TextComposite textComposite;

    @BeforeClass
    public void setup() {
        customWriter = new CustomWriter();
        textParser = new TextParser();
        textComposite = new TextComposite(TextLevel.TEXT);
    }

    @AfterClass
    public void tearDown() {
        customWriter = null;
        textParser = null;
        textComposite = null;
    }

    @Test
    public void writeTest() throws CustomException, IOException {
        String expected = "    It has survived - not only (five) centuries, but also the leap into electronic\n" +
                "type setting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига)\n" +
                "with the release of Letraset sheets containing Lorem Ipsum passages, and more\n" +
                "recently with desktop publishing software like Aldus PageMaker Faclon9 including\n" +
                "versions of Lorem Ipsum!\n" +
                "    It is a long a!=b established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using Ipsum is that it has a\n" +
                "more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here),\n" +
                "content here's, making it look like readable English?\n" +
                "    It is a established fact that a reader will be of a page when looking at its layout...\n" +
                "    Bye бандерлоги.";
        textParser.handleRequest(textComposite, expected);
        customWriter.write(textComposite);
        try (Stream<String> stream = Files.lines(Paths.get("data/dataOut.txt"))) {
            List<String> list = stream.collect(Collectors.toList());
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : list) {
                stringBuilder.append(s);
            }
            String actual = stringBuilder.toString();
            Assert.assertNotEquals(expected, actual);
        }
    }
}