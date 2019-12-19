package com.liukhtenko.informationhandling.entity;

import com.liukhtenko.informationhandling.exeption.CustomException;

public class Letter implements Component {
    private char letter;

    public Letter(char symbol) {
        this.letter = symbol;
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

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Letter letter1 = (Letter) o;
        return letter == letter1.letter;
    }

    @Override
    public int hashCode() {
        int result = 31;
        char ch = letter;
        result = result * Character.hashCode(ch);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }
}
