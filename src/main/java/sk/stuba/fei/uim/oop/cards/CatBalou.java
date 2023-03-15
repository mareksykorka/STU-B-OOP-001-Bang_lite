package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CatBalou";

    public CatBalou() {
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

    //TODO: Rework CatBalou to better logic -- BROKEN
    /*@Override
    public boolean play(Player player) {
        int targetIndex = this.choosePlayer(player);
        if(targetIndex == -1) {
            return false;
        }
        Player targetPlayer = game.getPlayerByIndex(targetIndex);
        char charInput = ZKlavesnice.readChar("Do you want to pick a card from "+ targetPlayer.getName() + "'s Hand or Table ? (T)able/(H)and");
        if(charInput == 'H' || charInput == 'h'){
            int intInput = -1;
            while (!(intInput >= 0 && intInput < targetPlayer.getCardsOnHandNumber())) {
                intInput =  ZKlavesnice.readInt("Pick a card you wanna pick. " + targetPlayer.getName() + " has " + targetPlayer.getCardsOnHandNumber() + "cards on hand.");
                intInput--;
                if((intInput >= 0) && (intInput < targetPlayer.getCardsOnHandNumber())){
                    table.discardCard(targetPlayer.removeCardOnHand(intInput));
                } else {
                    System.out.println("Player does not have the card "+ (intInput+1) + "! Try Again!");
                }
            }
            return true;
        }
        if(charInput == 'T' || charInput == 't'){
            int intInput = -1;
            while (!(intInput >= 0 && intInput < targetPlayer.getCardsOnTableNumber())) {
                intInput =  ZKlavesnice.readInt("Pick a card you wanna pick. " + targetPlayer.getName() + " has " + targetPlayer.getCardsOnTableNumber() + "cards on hand.");
                intInput--;
                if((intInput >= 0) && (intInput < targetPlayer.getCardsOnTableNumber())){
                    table.discardCard(targetPlayer.removeCardOnTable(intInput));
                } else {
                    System.out.println("Player does not have the card "+ (intInput+1) + "! Try Again!");
                }
            }
            return true;
        }
        return false;

    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }*/
}
