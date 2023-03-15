package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    private Random randomGenerator;
    public Prison() {
        super(CARD_NAME, Colour.BLUE);
        this.randomGenerator = new Random();
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        ArrayList<Player> playablePlayers = new ArrayList<Player>();
        for (Player player:enemyPlayers) {
            if(!player.checkCardTable(Prison.class)){
                playablePlayers.add(player);
            }
        }
        if(playablePlayers.size() == 0){
            return false;
        }
        Player targetPlayer = chooseTarget(playablePlayers);
        targetPlayer.setCardsOnTable(activePlayer.removeCardOnHand(this));
        return true;
    }

    private Player chooseTarget(ArrayList<Player> playablePlayers) {
        if(playablePlayers.size() == 1){
            return playablePlayers.get(0);
        }
        System.out.println("═════════════════ CHOOSE TARGET ═════════════════");
        for(int i = 0; i < playablePlayers.size(); i++) {
            System.out.println((i + 1) + ". " + playablePlayers.get(i).getName() + " " +
                    playablePlayers.get(i).aliveStatus());
        }
        return playablePlayers.get(this.pickIndex("Select player you want to use the BANG on", playablePlayers.size()));
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        deck.discardCard(targetPlayer.removeCardOnTable(this));

        if ((randomGenerator.nextInt(4) + 1) == 1) {
            System.out.println("PRISON escaped.");
            return true;
        }
        System.out.println("PRISON NOT escaped.");
        return false;
    }
}
