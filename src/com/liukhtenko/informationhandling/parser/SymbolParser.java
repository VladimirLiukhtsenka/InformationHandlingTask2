package com.liukhtenko.informationhandling.parser;

import com.liukhtenko.informationhandling.entity.Letter;
import com.liukhtenko.informationhandling.entity.Punctuation;
import com.liukhtenko.informationhandling.entity.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser extends AbstractParser {
    private static final String PARSE_ON_PUNCTUATION = "\\p{Punct}";
    static Logger logger = LogManager.getLogger();

    @Override
    public void handleRequest(TextComposite textComposite, String text) {
        Pattern pattern = Pattern.compile(PARSE_ON_PUNCTUATION);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            Punctuation punctuation = new Punctuation(text.charAt(0));
            textComposite.add(punctuation);
            logger.log(Level.INFO, textComposite + " added " + punctuation);
        } else {
            Letter letter = new Letter(text.charAt(0));
            textComposite.add(letter);
            logger.log(Level.INFO, textComposite + " added " + letter);
        }
    }
}
