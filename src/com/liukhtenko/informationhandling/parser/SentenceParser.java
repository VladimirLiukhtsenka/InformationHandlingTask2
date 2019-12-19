package com.liukhtenko.informationhandling.parser;

import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {
    private AbstractParser successor = new LexemeParser();
    private static final String PARSE_ON_LEXEME = "[^\\s]+";
    static Logger logger = LogManager.getLogger();
    @Override
    public void handleRequest(TextComposite textComposite, String text) {
        Pattern pattern = Pattern.compile(PARSE_ON_LEXEME);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            TextComposite lexeme = new TextComposite(TextLevel.LEXEME);
            textComposite.add(lexeme);
            logger.log(Level.INFO, textComposite + " added " + lexeme);
            successor.handleRequest(lexeme, matcher.group());
        }
    }
}
