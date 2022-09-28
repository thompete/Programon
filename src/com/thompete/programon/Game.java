package com.thompete.programon;

import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

    private App app;
    private Difficulty difficulty;
    private Player player;
    private Opponent opponent;
    private Round round;
    private int currentRoundNumber;

    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    public Game(App app) {
        this.app = app;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    public void start() {
        setupGame();
        createPlayer();
        loopRounds();
    }

    public void continueGame() {
        loopRounds();
    }

    private void setupGame() {
        int choice = UI.getChoice(
                "Choose save file:",
                List.of(
                        app.getSaveSlot(0).getName(),
                        app.getSaveSlot(1).getName(),
                        app.getSaveSlot(2).getName()
                )
        );
        app.setSaveFile(app.getSaveSlot(choice));

        int chosenDifficulty = UI.getChoice(
                "Choose difficulty:",
                List.of("Easy", "Normal", "Hard")
        );
        difficulty = Difficulty.values()[chosenDifficulty];
    }

    private void createPlayer() {
        String name = UI.getInput("Enter your name:");
        player = new Player(name, this, Constants.getIntConstant("REVITALIZE_USES_" + difficulty.name()));

        List<Creature> generatedCreatures = new ArrayList<>();
        CreatureGenerator cg = new CreatureGenerator(new NameGenerator(2, 4));

        int numberOfCreatures = Constants.NUMBER_OF_PLAYER_CREATURES;
        int toChooseFrom = Constants.NUMBER_OF_CREATURES_TO_CHOOSE_FROM;

        for (int i = 0; i < toChooseFrom; i++) {
            generatedCreatures.add(cg.generateCreature());
        }

        UI.print("Choose " + numberOfCreatures + " creatures out of " + toChooseFrom + ":\n");

        List<Creature> chosenCreatures = new ArrayList<>();
        for (int i = 1; i <= numberOfCreatures; i++) {
            int chosen = UI.getChoice(
                    "Choose the " + i + (i == 1 ? "st" : i == 2 ? "nd" : i == 3 ? "rd" : "th") + " creature:\n",
                    generatedCreatures.stream().map((Creature c) -> c.toString()).toList()
            );
            chosenCreatures.add(generatedCreatures.get(chosen));
            generatedCreatures.remove(chosen);
        }

        player.setCreatures(chosenCreatures);
    }

    private void loopRounds() {
        for (int i = currentRoundNumber; i < Constants.NUMBER_OF_ROUNDS; i++) {
            round = new Round(this, i);
            Round.Outcome outcome = round.start();
            currentRoundNumber++;

            if (outcome == Round.Outcome.WON) {
                Statistics.increaseRoundsWon();
                if (round.getNumber() == Constants.NUMBER_OF_ROUNDS - 1) {
                    displayWin();
                    return;
                } else {
                    int choice = UI.getChoice(
                            "You won the round!",
                            List.of("Next Round", "Save and Exit")
                    );
                    if (choice == 1) {
                        app.getSaveFile().save(this);
                        UI.print("Saved successfully\n");
                        return;
                    }
                }
            } else {
                displayGameOver();
                return;
            }
        }
    }

    private void displayGameOver() {
        UI.print("Game Over");
        displayStatistics();
        UI.getChoice(List.of("Main menu"));
    }

    private void displayWin() {
        UI.print("Congratulations! You win the game!\n");
        displayStatistics();
        UI.getChoice(List.of("Main menu"));
    }

    private void displayStatistics() {
        UI.print(Statistics.get());
        Statistics.reset();
    }
}

















