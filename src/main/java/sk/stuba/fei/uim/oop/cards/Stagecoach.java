package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";

    public Stagecoach() {
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
        player.setCardsOnHand(table.drawCards(2));
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }*/
}
