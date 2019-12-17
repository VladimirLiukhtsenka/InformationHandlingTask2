package com.liukhtenko.informatiohandling.parser;

import com.liukhtenko.informationhandling.entity.Component;
import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import com.liukhtenko.informationhandling.exeption.CustomExeption;
import com.liukhtenko.informationhandling.parser.TextParser;
import com.liukhtenko.informationhandling.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AllParserTest {

    private DataReader dataReader;
    private TextComposite textComposite;
    private TextParser textParser;

    @BeforeClass
    public void setup() throws CustomExeption {
        dataReader = new DataReader();
        String text = dataReader.read();
        textComposite = new TextComposite(TextLevel.TEXT);
        textParser = new TextParser();
        textParser.handleRequest(textComposite, text);
    }

    @AfterClass
    public void tearDown() {
        dataReader = null;
        textComposite = null;
        textParser = null;
    }

    @Test
    public void textParserTest() {
        int expected = 4;
        int actual = textComposite.getComponents().size();
        Assert.assertEquals(expected, actual, 0.1);
    }

    @Test
    public void paragraphParserTest() {
        int expected = 6;
        int actual = 0;
        for (Component component : textComposite.getComponents()) {
            TextComposite paragraph = (TextComposite) component;
            actual += paragraph.getComponents().size();
        }
        Assert.assertEquals(expected, actual, 0.1);
    }

    @Test
    public void sentenceParserTest() {
        int expected = 118;
        int actual = 0;
        for (Component componentParagraph : textComposite.getComponents()) {
            TextComposite paragraph = (TextComposite) componentParagraph;
            for (Component componentSentence : ((TextComposite) paragraph).getComponents()) {
                TextComposite sentence = (TextComposite) componentSentence;
                actual += sentence.getComponents().size();
            }
        }
        Assert.assertEquals(expected, actual, 0.1);
    }

    @Test
    public void lexemeParserTest() {
        int expected = 643;
        int actual = 0;
        for (Component componentParagraph : textComposite.getComponents()) {
            TextComposite paragraph = (TextComposite) componentParagraph;
            for (Component componentSentence : ((TextComposite) paragraph).getComponents()) {
                TextComposite sentence = (TextComposite) componentSentence;
                for (Component componentLexeme : sentence.getComponents()) {
                    TextComposite lexeme = (TextComposite) componentLexeme;
                    actual += lexeme.getComponents().size();
                }
            }
        }
        Assert.assertEquals(expected, actual, 0.1);
    }
}
