package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";

    public Bang() {
        super(CARD_NAME, Colour.BROWN);
    }

    /*@Override
    public boolean play(Player player) {
        int targetIndex = this.choosePlayer(player);
        if(targetIndex == -1) {
            return false;
        }
        Player targetPlayer = game.getPlayerByIndex(targetIndex);
        for (Card card:targetPlayer.getCardsOnTable()) {
            if(card instanceof Barrel) {
                if(card.receivePlay(targetPlayer))
                    return true;
            }
        }
        for (Card card:targetPlayer.getCardsOnHand()) {
            if(card instanceof Missed) {
                return card.receivePlay(targetPlayer);
            }
        }
        if(!targetPlayer.removeLives(1)){
            game.playerDeath(targetPlayer);
        }
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        table.discardCard(player.removeCardOnHand(this));
        System.out.println("INDIANS evaded by BANG");
        return true;
    }*/

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        return false;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return false;
    }
}
