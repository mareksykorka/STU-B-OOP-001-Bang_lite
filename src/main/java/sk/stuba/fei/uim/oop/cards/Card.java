package sk.stuba.fei.uim.oop.cards;

import java.util.ArrayList;

public abstract class Card {
    protected String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
