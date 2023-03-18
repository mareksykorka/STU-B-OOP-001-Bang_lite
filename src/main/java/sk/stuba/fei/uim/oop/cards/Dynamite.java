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

    public Dynamite(Random randomGenerator, Game game) {
        super(CARD_NAME, Colour.BLUE);
        this.randomGenerator = randomGenerator;
        this.game = game;
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        if (activePlayer.checkCardTable(Dynamite.class,deck)) {
            activePlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + "-> You can not have two " +
                    this.getName() + "on the table at once!");
            return false;
        }
        activePlayer.setStatusMessage(TxtDef.CLI_INFO + activePlayer.getName() + "-> " + this.getName() + " placed on table.");
        activePlayer.setCardsOnTable(activePlayer.removeCardsOnHand(this));
        return false;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        if ((randomGenerator.nextInt(8)) == 0) {
            deck.discardCard(targetPlayer.removeCardOnTable(this));
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + this.getName() + " exploded.");
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + "3 Lives lost.");
            targetPlayer.removeLives(3);
            if(!targetPlayer.isAlive()){
                targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> Died, Killed by " + this.getName() + ".");
            }
            return true;
        }
        Player prevPlayer = this.game.prevPlayer();
        targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + this.getName() + " moving to " + prevPlayer.getName());
        prevPlayer.setCardsOnTable(targetPlayer.removeCardOnTable(this));
        return false;
    }
}
