package com.liukhtenko.informationhandling.parser;

import com.liukhtenko.informationhandling.entity.TextComposite;

public abstract class AbstractParser {

    public abstract void handleRequest(TextComposite textComposite, String text);

}
