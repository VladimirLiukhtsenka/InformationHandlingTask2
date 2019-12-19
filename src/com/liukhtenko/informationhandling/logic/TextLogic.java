package com.liukhtenko.informationhandling.logic;

import com.liukhtenko.informationhandling.entity.Component;
import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import com.liukhtenko.informationhandling.exeption.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextLogic {
    static Logger logger = LogManager.getLogger();
    private static final String WORD = "[а-яА-яЁё\\w]+";

    public String sortParagraphsByCountSentence(TextComposite textComposite) throws CustomException {
        if (textComposite.getLevel() == TextLevel.TEXT) {
            textComposite.getComponents().sort((o1, o2) -> {
                TextComposite tc1 = (TextComposite) o1;
                TextComposite tc2 = (TextComposite) o2;
                return Integer.compare(tc1.getComponents().size(), tc2.getComponents().size());
            });
            return textComposite.operation();
        } else {
            throw new CustomException("The method was called incorrectly");
        }
    }

    public String sentenceWithLongestWord(TextComposite textComposite) throws CustomException {
        if (textComposite.getLevel() == TextLevel.TEXT) {
            StringBuilder sentences = new StringBuilder();
            String text = textComposite.operation();
            Pattern pattern = Pattern.compile(WORD);
            Matcher matcher = pattern.matcher(text);
            int countLetters = 0;
            String longestWord = "";
            while (matcher.find()) {
                String word = matcher.group();
                if (word.length() > countLetters) {
                    countLetters = word.length();
                    longestWord = word;
                }
            }
            logger.log(Level.INFO, "The longest word in " + textComposite + " is " + longestWord);
            for (Component componentParagraph : textComposite.getComponents()) {
                for (Component componentSentence : ((TextComposite) componentParagraph).getComponents()) {
                    if (componentSentence.operation().contains(longestWord)) {
                        sentences.append(componentSentence.operation());
                    }
                }
            }
            logger.log(Level.INFO, sentences.toString() + " contains " + longestWord);
            return sentences.toString();
        } else {
            throw new CustomException("The method was called incorrectly");
        }
    }

    public Map<String, Integer> countWords(TextComposite textComposite) throws CustomException {
        if (textComposite.getLevel() == TextLevel.TEXT) {
            String s = textComposite.operation();
            Map<String, Integer> wordsMap = new HashMap<>();
            Pattern pattern = Pattern.compile(WORD);
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                if (wordsMap.containsKey(word)) {
                    wordsMap.put(word, wordsMap.get(word) + 1);
                    logger.log(Level.INFO, word + " added " + wordsMap.get(word) + " times");
                } else {
                    wordsMap.put(word, 1);
                    logger.log(Level.INFO, "Added new word: " + word);
                }
            }
            return wordsMap;
        } else {
            throw new CustomException("The method was called incorrectly");
        }
    }

    public String deleteSentence(TextComposite textComposite, int numberOfWords) throws CustomException {
        if (textComposite.getLevel() == TextLevel.TEXT) {
            StringBuilder text = new StringBuilder();
            for (Component componentParagraph : textComposite.getComponents()) {
                for (Component componentSentence : ((TextComposite) componentParagraph).getComponents()) {
                    TextComposite sentence = (TextComposite) componentSentence;
                    if (sentence.getComponents().size() >= numberOfWords) {
                        text.append(sentence.operation());
                        logger.log(Level.INFO, sentence.toString() + " have no less words than " + numberOfWords);
                    }
                }
            }
            return text.toString();
        } else {
            throw new CustomException("The method was called incorrectly");
        }
    }
}
