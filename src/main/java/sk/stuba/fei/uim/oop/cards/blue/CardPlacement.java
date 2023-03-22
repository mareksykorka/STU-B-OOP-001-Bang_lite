package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;

import java.util.ArrayList;

public interface CardPlacement {
    public boolean canBePlacedOnTable(ArrayList<Card> table, Class cardType);
}
