package com.liukhtenko.informationhandling.parser;

import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {
    private AbstractParser successor = new SymbolParser();
    private static final String PARSE_ON_SYMBOL = "[\\w\\p{Punct}а-яА-ЯёЁ]";
    static Logger logger = LogManager.getLogger();

    @Override
    public void handleRequest(TextComposite textComposite, String text) {
        Pattern pattern = Pattern.compile(PARSE_ON_SYMBOL);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            TextComposite symbol = new TextComposite(TextLevel.SYMBOL);
            textComposite.add(symbol);
            logger.log(Level.INFO, textComposite + " added " + symbol);
            successor.handleRequest(symbol, matcher.group());
        }
    }
}
