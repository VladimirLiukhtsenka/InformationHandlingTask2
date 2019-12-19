package com.liukhtenko.informatiohandling.logic;

import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import com.liukhtenko.informationhandling.exeption.CustomException;
import com.liukhtenko.informationhandling.logic.TextLogic;
import com.liukhtenko.informationhandling.parser.TextParser;
import com.liukhtenko.informationhandling.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TextLogicTest {
    private DataReader dataReader;
    private TextComposite textComposite;
    private TextParser textParser;
    private TextLogic textLogic;

    @BeforeMethod
    public void setup() throws CustomException {
        dataReader = new DataReader();
        String text = dataReader.read();
        textComposite = new TextComposite(TextLevel.TEXT);
        textParser = new TextParser();
        textParser.handleRequest(textComposite, text);
        textLogic = new TextLogic();
    }

    @AfterMethod
    public void tearDown() {
        dataReader = null;
        textComposite = null;
        textParser = null;
        textLogic = null;
    }

    @Test
    public void sortParagraphsByCountSentenceTest() throws CustomException {
        String actual = "\tIt is a established fact that a reader will be of a page when looking at its layout... \n" +
                "\tBye бандерлоги. \n" +
                "\tIt has survived - not only (five) centuries, but also the leap into electronictype setting, " +
                "remaining essentially unchanged. \n" +
                "\tIt was popularised in the Динамо (Рига)with the release of Letraset sheets containing Lorem Ipsum passages, " +
                "and morerecently with desktop publishing software like Aldus PageMaker Faclon9 includingversions of Lorem Ipsum! \n" +
                "\tIt is a long a!=b established fact that a reader will be distracted by the readablecontent of a page when looking at its layout. \n" +
                "\tThe point of using Ipsum is that it has amore-or-less normal distribution ob.toString(a?b:c), as opposed to using " +
                "(Content here),content here's, making it look like readable English? \n";
        String expected = textLogic.sortParagraphsByCountSentence(textComposite);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sentenceWithLongestWordTest() throws CustomException {
        String actual = "It was popularised in the Динамо (Рига)with the release of Letraset sheets containing Lorem Ipsum passages, " +
                "and morerecently with desktop publishing software like Aldus PageMaker Faclon9 includingversions of Lorem Ipsum! ";
        String expected = textLogic.sentenceWithLongestWord(textComposite);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countWordsTest() throws CustomException {
        String s = "This sentence for test Test.";
        TextComposite textComposite = new TextComposite(TextLevel.TEXT);
        textParser.handleRequest(textComposite, s);
        Map<String, Integer> actual = new HashMap<>();
        actual.put("sentence", 1);
        actual.put("test", 2);
        actual.put("this", 1);
        actual.put("for", 1);
        Map expected = textLogic.countWords(textComposite);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteSentenceTest() throws CustomException {
        String actual = "It was popularised in the Динамо (Рига)with the release of Letraset sheets containing Lorem Ipsum passages, " +
                "and morerecently with desktop publishing software like Aldus PageMaker Faclon9 includingversions of Lorem Ipsum! ";
        String expected = textLogic.deleteSentence(textComposite, 30);
        Assert.assertEquals(expected, actual);
    }
}