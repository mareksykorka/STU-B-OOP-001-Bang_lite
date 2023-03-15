package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    private Random randomGenerator;
    public Prison() {
        super(CARD_NAME, Colour.BLUE);
        this.randomGenerator = new Random();
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
        int targetIndex = this.choosePlayer(player);
        if(targetIndex == -1) {
            return false;
        }
        Player targetPlayer = game.getPlayerByIndex(targetIndex);
        for (Card card:player.getCardsOnTable()) {
            if(card instanceof Prison) {
                System.out.println("You can not have two blue cards of the same type on the table at once!");
                return false;
            }
        }
        targetPlayer.setCardsOnTable(player.removeCardOnHand(this));
        return false;
    }

    @Override
    public boolean receivePlay(Player player) {
        boolean returnVal = true;
        if ((randomGenerator.nextInt(4) + 1) == 1) {
            System.out.println("PRISON escaped.");
            returnVal = false;
        }
        System.out.println("PRISON NOT escaped.");
        table.discardCard(player.removeCardOnTable(this));
        return returnVal;
    }*/
}
