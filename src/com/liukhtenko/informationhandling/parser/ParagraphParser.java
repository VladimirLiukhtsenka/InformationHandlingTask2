package com.liukhtenko.informationhandling.parser;


import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    private AbstractParser successor = new SentenceParser();
    private static final String PARSE_ON_SENTENCE = "[A-ZА-ЯЁ][^.?!]+((?![.?!][’\"]?\\s[\"’]?[A-ZА-ЯЁ][^.?!]).)+[.?!]+";
    static Logger logger = LogManager.getLogger();

    @Override
    public void handleRequest(TextComposite textComposite, String text) {
        Pattern pattern = Pattern.compile(PARSE_ON_SENTENCE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            TextComposite sentence = new TextComposite(TextLevel.SENTENCE);
            textComposite.add(sentence);
            logger.log(Level.INFO, textComposite + " added " + sentence);
            successor.handleRequest(sentence, matcher.group());
        }
    }
}
