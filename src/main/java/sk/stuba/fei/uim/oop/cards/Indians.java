package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians() {
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
        for (int i = 0; i < game.getNumberOfAllPlayers(); i++) {
            Player targetPlayer = game.getPlayerByIndex(i);
            if(targetPlayer.isAlive() && !(targetPlayer.equals(player))){
                ArrayList<Card> iteratingList = new ArrayList<Card>();
                iteratingList.addAll(targetPlayer.getCardsOnHand());
                for (Card card:iteratingList) {
                    if(card instanceof Bang) {
                        card.receivePlay(targetPlayer);
                    }
                }
                if(!targetPlayer.removeLives(1)){
                    game.playerDeath(targetPlayer);
                }
            }
        }
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }*/
}
