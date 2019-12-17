package com.liukhtenko.informationhandling.parser;

import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends AbstractParser {
    static Logger logger = LogManager.getLogger();
    private AbstractParser successor = new ParagraphParser();
    private static final String PARSE_ON_PARAGRAPH = "\\s{4,}";

    @Override
    public void handleRequest(TextComposite textComposite, String text) {
        String[] paragraphs = text.split(PARSE_ON_PARAGRAPH);
        for (String element : paragraphs) {
            if (element.length() > 0) {
                TextComposite paragraph = new TextComposite(TextLevel.PARAGRAPH);
                textComposite.add(paragraph);
                logger.log(Level.INFO, textComposite + " added " + paragraph);
                successor.handleRequest(paragraph, element);
            }
        }
    }
}
