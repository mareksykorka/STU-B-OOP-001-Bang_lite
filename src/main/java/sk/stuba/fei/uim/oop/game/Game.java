package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Game {
    private Player[] players;
    private int activePlayer;
    private Table table;

    public Game() {
        this.activePlayer = 0;
        int playersCount = 0;
        while(playersCount < 2 || playersCount  > 4){
            playersCount = ZKlavesnice.readInt("Enter the number of players (2 -> 4):");
            if(playersCount < 2 || playersCount  > 4){
                System.out.println("You have entered a wrong number of players, Try Again.");
            }
        }

        this.table = new Table();
        this.players = new Player[playersCount];
        for(int i = 0; i < playersCount; i++){
            this.players[i] = new Player(ZKlavesnice.readString("What is the name of Player No."+(i+1)), this.table);
        }
        this.gameLoop();
    }

    private void gameLoop(){
        while(this.getNumberOfAlivePlayers() > 1){
            if(this.players[activePlayer].isAlive()) {
                // First Phase
                this.players[activePlayer].setCardsOnHand(this.table.drawCards(2));
                this.showPlayingField();
                // Second Phase
                this.askForAction();
                // Third phase
                this.checkPlayerCards();
            }
            this.nextPlayer();
        }
    }

    private void checkPlayerCards() {
        while (players[activePlayer].checkCards()) {
            int input = ZKlavesnice.readInt(" --- Ktoru kartu chces zahodit? --- ");
            players[activePlayer].throwAway(input, table);
        }

    }

    private void showPlayingField(){
        System.out.println(" --- Table --- ");
        for(int i = 0; i < this.players.length; i++){
            System.out.println((i+1) + ". " + players[i].getName() + " " + players[i].showAliveStatus());
            players[i].showCarsOnTable();
        }
        System.out.println(" --- "+ players[activePlayer].getName() + "`s hand --- ");
        players[activePlayer].showCarsOnHand();
    }

    private void askForAction(){
        boolean ask = true;
        while(ask) {
            String input = ZKlavesnice.readString(" --- Ktoru kartu chces zahrat?/Chces ukoncit tah (K)oniec? --- ");
            if (input.toLowerCase().equals("k")) {
                ask = false;
            } else {
                int inputInt = Integer.parseInt(input);
                this.players[activePlayer].playCard(inputInt, table);
            }
        }
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
        activePlayer++;
        if(activePlayer >= this.players.length)
            activePlayer = 0;
    }
}
