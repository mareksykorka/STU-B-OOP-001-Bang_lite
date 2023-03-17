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
            System.out.println("You can not play this card.");
            return false;
        }

        Player targetPlayer = this.chooseTarget(playablePlayers);

        ArrayList<Card> targetTable = targetPlayer.getCardsOnTable();
        ArrayList<Card> targetHand = targetPlayer.getCardsOnHand();

        if(targetTable.isEmpty()) {
            deck.discardCard(targetPlayer.removeCardOnHand(this.pickCard(targetPlayer.getCardsOnHand())));
            this.printGameStatus(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
        } else if(targetHand.isEmpty()) {
            deck.discardCard(targetPlayer.removeCardOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
            this.printGameStatus(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
        } else {
            char charInput = ZKlavesnice.readChar("Do you want to pick a card from " + targetPlayer.getName() + "'s Hand or Table ? (T)able/(H)and");
            if(Character.toLowerCase(charInput) == 'h'){
                deck.discardCard(targetPlayer.removeCardOnHand(this.pickCard(targetPlayer.getCardsOnHand())));
                this.printGameStatus(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
            } else if(charInput == 'T' || charInput == 't'){
                deck.discardCard(targetPlayer.removeCardOnTable(this.pickCard(targetPlayer.getCardsOnTable())));
                this.printGameStatus(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " +  " Lost card.");
            } else {
                this.printGameStatus(TxtDef.CLI_WARNING + activePlayer.getName() + "-> " + this.getName() + " can't be played now!");
                return false;
            }
        }
        deck.discardCard(activePlayer.removeCardOnHand(this));
        return true;
    }

    private Card pickCard(ArrayList<Card> cards) {
        return cards.get(this.pickIndex("What card do you want to throw away?", cards.size()));
    }

    private Player chooseTarget(ArrayList<Player> playablePlayers) {
        if(playablePlayers.size() == 1){
            return playablePlayers.get(0);
        }
        System.out.println("═════════════════ CHOOSE TARGET ═════════════════");

        for(int i = 0; i < playablePlayers.size(); i++){
            System.out.println((i+1) + ". " + playablePlayers.get(i).getName() + " Cards on hand: " +
                    playablePlayers.get(i).getCardsOnHandNumber()+ " Cards on table: " + playablePlayers.get(i).getCardsOnTableNumber());
        }
        return playablePlayers.get(this.pickIndex("Select player which`s card you want to discard.", playablePlayers.size()));
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return true;
    }
}
