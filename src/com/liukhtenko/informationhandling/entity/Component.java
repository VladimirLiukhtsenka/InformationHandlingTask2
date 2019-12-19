package com.liukhtenko.informationhandling.entity;

import com.liukhtenko.informationhandling.exeption.CustomException;

public interface Component {
    String operation();
    void add(Component c) throws CustomException;
    void remove(Component c) throws CustomException;
    Component getChild (int index) throws CustomException;
}
