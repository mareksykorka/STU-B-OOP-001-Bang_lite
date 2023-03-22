package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.utility.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.deck.Deck;

import java.util.ArrayList;

public abstract class Card {
    private String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck);

    public abstract boolean receivePlay(Player targetPlayer, Deck deck);

    protected Player chooseTarget(ArrayList<Player> playablePlayers, String options, String question) {
        System.out.println("═════════════════ CHOOSE TARGET ═════════════════");
        if (playablePlayers.size() == 1) {
            System.out.println("You have only one enemy player - automatically choosing player " + playablePlayers.get(0).getName());
            return playablePlayers.get(0);
        }
        System.out.print(options);
        return playablePlayers.get(this.pickIndex(question, playablePlayers.size()));
    }

    protected int pickIndex(String message, int max) {
        return this.pickIndex(message, 1, max);
    }

    protected int pickIndex(String message, int min, int max) {
        if (min == max) {
            return max - 1;
        }

        int input;
        do {
            input = ZKlavesnice.readInt(message + " (" + min + "-" + max + "):");
        } while (input < min || input > max);

        return input - 1;
    }
}
