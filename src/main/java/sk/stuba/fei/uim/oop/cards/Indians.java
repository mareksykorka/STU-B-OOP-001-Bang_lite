package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians() {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        deck.discardCard(activePlayer.removeCardOnHand(this));
        this.printGameStatus();
        for (Player targetPlayer:enemyPlayers) {
            if(!targetPlayer.checkCardHand(Bang.class,deck)) {
                System.out.println(TxtDef.CLI_INFO + targetPlayer.getName() + "-> Life lost.");
                targetPlayer.removeLives(1);
            }
        }
        return true;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return true;
    }
}
