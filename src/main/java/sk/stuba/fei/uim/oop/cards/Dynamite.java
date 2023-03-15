package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";
    private Random randomGenerator;
    private final Game game;

    public Dynamite(Game game) {
        super(CARD_NAME, Colour.BLUE);
        this.randomGenerator = new Random();
        this.game = game;
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        if (activePlayer.checkCardTable(Dynamite.class,deck)) {
            System.out.println("You can not have two blue cards of the same type on the table at once!");
            return false;
        }
        activePlayer.setCardsOnTable(activePlayer.removeCardOnHand(this));
        return false;
    }

    //TODO:Dynamite moving
    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        if ((randomGenerator.nextInt(8) + 1) == 1) {
            System.out.println("DYNAMITE exploded.");
            targetPlayer.removeLives(3);

            deck.discardCard(targetPlayer.removeCardOnTable(this));
            return true;
        }
        System.out.println("DYNAMIT moving.");
        Player prevPlayer = this.game.prevPlayer();
        prevPlayer.setCardsOnTable(targetPlayer.removeCardOnTable(this));
        return false;
    }

}
