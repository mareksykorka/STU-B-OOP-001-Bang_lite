package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;
import java.util.Random;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    private Random randomGenerator;
    public Prison(Random randomGenerator) {
        super(CARD_NAME, Colour.BLUE);
        this.randomGenerator = randomGenerator;
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        ArrayList<Player> playablePlayers = new ArrayList<Player>();
        for (Player player:enemyPlayers) {
            if(!player.checkCardTable(Prison.class)){
                playablePlayers.add(player);
            }
        }
        if(playablePlayers.isEmpty()){
            activePlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + "-> " + this.getName() + " can't be played now!");
            return false;
        }

        String options = "";
        for(int i = 0; i < playablePlayers.size(); i++) {
            options += (i + 1) + ". " + playablePlayers.get(i).getName() + " " + playablePlayers.get(i).isAliveToString() + "\n";
        }
        Player targetPlayer = this.chooseTarget(enemyPlayers, options, "Who will go to " + this.getName() + " ");
        targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> Went to " + this.getName() + ".");
        targetPlayer.setCardsOnTable(activePlayer.removeCardsOnHand(this));
        return true;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        deck.discardCard(targetPlayer.removeCardOnTable(this));
        if ((randomGenerator.nextInt(4) + 1) == 1) {
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + this.getName() + " escaped.");
            return true;
        }
        targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> " + this.getName() + " not escaped. Skipping turn.");
        return false;
    }
}
