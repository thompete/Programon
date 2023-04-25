package com.thompete.programon;

import com.thompete.programon.creature.Attribute;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Opponent extends Player {

    private String title;
    private int changeCreatureCooldown;

    public Opponent(String title, String name, Game game) {
        super(name, game, 0);
        this.title = title;
        this.changeCreatureCooldown = 0;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void doMove(Player opponent) {
        if (!opponent.isAlive()) return;

        if (!setActiveCreature()) {
            isAlive = false;
            return;
        }

        if (!activeCreature.getCanMove()) {
            int roll = Util.randomInRange(0, 1);
            if (roll == 0) {
                changeCreature(false);
                return;
            }
        }

        if (changeCreatureCooldown > 0) changeCreatureCooldown--;

        if (activeCreature.getCurrentHealth() <= Constants.OPPONENT_CREATURE_LOW_HEALTH * activeCreature.getHealth().getDefaultValue() &&
            changeCreatureCooldown == 0 && getOtherAliveCreatures().size() > 0) {
            double roll = Util.randomInRange(0, 100);
            double chance = Constants.getDoubleConstant("OPPONENT_CHANGE_CREATURE_CHANCE_" + game.getDifficulty().name());
            if (roll <= chance) {
                changeCreature(false);
                changeCreatureCooldown = Constants.OPPONENT_CHANGE_CREATURE_COOLDOWN;
                return;
            }
        }

        if (activeCreature.getCanEvolve()) {
            double roll = Util.randomInRange(0, 100);
            double chance = Constants.getDoubleConstant("OPPONENT_EVOLVE_CREATURE_CHANCE_" + game.getDifficulty().name());
            if (roll <= chance) {
                evolveCreature(game.getPlayer());
                return;
            }
        }

        if (activeCreature.getRemainingSpecialPowerUses() > 0) {
            double roll = Util.randomInRange(0, 100);
            double chance = Constants.getDoubleConstant("OPPONENT_SPECIAL_ATTACK_CHANCE_" + game.getDifficulty().name());
            if (roll <= chance) {
                useSpecialPower(game.getPlayer(), title);
                return;
            }
        }

        attack(game.getPlayer(), title);
    }

    @Override
    public void attack(Player opponent, String title) {
        UI.print(title + " " + name + " used attack\n");
        activeCreature.attack(opponent.getActiveCreature(), false);
    }

    @Override
    public void useSpecialPower(Player opponent, String title) {
        UI.print(title + " " + name + " used special power\n");
        activeCreature.useSpecialPower(opponent.getActiveCreature(), false);
    }

    @Override
    public boolean changeCreature(boolean forced) {
        if (!forced) UI.print(title + " " + name + " used change\n");

        List<Creature> aliveCreatures = getOtherAliveCreatures();
        if (aliveCreatures.size() == 0) return false;

        int roll = Util.randomInRange(0, aliveCreatures.size() - 1);
        activeCreature = aliveCreatures.get(roll);
        if (forced) UI.print(title + " " + name + " replaces fainted creature\n");

        return true;
    }

    @Override
    public void evolveCreature(Player opponent) {
        UI.print(title + " " + name + " used evolve\n");

        List<Attribute> modifiableAttributes = activeCreature.getAttributes().stream()
                .filter(Attribute::isModifiableByPlayer)
                .toList();

        List<Integer> chosenAttributes = new ArrayList<>();
        for (int i = 0; i < Constants.EVOLUTION_NUMBER_OF_INCREASED_ATTRIBUTES; ++i) {
            chosenAttributes.add(Util.randomInRange(0, modifiableAttributes.size() - 1));
        }
        activeCreature.evolve(chosenAttributes);
    }

    @Override
    public void resolveStatusEffects() {
        creatures.forEach(c -> c.resolveStatusEffects(false));
    }
}
