package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Game {
    private Player[] players;
    private int activePlayer;
    private Table table;

    public Game() {
        int playersCount = 0;
        while(playersCount < 2 || playersCount  > 4){
            playersCount = ZKlavesnice.readInt("Enter the number of players (2 -> 4):");
            if(playersCount < 2 || playersCount  > 4){
                System.out.println("You have entered a wrong number of players, Try Again.");
            }
        }
        this.players = new Player[playersCount];
        for(int i = 0; i < playersCount; i++){
            this.players[i] = new Player(ZKlavesnice.readString("What is the name of Player No."+(i+1)));
        }
        this.activePlayer = 0;
        this.table = new Table();
        this.gameLoop();
    }

    private void gameLoop(){

    }
}
