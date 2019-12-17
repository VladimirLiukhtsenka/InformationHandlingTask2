package com.liukhtenko.informationhandling.logic;

import com.liukhtenko.informationhandling.entity.Component;
import com.liukhtenko.informationhandling.entity.TextComposite;
import com.liukhtenko.informationhandling.entity.TextLevel;
import com.liukhtenko.informationhandling.exeption.CustomExeption;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextLogic {
    static Logger logger = LogManager.getLogger();

    public String sortParagraphsByCountSentence(TextComposite textComposite) {
        
        textComposite.getComponents().sort((o1, o2) -> {
            TextComposite tc1 = (TextComposite) o1;
            TextComposite tc2 = (TextComposite) o2;
            return Integer.compare(tc1.getComponents().size(), tc2.getComponents().size());
        });
        return textComposite.operation();
    }

    public String sentenceWithLongestWord(TextComposite textComposite) throws CustomExeption {
       if (textComposite.getLevel() == TextLevel.TEXT) {  // FIXME: 16.12.2019 
           StringBuilder sentences = new StringBuilder();
           String text = textComposite.operation();
           Pattern pattern = Pattern.compile("[а-яА-яЁё\\w]+");
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
           throw new CustomExeption("The method was called incorrectly");// FIXME: 14.12.2019 
       }
    }

    public Map<String, Integer> countWords(TextComposite textComposite) {
        String s = textComposite.operation();
        Map<String, Integer> wordsMap = new HashMap<>();
        Pattern pattern = Pattern.compile("[а-яА-яЁё\\w]+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
                logger.log(Level.INFO, word + " added " + (wordsMap.get(word) + 1) + " times");
            } else {
                wordsMap.put(word, 1);
                logger.log(Level.INFO, "Added new word: " + word);
            }
        }
        return wordsMap;
    }

    public String deleteSentence(TextComposite textComposite, int numberOfWords) {
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
    }
}
