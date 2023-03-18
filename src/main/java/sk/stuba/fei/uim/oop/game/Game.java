package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.utility.*;
import sk.stuba.fei.uim.oop.deck.*;
import sk.stuba.fei.uim.oop.player.*;
import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Player activePlayer;
    private Deck deck;

    public Game() {
        System.out.println("══════════════════ BANG \"Lite\" ══════════════════\n" +
                            "Welcome to the BANG \"Lite\",\n" +
                            "Goal of the game is to be the last one alive.\n" +
                            "═════════════════════════════════════════════════");
        int playersCount = 0;
        while(playersCount < 2 || playersCount  > 4){
            playersCount = ZKlavesnice.readInt("How many players will be playing ? (2-4)");
            if(playersCount < 2 || playersCount  > 4){
                System.out.println("You've entered a wrong number of players, Try Again.");
            }
        }
        this.deck = new Deck(this);

        ArrayList<Player> newPlayers = new ArrayList<>();
        for(int i = 0; i < playersCount; i++){
            newPlayers.add(new Player(ZKlavesnice.readString("What is the name of Player No."+(i+1)).trim(), deck));
            newPlayers.get(i).setCardsOnHand(this.deck.drawCards(4));
        }
        this.players = new ArrayList<>(newPlayers);
        this.activePlayer = players.get(0);
        this.gameLoop();
    }

    private ArrayList<Player> getEnemyPlayers() {
        ArrayList<Player> alivePlayers = new ArrayList<>();
        for (Player player : this.players) {
            if(player.isAlive() && !player.equals(this.activePlayer)) {
                alivePlayers.add(player);
            }
        }
        return alivePlayers;
    }
    private int getNumberOfAlivePlayers() {
        ArrayList<Player> alivePlayers = new ArrayList<>();
        for (Player player : this.players) {
            if(player.isAlive()) {
                alivePlayers.add(player);
            }
        }
        return alivePlayers.size();
    }
    private int getNumberOfAllPlayers() {
        return this.players.size();
    }
    private String getWinnerName() {
        String winner = "";
        for (Player player : this.players) {
            if(player.isAlive()) {
                winner = player.getName();
            }
        }
        return winner;
    }

    private void gameLoop() {
        while(this.getNumberOfAlivePlayers() > 1) {
            this.activePlayer.checkCardTable(Dynamite.class, this.deck);
            if(this.activePlayer.isAlive()) {
                if(this.activePlayer.checkCardTable(Prison.class, this.deck)) {
                    this.activePlayer.setCardsOnHand(this.deck.drawCards(2));
                    this.playCards();
                    this.throwCards();
                }
            }
            this.activePlayer = this.nextPlayer();
        }
        this.showPlayingField();
        System.out.println(TxtDef.ANSI_BOLD + TxtDef.ANSI_BRIGHT_YELLOW + "═════════════════════ WINNER ════════════════════");
        System.out.println(TxtDef.ANSI_BOLD + TxtDef.ANSI_BRIGHT_YELLOW + "The winner is " + this.getWinnerName());
        System.out.println(TxtDef.ANSI_BOLD + TxtDef.ANSI_BRIGHT_YELLOW + "═════════════════════ WINNER ════════════════════");
    }

    private void showPlayingField() {
        System.out.println(TxtDef.CLI_CLS + "══════════════════ GAME STATUS ══════════════════");
        for (Player player : this.players) {
            System.out.print(player.getStatusMessage());
        }
        System.out.print(this.deck.getStatusMessage());
        System.out.println("═════════════════════ TABLE ═════════════════════");
        for(int i = 0; i < this.getNumberOfAllPlayers(); i++) {
            System.out.println(TxtDef.ANSI_BOLD + (i+1) + ". " + this.players.get(i).getName() +
                    TxtDef.ANSI_BOLD + " " + this.players.get(i).isAliveToString());
            if(this.players.get(i).isAlive()) {
                System.out.println("\t--- Hand ---");
                System.out.println("\tCards on hand: " + this.players.get(i).getCardsOnHandNumber());
                System.out.print("\t--- Table ---\n" + this.players.get(i).showCardsOnTable());
            }
        }
        System.out.println("═════════════════ ACTIVE PLAYER ═════════════════");
        System.out.println(TxtDef.ANSI_BOLD + "Active player is: " + activePlayer.getName() + " " + TxtDef.ANSI_RESET +
                activePlayer.isAliveToString() + TxtDef.ANSI_RESET);
        System.out.println("You have these cards on the hand:");
        System.out.print(this.activePlayer.cardsOnHandToString());
    }
    private void playCards() {
        int input;
        do {
            this.showPlayingField();
            input = ZKlavesnice.readInt("What card you want to play? If you want to end your turn just write '0'.");
            if (input != 0) {
                if(((input-1) >= 0) && ((input-1) < this.activePlayer.getCardsOnHandNumber())) {
                    this.activePlayer.useCard((input-1), this.getEnemyPlayers(), this.deck);
                } else {
                    deck.setStatusMessage(TxtDef.CLI_WARNING + "You don't have the card " + input + "! Try Again!");
                }
            }
        } while ((this.getNumberOfAlivePlayers() > 1) && (input!=0) && (this.activePlayer.getCardsOnHandNumber()!=0));
    }
    private void throwCards() {
        if(this.getNumberOfAlivePlayers() > 1) {
            while (!(this.activePlayer.isTurnEndAllowed())) {
                this.showPlayingField();
                int input = ZKlavesnice.readInt("What card do you want to throw away?");
                if (((input - 1) >= 0) && ((input - 1) < this.activePlayer.getCardsOnHandNumber())) {
                    this.deck.discardCard(this.activePlayer.removeCardsOnHand(this.activePlayer.getCardsOnHand().get(input - 1)));
                } else {
                    System.out.println("You don't have the card " + (input) + "! Try Again!");
                }
            }
        }
    }

    public Player nextPlayer() {
        int index = this.players.indexOf(this.activePlayer);
        Player nextPlayer;

        do {
            index++;
            if(index >= this.getNumberOfAllPlayers()) {
                index = 0;
            }
            nextPlayer = this.players.get(index);
        } while (!(nextPlayer.isAlive()));

        return nextPlayer;
    }
    public Player prevPlayer() {
        int index = this.players.indexOf(this.activePlayer);
        Player prevPlayer;

        do {
            index--;
            if(index < 0) {
                index = (this.getNumberOfAllPlayers()-1);
            }
            prevPlayer = this.players.get(index);
        } while (!(prevPlayer.isAlive()));

        return prevPlayer;
    }
}
