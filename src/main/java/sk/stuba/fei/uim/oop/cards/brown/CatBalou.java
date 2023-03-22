package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends BrownCard {
    private static final String CARD_NAME = "CatBalou";

    public CatBalou() {
        super(CARD_NAME);
    }


    @Override
    public void play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        ArrayList<Player> playablePlayers = new ArrayList<>();
        for (Player player:enemyPlayers) {
            if((!player.getCardsOnHand().isEmpty()) || (!player.getCardsOnTable().isEmpty())){
                playablePlayers.add(player);
            }
        }
        if(playablePlayers.isEmpty()){
            deck.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " can't be played now!");
            return;
        }

        String options = "";
        for(int i = 0; i < playablePlayers.size(); i++){
            options += ((i+1) + ". " + playablePlayers.get(i).getName() + " Cards on hand: " +
                    playablePlayers.get(i).getCardsOnHandNumber()+ " Cards on table: " +
                    playablePlayers.get(i).getCardsOnTableNumber()) + "\n";
        }
        Player targetPlayer = this.chooseTarget(playablePlayers, options,"Select player whose card you want to discard");

        ArrayList<Card> targetTable = targetPlayer.getCardsOnTable();
        ArrayList<Card> targetHand = targetPlayer.getCardsOnHand();

        if(targetTable.isEmpty()) {
            System.out.println(targetPlayer.getName() + " has no cards on table, hand chosen automatically.");
            deck.discardCard(targetPlayer.removeCardsOnHand(this.pickCard(targetPlayer.getCardsOnHand())));
            deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
        } else if(targetHand.isEmpty()) {
            System.out.println(targetPlayer.getName() + " has no cards on hand, table chosen automatically.");
            targetPlayer.cardsOnTableToString();
            deck.discardCard(targetPlayer.removeCardsOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
            deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
        } else {
            char charInput = ZKlavesnice.readChar("Do you want to pick a card from " + targetPlayer.getName() + "'s Hand or Table ? (T)able/(H)and");
            if(Character.toLowerCase(charInput) == 'h'){
                deck.discardCard(targetPlayer.removeCardsOnHand(this.pickCard(targetPlayer.getCardsOnHand())));
                deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
            } else if(charInput == 'T' || charInput == 't'){
                targetPlayer.cardsOnTableToString();
                deck.discardCard(targetPlayer.removeCardsOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
                deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
            } else {
                deck.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " can't be played now!");
                return;
            }
        }
        deck.discardCard(activePlayer.removeCardsOnHand(this));
    }
    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return true;
    }

    private Card pickCard(ArrayList<Card> cards) {
        if(cards.size() == 1) {
            System.out.println("You have only one option - automatically choosing card.");
            return cards.get(0);
        }
        return cards.get(this.pickIndex("What card do you want to throw away?", cards.size()));
    }
}
