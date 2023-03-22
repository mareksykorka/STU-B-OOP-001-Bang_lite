package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;
import java.util.Random;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "Prison";

    public Prison(Random randomGenerator) {
        super(CARD_NAME, randomGenerator);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        ArrayList<Player> playablePlayers = new ArrayList<>();
        String options = "";
        int playerIndex = 0;
        for (Player player : enemyPlayers) {
            if (this.canBePlacedOnTable(player.getCardsOnTable(), Prison.class)) {
                playablePlayers.add(player);
                options += (playerIndex + 1) + ". " + player.getName() + " " + player.isAliveToString() + "\n";
                playerIndex++;
            }
        }
        if (playablePlayers.isEmpty()) {
            deck.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " can't be played now!");
        } else {
            Player targetPlayer = this.chooseTarget(playablePlayers, options, "Who will go to " + this.getName());
            deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> Went to " + this.getName() + ".");
            targetPlayer.setCardsOnTable(activePlayer.removeCardsOnHand(this));
        }
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        deck.discardCard(targetPlayer.removeCardsOnTable(this));
        if (this.checkChance((double) 1 / 4)) {
            deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + this.getName() + " escaped.");
            return false;
        }
        deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + this.getName() + " not escaped. Skipping turn.");
        return true;
    }
}
