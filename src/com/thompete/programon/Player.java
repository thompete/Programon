package com.thompete.programon;

import com.thompete.programon.creature.Attribute;
import com.thompete.programon.creature.Creature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player implements Serializable {

    protected String name;
    protected Game game;
    protected boolean isAlive;
    protected List<Creature> creatures;
    protected Creature activeCreature;
    protected int remainingRevitalizeUses;

    public Player(String name, Game game, int remainingRevitalizeUses) {
        this.name = name;
        this.game = game;
        this.remainingRevitalizeUses = remainingRevitalizeUses;
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getRemainingRevitalizeUses() {
        return remainingRevitalizeUses;
    }

    public void setCreatures(List<Creature> creatures) {
        this.creatures = creatures;
        this.activeCreature = this.creatures.get(0);
    }

    public Creature getActiveCreature() {
        return activeCreature;
    }

    public void beginTurn(Player opponent) {
        resolveStatusEffects();
        doMove(opponent);
    }

    public void doMove(Player opponent) {
        if (!opponent.isAlive()) return;

        if (!setActiveCreature()) {
            isAlive = false;
            return;
        }

        UI.print("Your active creature:\n" + activeCreature);
        UI.print("Opponent's active creature:\n" + game.getOpponent().getActiveCreature());

        int choice = UI.getChoice(
                "What do you want to do?",
                List.of("Attack", "Use special power", "Evolve creature", "Change creature")
        );

        switch (choice) {
            case 0 -> attack(opponent);
            case 1 -> useSpecialPower(opponent);
            case 2 -> evolveCreature(opponent);
            case 3 -> changeCreature(false);
        }
    }

    public void attack(Player opponent, String title) {
        UI.print(title + " " + name + " used attack\n");
        activeCreature.attack(opponent.getActiveCreature(), true);
    }

    public void attack(Player opponent) {
        attack(opponent, "Player");
    }

    public void useSpecialPower(Player opponent, String title) {
        UI.print(title + " " + name + " used special power\n");
        activeCreature.useSpecialPower(opponent.getActiveCreature(), true);
    }

    public void useSpecialPower(Player opponent) {
        useSpecialPower(opponent, "Player");
    }

    public void evolveCreature(Player opponent) {
        UI.print("Player " + name + " used evolve\n");

        if (!activeCreature.getCanEvolve()) {
            activeCreature.evolve(Arrays.asList(0, 0));
            return;
        }

        List<Attribute> modifiableAttributes = activeCreature.getAttributes().stream()
                .filter(Attribute::isModifiableByPlayer)
                .toList();
        List<String> attributeNames = modifiableAttributes.stream()
                .map(Attribute::getName)
                .toList();

        List<Integer> chosenAttributes = new ArrayList<>();
        for (int i = 0; i < Constants.EVOLUTION_NUMBER_OF_INCREASED_ATTRIBUTES; ++i) {
            chosenAttributes.add(UI.getChoice(
                    "Choose " + (i + 1) + (i == 0 ? "st" : i == 1 ? "nd" : i == 2 ? "rd" : "th") +
                            " attribute to increase:",
                    attributeNames
            ));
        }

        activeCreature.evolve(chosenAttributes);
    }

    public boolean changeCreature(boolean forced) {
        if (!forced) UI.print("Player " + name + " used change\n");

        List<Creature> aliveCreatures = getOtherAliveCreatures();
        if (aliveCreatures.size() == 0) return false;

        int chosen = UI.getChoice(
                "Choose active creature:\n",
                aliveCreatures.stream().map(Creature::toString).toList()
        );
        activeCreature = aliveCreatures.get(chosen);
        if (forced) UI.print("Player " + name + " replaces fainted creature\n");
        return true;
    }

    public void revitalizeCreatures() {
        creatures.forEach(Creature::revitalize);
    }

    public void resolveStatusEffects() {
        creatures.forEach(c -> c.resolveStatusEffects(true));
    }

    protected boolean setActiveCreature() {
        boolean result = true;
        if (activeCreature.isFainted()) {
            result = changeCreature(true);
        }
        return result;
    }

    protected List<Creature> getOtherAliveCreatures() {
        return creatures.stream()
                .filter(c -> !c.isFainted() && !c.equals(activeCreature))
                .toList();
    }
}
