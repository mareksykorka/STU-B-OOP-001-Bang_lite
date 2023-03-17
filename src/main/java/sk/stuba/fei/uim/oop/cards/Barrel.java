package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;
import java.util.Random;

public class Barrel extends Card {
    private static final String CARD_NAME = "Barrel";
    private Random randomGenerator;

    public Barrel() {
        super(CARD_NAME, Colour.BLUE);
        this.randomGenerator = new Random();
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        if (activePlayer.checkCardTable(Barrel.class)) {
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
        if (((randomGenerator.nextInt(4) + 1) == 1)) {
            System.out.println(TxtDef.CLI_INFO + targetPlayer.getName() + "-> BANG evaded by " + this.getName());
            return true;
        }
        System.out.println(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + this.getName() + " not effective." );
        return false;
    }
}
