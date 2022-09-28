package com.thompete.programon;

import com.thompete.programon.util.Util;

import java.io.Serializable;
import java.util.List;

public class Round implements Serializable {

    private Game game;
    private int number;

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
        OpponentGenerator og = new OpponentGenerator(new NameGenerator(3, 5), game);
        if (number < Constants.NUMBER_OF_ROUNDS - 1) opponent = og.generateOpponent();
        else opponent = og.generateBoss();
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
        Player pl = game.getPlayer(), op = game.getOpponent();
        Player firstPlayer, secondPlayer;
        int roll = Util.randomInRange(0, 1);
        if (roll == 0) {
            UI.print("You go first\n");
            firstPlayer = pl;
            secondPlayer = op;
        } else {
            UI.print("Your opponent goes first\n");
            firstPlayer = op;
            secondPlayer = pl;
        }

        while (pl.isAlive() && op.isAlive()) {
            if (firstPlayer.isAlive()) {
                firstPlayer.beginTurn(secondPlayer);
            } else {
                if (firstPlayer == pl) return Outcome.LOST;
                else return Outcome.WON;
            }
            if (secondPlayer.isAlive()) {
                secondPlayer.beginTurn(firstPlayer);
            } else {
                if (secondPlayer == pl) return Outcome.LOST;
                else return Outcome.WON;
            }
        }

        if (pl.isAlive()) return Outcome.WON;
        else return Outcome.LOST;
    }
}















