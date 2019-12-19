package com.liukhtenko.informationhandling.entity;

import java.util.ArrayList;

public class TextComposite implements Component {
    private static final String NEW_LINE = "\n";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private TextLevel level;
    private ArrayList<Component> components = new ArrayList<>();

    public TextComposite(TextLevel level) {
        this.level = level;
    }

    public TextComposite(TextLevel level, ArrayList<Component> components) {
        this.level = level;
        this.components = components;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public TextLevel getLevel() {
        return level;
    }

    public void setLevel(TextLevel level) {
        this.level = level;
    }

    public String operation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : components) {
            if (this.getLevel() == TextLevel.PARAGRAPH) {
                stringBuilder.append(TAB);
            }
            stringBuilder.append(component.operation());
            if (this.getLevel() == TextLevel.PARAGRAPH) {
                stringBuilder.append(NEW_LINE);
            }
            if (this.getLevel() == TextLevel.SENTENCE) {
                stringBuilder.append(SPACE);
            }
        }
        return stringBuilder.toString();
    }

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TextComposite{\"level=").append(level).append("}");
        return sb.toString();
    }
}
