package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

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
            this.printGameStatus(TxtDef.CLI_WARNING + activePlayer.getName() + "-> You can not have two " +
                    this.getName() + "on the table at once!");
            return false;
        }
        this.printGameStatus(TxtDef.CLI_INFO + activePlayer.getName() + "-> " + this.getName() + " placed on table.");
        activePlayer.setCardsOnTable(activePlayer.removeCardOnHand(this));
        return false;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        if ((randomGenerator.nextInt(8)) == 0) {
            this.printGameStatus(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + this.getName() + " exploded.");
            targetPlayer.removeLives(3);

            deck.discardCard(targetPlayer.removeCardOnTable(this));
            return true;
        }
        this.printGameStatus(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + this.getName() + " moving to previous player.");
        Player prevPlayer = this.game.prevPlayer();
        prevPlayer.setCardsOnTable(targetPlayer.removeCardOnTable(this));
        return false;
    }
}
