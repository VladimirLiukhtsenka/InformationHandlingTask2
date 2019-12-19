package com.liukhtenko.informationhandling.entity;

import com.liukhtenko.informationhandling.exeption.CustomException;

public class Punctuation implements Component {
    private char punctuation;

    public Punctuation(char symbol) {
        this.punctuation = symbol;
    }

    @Override
    public String operation() {
        return this.toString();
    }

    @Override
    public void add(Component c) throws CustomException {
        throw new CustomException("Unable to add component " + c + " to leaf");
    }

    @Override
    public void remove(Component c) throws CustomException {
        throw new CustomException("Unable to remove component " + c + " from leaf");
    }

    @Override
    public Component getChild(int index) throws CustomException {
        throw new CustomException("Unable to get child from leaf");
    }

    public char getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(char punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Punctuation that = (Punctuation) o;
        return punctuation == that.punctuation;
    }

    @Override
    public int hashCode() {
        int result = 31;
        char ch = punctuation;
        result = result * Character.hashCode(ch);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(punctuation);
    }
}
