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
        String options = "";
        int playerIndex = 0;
        for (Player player : enemyPlayers) {
            if ((!player.getCardsOnHand().isEmpty()) || (!player.getCardsOnTable().isEmpty())) {
                playablePlayers.add(player);
                options += ((playerIndex + 1) + ". " + player.getName() + " Cards on hand: " +
                        player.getCardsOnHandNumber() + " Cards on table: " +
                        player.getCardsOnTableNumber()) + "\n";
                playerIndex++;
            }
        }
        if (playablePlayers.isEmpty()) {
            deck.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " can't be played now!");
            return;
        }

        Player targetPlayer = this.chooseTarget(playablePlayers, options, "Select player whose card you want to discard");

        ArrayList<Card> targetTable = targetPlayer.getCardsOnTable();
        ArrayList<Card> targetHand = targetPlayer.getCardsOnHand();

        if (targetTable.isEmpty()) {
            System.out.println(targetPlayer.getName() + " has no cards on table, hand chosen automatically.");
            this.throwAwayHand(targetPlayer, targetHand, deck);
        } else if (targetHand.isEmpty()) {
            System.out.println(targetPlayer.getName() + " has no cards on hand, table chosen automatically.");
            this.throwAwayTable(targetPlayer, targetTable, deck);
        } else {
            char charInput = ZKlavesnice.readChar("Do you want to pick a card from " + targetPlayer.getName() + "'s Hand or Table ? (T)able/(H)and");
            if (charInput == 'h' || charInput == 'H') {
                this.throwAwayHand(targetPlayer, targetHand, deck);
            } else if (charInput == 't' || charInput == 'T') {
                this.throwAwayTable(targetPlayer, targetTable, deck);
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
        if (cards.size() == 1) {
            System.out.println("You have only one option - automatically choosing card.");
            return cards.get(0);
        }
        return cards.get(this.pickIndex("What card do you want to throw away?", cards.size()));
    }

    private void throwAwayTable(Player targetPlayer, ArrayList<Card> cards, Deck deck) {
        Card throwCard;
        System.out.print(targetPlayer.cardsOnTableToString());
        throwCard = targetPlayer.removeCardsOnTable(this.pickCard(cards));
        deck.discardCard(throwCard);
        deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + "Lost " + throwCard.getName() + " card.");
    }

    private void throwAwayHand(Player targetPlayer, ArrayList<Card> cards, Deck deck) {
        Card throwCard;
        throwCard = targetPlayer.removeCardsOnHand(this.pickCard(cards));
        deck.discardCard(throwCard);
        deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + "Lost " + throwCard.getName() + " card.");
    }
}
