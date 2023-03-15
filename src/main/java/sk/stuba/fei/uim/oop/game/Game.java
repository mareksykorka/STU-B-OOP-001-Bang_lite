package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.utility.*;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> players;
    private Player activePlayer;
    private final Deck deck;

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
        this.deck = new Deck();

        ArrayList<Player> newPlayers = new ArrayList<>();
        for(int i = 0; i < playersCount; i++){
            newPlayers.add(new Player(ZKlavesnice.readString("What is the name of Player No."+(i+1)).trim(), deck));
            newPlayers.get(i).setCardsOnHand(this.deck.drawCards(4));
        }
        this.players = new ArrayList<>(newPlayers);
        //this.players.addAll(newPlayers);
        this.activePlayer = players.get(0);
        this.gameLoop();
    }

    private void gameLoop(){
        while(this.getNumberOfAlivePlayers() > 1){
            this.activePlayer.checkCardTable(Dynamite.class, this.deck);
            if(this.activePlayer.isAlive() /*&& this.activePlayer.checkCardTable(Prison.class, this.deck)*/){
                this.activePlayer.setCardsOnHand(this.deck.drawCards(2));
                this.playCards();
                this.throwCards();
            }
            this.activePlayer = this.nextPlayer();
        }
        System.out.println("The winner is " + this.getWinnerName());
    }

    private void showPlayingField(){
        System.out.println("═════════════════════ TABLE ═════════════════════");
        for(int i = 0; i < this.getNumberOfAllPlayers(); i++){
            System.out.println((i+1) + ". " + this.players.get(i).getName() + " " +
                    this.players.get(i).aliveStatus());

            if(this.players.get(i).isAlive()){
                System.out.println("\t--- Hand ---");
                System.out.println("\tCards on hand: " + this.players.get(i).getCardsOnHandNumber());
                System.out.println("\t--- Table ---");
                this.players.get(i).showCardsOnTable();
            }
        }
        System.out.println("═════════════════════ STATUS ════════════════════");
        System.out.println("Deck: " + deck.getNumberOfCardsInDeck());
        System.out.println("Discard: " + deck.getNumberOfCardsInDiscardPile());
        System.out.println("═════════════════════ PLAYER ════════════════════");
        System.out.println(TxtModif.ANSI_BOLD + "Active player: " + activePlayer.getName() + "\n" +
                            "Lives: " + activePlayer.aliveStatus() + TxtModif.ANSI_RESET);
        this.activePlayer.showCardsOnHand();
    }
    private void playCards() {
        int input;
        do {
            this.showPlayingField();

            input = ZKlavesnice.readInt("What card you want to play? If you want to end your turn just write '0'.");
            if (input != 0) {
                if(((input-1) >= 0) && ((input-1) < this.activePlayer.getCardsOnHandNumber())){
                    this.activePlayer.useCard((input-1), this.getAlivePlayers(), this.deck);
                } else {
                    System.out.println("You don't have the card " + input + "! Try Again!");
                }
            }
        } while ((this.getNumberOfAlivePlayers() > 1) && (input!=0) && (this.activePlayer.getCardsOnHandNumber()!=0));
    }
    private void throwCards() {
        while (!(this.activePlayer.isTurnEndAllowed())) {
            this.showPlayingField();

            int input = ZKlavesnice.readInt("What card do you want to throw away?");
            if (((input - 1) >= 0) && ((input - 1) < this.activePlayer.getCardsOnHandNumber())) {
                this.deck.discardCard(this.activePlayer.removeCardOnHand((input - 1)));
            } else {
                System.out.println("You don't have the card " + (input) + "! Try Again!");
            }
        }
    }

    public ArrayList<Player> getAlivePlayers(){
        ArrayList<Player> alivePlayers = new ArrayList<>();
        for (Player player: this.players) {
            if(player.isAlive()) {
                alivePlayers.add(player);
            }
        }
        return alivePlayers;
    }
    public int getNumberOfAlivePlayers(){
        return this.getAlivePlayers().size();
    }
    public int getNumberOfAllPlayers(){
        return this.players.size();
    }

    public int getPlayerIndex(Player unknownPlayer){
        return this.players.indexOf(unknownPlayer);
    }
    public Player nextPlayer(){
        int index = this.getPlayerIndex(this.activePlayer);
        Player nextPlayer;

        do {
            index++;
            if(index >= this.getNumberOfAllPlayers()){
                index = 0;
            }
            nextPlayer = this.players.get(index);
        } while (!(nextPlayer.isAlive()));

        return nextPlayer;
    }
    public Player prevPlayer(){
        int index = this.getPlayerIndex(this.activePlayer);
        Player prevPlayer;

        do {
            index--;
            if(index < 0){
                index = (this.getNumberOfAllPlayers()-1);
            }
            prevPlayer = this.players.get(index);
        } while (!(prevPlayer.isAlive()));

        return prevPlayer;
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
}
