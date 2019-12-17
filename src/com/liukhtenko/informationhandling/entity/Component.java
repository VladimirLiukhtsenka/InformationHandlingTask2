package com.liukhtenko.informationhandling.entity;

import com.liukhtenko.informationhandling.exeption.CustomExeption;

public interface Component {
    String operation();
    void add(Component c) throws CustomExeption;
    void remove(Component c) throws CustomExeption;
    Component getChild (int index) throws CustomExeption;
}
