package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CatBalou";

    public CatBalou() {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        ArrayList<Player> playablePlayers = new ArrayList<Player>();
        for (Player player:enemyPlayers) {
            if((!player.getCardsOnHand().isEmpty()) || (!player.getCardsOnTable().isEmpty())){
                playablePlayers.add(player);
            }
        }
        if(playablePlayers.isEmpty()){
            activePlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + "-> " + this.getName() + " can't be played now!");
            return false;
        }

        String options = "";
        for(int i = 0; i < playablePlayers.size(); i++){
            options += ((i+1) + ". " + playablePlayers.get(i).getName() + " Cards on hand: " +
                    playablePlayers.get(i).getCardsOnHandNumber()+ " Cards on table: " +
                    playablePlayers.get(i).getCardsOnTableNumber()) + "\n";
        }
        Player targetPlayer = this.chooseTarget(playablePlayers, options,"Select player which`s card you want to discard");

        ArrayList<Card> targetTable = targetPlayer.getCardsOnTable();
        ArrayList<Card> targetHand = targetPlayer.getCardsOnHand();

        if(targetTable.isEmpty()) {
            deck.discardCard(targetPlayer.removeCardsOnHand(this.pickCard(targetPlayer.getCardsOnHand())));
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
        } else if(targetHand.isEmpty()) {
            deck.discardCard(targetPlayer.removeCardOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
        } else {
            char charInput = ZKlavesnice.readChar("Do you want to pick a card from " + targetPlayer.getName() + "'s Hand or Table ? (T)able/(H)and");
            if(Character.toLowerCase(charInput) == 'h'){
                deck.discardCard(targetPlayer.removeCardsOnHand(this.pickCard(targetPlayer.getCardsOnHand())));
                targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
            } else if(charInput == 'T' || charInput == 't'){
                deck.discardCard(targetPlayer.removeCardOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
                targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
            } else {
                targetPlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + "-> " + this.getName() + " can't be played now!");
                return false;
            }
        }
        deck.discardCard(activePlayer.removeCardsOnHand(this));
        return true;
    }

    private Card pickCard(ArrayList<Card> cards) {
        if(cards.size() == 1){
            System.out.println("You have only one option - automatically choosing card.");
            return cards.get(0);
        }
        return cards.get(this.pickIndex("What card do you want to throw away?", cards.size()));
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return true;
    }
}
