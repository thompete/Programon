package com.thompete.programon;

import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureGenerator;

import java.util.ArrayList;
import java.util.List;

public class OpponentGenerator {

    private final Game game;
    private final Game.Difficulty difficulty;
    private final NameGenerator nameGenerator;

    public OpponentGenerator(NameGenerator nameGenerator, Game game) {
        this.difficulty = game.getDifficulty();
        this.nameGenerator = nameGenerator;
        this.game = game;
    }

    public Opponent generateOpponent() {
        Opponent opponent =  new Opponent("Trainer", nameGenerator.getName(), game);
        int numberOfCreatures = Constants.getIntConstant("NUMBER_OF_OPPONENT_CREATURES_" + difficulty.name());
        return generateOpponentCreatures(opponent, numberOfCreatures);
    }

    public Opponent generateBoss() {
        Opponent boss = new Opponent("Master", nameGenerator.getName(), game);
        int numberOfCreatures = Constants.getIntConstant("NUMBER_OF_OPPONENT_CREATURES_" + difficulty.name());
        numberOfCreatures += Constants.BOSS_NUMBER_OF_CREATURES_INCREASE;
        return generateOpponentCreatures(boss, numberOfCreatures);
    }

    private Opponent generateOpponentCreatures(Opponent opponent, int numberOfCreatures) {
        List<Creature> generatedCreatures = new ArrayList<>();
        CreatureGenerator creatureGenerator = new CreatureGenerator(new NameGenerator(2, 4));
        for (int i = 0; i < numberOfCreatures; i++) {
            generatedCreatures.add(creatureGenerator.generateCreature());
        }
        opponent.setCreatures(generatedCreatures);
        return opponent;
    }
}
