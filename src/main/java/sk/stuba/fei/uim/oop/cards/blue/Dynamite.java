package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;
import java.util.Random;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "Dynamite";

    public Dynamite(Random randomGenerator) {
        super(CARD_NAME, randomGenerator);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        if (this.canBePlacedOnTable(activePlayer.getCardsOnTable(), Dynamite.class)) {
            deck.setStatusMessage(TxtDef.CLI_INFO + activePlayer.getName() + " -> " + this.getName() + " placed on table.");
            activePlayer.setCardsOnTable(activePlayer.removeCardsOnHand(this));
        } else {
            deck.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> You can not have two " + this.getName() + "'s on the table at once!");
        }
    }

    @Override
    public boolean receivePlay(Player targetPlayer, ArrayList<Player> alivePlayers, Deck deck) {
        if (this.checkChance((double) 1 / 8)) {
            deck.discardCard(targetPlayer.removeCardOnTable(this));
            deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + this.getName() + " exploded.");
            deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + "3 Lives lost.");
            targetPlayer.removeLives(3);
            if (!targetPlayer.isAlive()) {
                deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> Died, Killed by " + this.getName() + ".");
            }
            return true;
        }
        Player prevPlayer = targetPlayer.prevPlayer(alivePlayers);
        deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + this.getName() + " moving to " + prevPlayer.getName());
        prevPlayer.setCardsOnTable(targetPlayer.removeCardOnTable(this));
        return false;
    }
}
