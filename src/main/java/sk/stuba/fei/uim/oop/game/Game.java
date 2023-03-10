package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Game {
    private Player[] players;
    private Player activePlayer;
    private int indexOfActivePlayer;
    private Table table;

    public Game() {
        int playersCount = 0;
        while(playersCount < 2 || playersCount  > 4){
            playersCount = ZKlavesnice.readInt("Enter the number of players (2 -> 4):");
            if(playersCount < 2 || playersCount  > 4){
                System.out.println("You have entered a wrong number of players, Try Again.");
            }
        }
        this.table = new Table();
        this.indexOfActivePlayer = 0;
        this.players = new Player[playersCount];
        for(int i = 0; i < playersCount; i++){
            this.players[i] = new Player(ZKlavesnice.readString("What is the name of Player No."+(i+1)));
            this.players[i].setCardsOnHand(table.drawCards(4));
        }
        this.activePlayer = players[indexOfActivePlayer];
        this.gameLoop();
    }

    private void gameLoop(){
        while(this.getNumberOfAlivePlayers() > 1){
            if(this.activePlayer.isAlive()) {
                // First Phase
                this.activePlayer.setCardsOnHand(this.table.drawCards(2));
                showPlayingField();
                // Second Phase
                while (this.askForAction()) {
                    showPlayingField();
                }
                // Third phase
                while (this.checkPlayerCards()) {
                    showPlayingField();
                }
            }
            this.nextPlayer();
        }
        System.out.println("The winner is " + this.getWinnerName());
    }

    private void showPlayingField(){
        System.out.println("Table:");
        for(int i = 0; i < this.players.length; i++){
            System.out.println((i+1) + ". " + players[i].getName() + " " + players[i].isAlive("Full"));
            players[i].showCardsOnTable();
        }
        System.out.println(""+ activePlayer.getName() + "`s hand:");
        activePlayer.showCardsOnHand();
    }
    private boolean askForAction() {
        int input = ZKlavesnice.readInt("What card you want to play? If you want to end your turn just write '0'.");
        if (input != 0) {
            // TODO: Playcard method
            //this.activePlayer;
            return true;
        }
        return false;
    }
    private boolean checkPlayerCards() {
        if(!(activePlayer.isTurnEndAllowed())) {
            int input = ZKlavesnice.readInt("What card do you want to throw away?");
            // TODO: ThrowAway method
            //this.activePlayer;
            return true;
        }
        return false;
    }

    private int getNumberOfAlivePlayers(){
        int alivePlayers = 0;
        for (Player player:players) {
            if(player.isAlive()) {
                alivePlayers++;
            }
        }
        return alivePlayers;
    }
    private void nextPlayer(){
        indexOfActivePlayer++;
        if(indexOfActivePlayer >= this.players.length){
            indexOfActivePlayer = 0;
        }
        activePlayer = players[indexOfActivePlayer];
    }
    private String getWinnerName() {
        String winner = "";
        for (Player player:players) {
            if(player.isAlive()) {
                winner = player.getName();
            }
        }
        return winner;
    }
}
