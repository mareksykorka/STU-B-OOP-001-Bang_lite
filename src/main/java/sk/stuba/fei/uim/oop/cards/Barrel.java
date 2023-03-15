package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Barrel extends Card {
    private static final String CARD_NAME = "Barrel";
    private Random randomGenerator;

    public Barrel() {
        super(CARD_NAME, Colour.BLUE);
        this.randomGenerator = new Random();
    }

    /*@Override
    public boolean play(Player player) {
        for (Card card:player.getCardsOnTable()) {
            if(card instanceof Barrel)
            {
                System.out.println("You can not have two blue cards of the same type on the table at once!");
                return false;
            }
        }
        player.setCardsOnTable(player.removeCardOnHand(this));
        return false;
    }

    @Override
    public boolean receivePlay(Player player) {
        if ((randomGenerator.nextInt(4) + 1) == 1) {
            System.out.println("BANG evaded by BARREL.");
            return true;
        }
        System.out.println("BARREL not effective.");
        return false;
    }*/

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        return false;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        if ((randomGenerator.nextInt(4) + 1) == 1) {
            System.out.println("BANG evaded by BARREL.");
            return true;
        }
        System.out.println("BARREL not effective.");
        return false;
    }
}
