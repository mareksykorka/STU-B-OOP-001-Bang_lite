package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";

    public Beer() {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        return false;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return false;
    }

    /*@Override
    public boolean play(Player player) {
        player.addLives(1);
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }*/
}
