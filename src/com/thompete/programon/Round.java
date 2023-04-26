package com.thompete.programon;

import com.thompete.programon.util.Util;

import java.io.Serializable;
import java.util.List;

public class Round implements Serializable {

    private final Game game;
    private final int number;

    public enum Outcome {
        WON, LOST
    }

    public Round(Game game, int number) {
        this.game = game;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Outcome start() {
        Opponent opponent;
        OpponentGenerator opponentGenerator = new OpponentGenerator(new NameGenerator(3, 5), game);
        if (number < Constants.NUMBER_OF_ROUNDS - 1) opponent = opponentGenerator.generateOpponent();
        else opponent = opponentGenerator.generateBoss();
        game.setOpponent(opponent);

        Player player = game.getPlayer();
        if (number > 0 && player.getRemainingRevitalizeUses() > 0) {
            int choice = UI.getChoice(
                    "Do you want to revitalize your creatures? Remaining uses: " + player.getRemainingRevitalizeUses(),
                    List.of("Yes", "No")
            );
            if (choice == 0) player.revitalizeCreatures();
        }

        UI.print("Round " + (number + 1) + " of " + Constants.NUMBER_OF_ROUNDS);
        UI.print("You are challenged by " + opponent.getTitle() + " " + opponent.getName() + "\n");
        UI.getChoice(List.of("Continue"));

        return loopTurns();
    }

    private Outcome loopTurns() {
        Player player = game.getPlayer(), opponent = game.getOpponent();
        Player firstPlayer, secondPlayer;
        int roll = Util.randomInRange(0, 1);
        if (roll == 0) {
            UI.print("You go first\n");
            firstPlayer = player;
            secondPlayer = opponent;
        } else {
            UI.print("Your opponent goes first\n");
            firstPlayer = opponent;
            secondPlayer = player;
        }

        while (player.isAlive() && opponent.isAlive()) {
            if (firstPlayer.isAlive()) {
                firstPlayer.beginTurn(secondPlayer);
            } else {
                if (firstPlayer == player) return Outcome.LOST;
                else return Outcome.WON;
            }
            if (secondPlayer.isAlive()) {
                secondPlayer.beginTurn(firstPlayer);
            } else {
                if (secondPlayer == player) return Outcome.LOST;
                else return Outcome.WON;
            }
        }

        if (player.isAlive()) return Outcome.WON;
        else return Outcome.LOST;
    }
}















