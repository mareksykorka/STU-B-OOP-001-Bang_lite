package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CatBalou";

    public CatBalou() {
        super(CARD_NAME);
    }

    @Override
    public String getName() {
        return TxtDef.ANSI_DARK_YELLOW + super.getName() + TxtDef.ANSI_RESET;
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        ArrayList<Player> playablePlayers = new ArrayList<>();
        for (Player player:enemyPlayers) {
            if((!player.getCardsOnHand().isEmpty()) || (!player.getCardsOnTable().isEmpty())){
                playablePlayers.add(player);
            }
        }
        if(playablePlayers.isEmpty()){
            activePlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " can't be played now!");
            return false;
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
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
        } else if(targetHand.isEmpty()) {
            System.out.println(targetPlayer.getName() + " has no cards on hand, table chosen automatically.");
            targetPlayer.showCardsOnTable();
            deck.discardCard(targetPlayer.removeCardOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
        } else {
            char charInput = ZKlavesnice.readChar("Do you want to pick a card from " + targetPlayer.getName() + "'s Hand or Table ? (T)able/(H)and");
            if(Character.toLowerCase(charInput) == 'h'){
                deck.discardCard(targetPlayer.removeCardsOnHand(this.pickCard(targetPlayer.getCardsOnHand())));
                targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
            } else if(charInput == 'T' || charInput == 't'){
                targetPlayer.showCardsOnTable();
                deck.discardCard(targetPlayer.removeCardOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
                targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " +  "Lost card.");
            } else {
                targetPlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " can't be played now!");
                return false;
            }
        }
        deck.discardCard(activePlayer.removeCardsOnHand(this));
        return true;
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
