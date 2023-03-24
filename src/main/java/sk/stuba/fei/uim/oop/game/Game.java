package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Player activePlayer;
    private Deck deck;

    public Game() {
        System.out.println("══════════════════ BANG \"Lite\" ══════════════════\n" +
                "Welcome to the BANG \"Lite\",\n" +
                "Goal of the game is to be the " + TxtDef.ANSI_BOLD + "last one alive.\n" + TxtDef.ANSI_RESET +
                "═════════════════════════════════════════════════");
        this.players = new ArrayList<>();
        this.deck = new Deck(this.players);
        this.initializePlayers();
        this.activePlayer = players.get(0);
        this.gameLoop();
    }

    private ArrayList<Player> getEnemyPlayers() {
        ArrayList<Player> enemyPlayers = new ArrayList<>();
        for (Player player : this.players) {
            if (player.isAlive() && !player.equals(this.activePlayer)) {
                enemyPlayers.add(player);
            }
        }
        return enemyPlayers;
    }

    private int getNumberOfAlivePlayers() {
        ArrayList<Player> alivePlayers = new ArrayList<>();
        for (Player player : this.players) {
            if (player.isAlive()) {
                alivePlayers.add(player);
            }
        }
        return alivePlayers.size();
    }

    private void initializePlayers() {
        int playersCount = 0;
        while (playersCount < 2 || playersCount > 4) {
            playersCount = ZKlavesnice.readInt("How many players will be playing ? (2-4)");
            if (playersCount < 2 || playersCount > 4) {
                System.out.println("You've entered a wrong number of players. Try Again!");
            }
        }

        for (int i = 0; i < playersCount; i++) {
            this.players.add(new Player(ZKlavesnice.readString("What is the name of Player No." + (i + 1)).trim()));
            this.players.get(i).setCardsOnHand(this.deck.drawCards(4, false));
        }
    }

    private void gameLoop() {
        while (this.getNumberOfAlivePlayers() > 1) {
            this.activePlayer.checkCardTable(Dynamite.class, this.deck);
            if (this.activePlayer.isAlive()) {
                if (!this.activePlayer.checkCardTable(Prison.class, this.deck)) {
                    this.activePlayer.setCardsOnHand(this.deck.drawCards(2, false));
                    this.playCards();
                    this.throwCards();
                }
            }
            this.activePlayer = this.activePlayer.nextPlayer(this.players);
        }
        this.showPlayingField();
        this.showWinner();
    }

    private void showPlayingField() {
        System.out.println(TxtDef.CLI_CLS + "══════════════════ GAME STATUS ══════════════════");
        System.out.print(this.deck.getStatusMessage());
        System.out.println("═════════════════════ TABLE ═════════════════════");
        for (int i = 0; i < this.players.size(); i++) {
            System.out.println(TxtDef.ANSI_BOLD + (i + 1) + ". " + this.players.get(i).getName() +
                    TxtDef.ANSI_BOLD + " " + this.players.get(i).isAliveToString());
            if (this.players.get(i).isAlive()) {
                System.out.println("\t--- Hand ---");
                System.out.println("\tCards on hand: " + this.players.get(i).getCardsOnHandNumber());
                System.out.print("\t--- Table ---\n" + this.players.get(i).cardsOnTableToString());
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
            input = ZKlavesnice.readInt("What card do you want to play? If you want to end your turn just write '0'.");
            if (input != 0) {
                if (((input - 1) >= 0) && ((input - 1) < this.activePlayer.getCardsOnHandNumber())) {
                    this.activePlayer.useCard((input - 1), this.getEnemyPlayers(), this.deck);
                } else {
                    deck.setStatusMessage(TxtDef.CLI_WARNING + "You don't have the card " + input + "! Try Again!");
                }
            }
        } while ((this.getNumberOfAlivePlayers() > 1) && (input != 0) && (this.activePlayer.getCardsOnHandNumber() != 0));
    }

    private void throwCards() {
        if (this.getNumberOfAlivePlayers() > 1) {
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

    private void showWinner() {
        String winner = "";
        for (Player player : this.players) {
            if (player.isAlive()) {
                winner = player.getName();
            }
        }
        System.out.println(TxtDef.ANSI_BOLD + TxtDef.ANSI_BRIGHT_YELLOW + "═════════════════════ WINNER ════════════════════\n" +
                "The winner is " + winner + "\n" + TxtDef.ANSI_BOLD + TxtDef.ANSI_BRIGHT_YELLOW +
                "═════════════════════ WINNER ════════════════════" + TxtDef.ANSI_RESET);
    }
}
