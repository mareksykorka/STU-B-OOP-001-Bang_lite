package sk.stuba.fei.uim.oop.table;

import sk.stuba.fei.uim.oop.cards.*;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Table {
    private ArrayList<Card> deck;
    private ArrayList<Card> discardDeck;

    public Table() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 30; i++) {
            cards.add(new Bang());
        }
        for (int i = 0; i < 15; i++) {
            cards.add(new Missed());
        }
        for (int i = 0; i < 8; i++) {
            cards.add(new Beer());
        }
        for (int i = 0; i < 6; i++) {
            cards.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Stagecoach());
        }
        cards.add(new Indians());
        cards.add(new Indians());
        cards.add(new Barrel());
        cards.add(new Barrel());
        cards.add(new Dynamite());
        cards.add(new Prison());
        cards.add(new Prison());
        cards.add(new Prison());

        System.out.println("---------------- All Cards ----------------");
        for (Card card: cards) {
            System.out.println(card.getName());
        }
        System.out.println("---------------- Shuffled Cards ----------------");
        Collections.shuffle(cards);
        for (Card card: cards) {
            System.out.println(card.getName());
        }

        this.deck = cards;
        this.discardDeck = new ArrayList<Card>();
    }

}
