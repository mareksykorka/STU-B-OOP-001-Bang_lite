package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Game {
    private final Player[] players; //Je final v pohode ?
    private Player activePlayer;
    private int indexOfActivePlayer;
    private Table table;

    public Game() {
        System.out.println("══════════════════ BANG \"Lite\" ══════════════════\n" +
                            "Welcome to the BANG \"Lite\",\n" +
                            "It is simplified version of the card game BANG,\n" +
                            "the players are in the wild west, they all fight\n" +
                            "against each other. Goal of the game is to be the\n" +
                            "last one alive.\n" +
                            "═════════════════════════════════════════════════");
        int playersCount = 0;
        while(playersCount < 2 || playersCount  > 4){
            playersCount = ZKlavesnice.readInt("How many players will be playing ? (2-4)");
            if(playersCount < 2 || playersCount  > 4){
                System.out.println("You've entered a wrong number of players, Try Again.");
            }
        }
        this.table = new Table(this);
        this.indexOfActivePlayer = 0;
        this.players = new Player[playersCount];
        for(int i = 0; i < playersCount; i++){
            this.players[i] = new Player(ZKlavesnice.readString("What is the name of Player No."+(i+1)).trim());
            this.players[i].setCardsOnHand(table.drawCards(4));
        }
        this.activePlayer = players[indexOfActivePlayer];
        System.out.println("══════════════════ BANG \"Lite\" ══════════════════\n");
        this.gameLoop();
    }

    //TODO: Rework Game Loop - Break it into pieces
    private void gameLoop(){
        while(this.getNumberOfAlivePlayers() > 1){
            // First Phase - Automatic
            this.activePlayer.checkDynamit();
            if(this.activePlayer.isAlive()) {
                if(!this.activePlayer.checkPrison()){
                    this.activePlayer.setCardsOnHand(this.table.drawCards(2));
                    // Second Phase
                    do {
                        if(!(this.getNumberOfAlivePlayers() > 1))
                            break;
                        showPlayingField();
                    } while (this.askForAction());
                    // Third phase
                    do {
                        if(!(this.getNumberOfAlivePlayers() > 1))
                            break;
                        showPlayingField();
                    } while (this.checkPlayerCards());
                }
            }
            this.nextActivePlayer();
        }
        System.out.println("The winner is " + this.getWinnerName());
    }

    private void showPlayingField(){
        System.out.println("═════════════════════ TABLE ═════════════════════");
        for(int i = 0; i < this.players.length; i++){
            System.out.println((i+1) + ". " + this.players[i].getName() + " " +
                                this.players[i].isAlive(Player.PrintType.FULL));
            //TODO: Better Player card status printout
            System.out.println("\t--- Hand ---");
            System.out.println("\tNo. cards on hand: " + players[i].getCardsOnHandNumber());
            System.out.println("\t--- Table ---");
            players[i].showCardsOnTable();
        }
        System.out.println("═════════════════════ STATUS ════════════════════");
        System.out.println("Deck: " + table.getNumberOfCardsInDeck());
        System.out.println("Discard: " + table.getNumberOfCardsInDiscardPile());
        System.out.println("═════════════════════ PLAYER ════════════════════");
        System.out.println("\u001B[1mActive player: " + activePlayer.getName() + "\n" +
                            "Lives: " + activePlayer.isAlive(Player.PrintType.SIMPLE) +
                            "\u001B[0m");
        activePlayer.showCardsOnHand();
    }
    private boolean askForAction() {
        int input = ZKlavesnice.readInt("What card you want to play? If you want to end your turn just write '0'.");
        if (input != 0) {
            input -= 1;
            if((input >= 0) && (input < this.activePlayer.getCardsOnHandNumber())){
                this.activePlayer.useCard(input, this.table);
            } else {
                System.out.println("You don't have the card "+ (input+1) + "! Try Again!");
            }
            return true;
        }
        return false;
    }
    //TODO: rework check with isInstanceof not instance of
    private boolean checkPlayerCards() {
        if(!(activePlayer.isTurnEndAllowed())) {
            int input = ZKlavesnice.readInt("What card do you want to throw away?");
            input -= 1;
            if((input >= 0) && (input < this.activePlayer.getCardsOnHandNumber())){
                table.discardCard(this.activePlayer.removeCardOnHand(input));
            } else {
                System.out.println("You don't have the card "+ (input+1) + "! Try Again!");
            }
            return true;
        }
        return false;
    }


    public int getNumberOfAlivePlayers(){
        int alivePlayers = 0;
        for (Player player:players) {
            if(player.isAlive()) {
                alivePlayers++;
            }
        }
        return alivePlayers;
    }
    public int getNumberOfAllPlayers(){
        return players.length;
    }
    // TODO: See decluter code from useless methods.
    public int getIndexOfActivePlayer() {
        return indexOfActivePlayer;
    }
    public Player getPlayerByIndex(int index) {
        return players[index];
    }


    public int nextPlayer(int index){
        Player nextPlayer;

        do {
            index++;
            if(index >= this.getNumberOfAllPlayers()){
                index = 0;
            }
            nextPlayer = this.players[index];
        } while (!(nextPlayer.isAlive()));

        return index;
    }
    public int nextPlayer() {
        return nextPlayer(this.indexOfActivePlayer);
    }
    private void nextActivePlayer(){
        indexOfActivePlayer = nextPlayer();
        this.activePlayer = this.players[indexOfActivePlayer];
    }
    public int prevPlayer(int index){
        Player prevPlayer;

        do {
            index--;
            if(index <= 0){
                index = (this.getNumberOfAllPlayers()-1);
            }
            prevPlayer = this.players[index];
        } while (!(prevPlayer.isAlive()));

        return index;
    }
    public int prevPlayer() {
        return prevPlayer(this.indexOfActivePlayer);
    }


    public void playerDeath(Player player){
        this.table.discardCards(player.removeCardOnTable());
        this.table.discardCards(player.removeCardOnHand());
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
