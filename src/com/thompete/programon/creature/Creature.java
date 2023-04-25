package com.thompete.programon.creature;

import com.thompete.programon.creature.statuseffect.StatusEffect;
import com.thompete.programon.Constants;
import com.thompete.programon.Statistics;
import com.thompete.programon.UI;
import com.thompete.programon.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Creature implements Serializable {

    protected String name;
    protected CreatureType type;
    protected int level;
    protected List<Attribute> attributes;
    protected double experience;
    protected double damageReduction;
    protected boolean isFainted, canEvolve, canMove;
    protected List<StatusEffect> statusEffects;

    public Creature(String name, CreatureType type, Attribute power, Attribute health, Attribute agility,
                    Attribute critChance, Attribute critMultiplier, Attribute specialPowerUses) {
        this.name = name;
        this.type = type;

        this.attributes = new ArrayList<>(List.of(power, health, agility, critChance, critMultiplier, specialPowerUses));

        double exp = 0;
        for (Attribute attribute : this.attributes) {
            exp += attribute.getDefaultValue();
        }
        double expVal = exp / this.attributes.size() * Constants.EXP_VALUE_CALCULATION_FACTOR;
        Attribute expValue = new Attribute("Exp value", expVal, false);
        this.attributes.add(expValue);

        double expToEvo = exp / attributes.size() * Constants.EXP_TO_EVOLUTION_CALCULATION_FACTOR;
        Attribute expToEvolution = new Attribute("Exp to evolution", expToEvo, false);
        this.attributes.add(expToEvolution);

        this.damageReduction = 0;

        this.level = 0;
        this.experience = 0;
        this.isFainted = false;
        this.canEvolve = false;
        this.canMove = true;

        this.statusEffects = new ArrayList<>();
    }

    public abstract void performSpecialPower(Creature opponentCreature, boolean sourceIsPlayer);

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public Attribute getPower() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Power")).toList().get(0);
    }

    public Attribute getHealth() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Health")).toList().get(0);
    }

    public Attribute getAgility() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Agility")).toList().get(0);
    }

    public Attribute getCritChance() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Crit chance")).toList().get(0);
    }

    public Attribute getCritMultiplier() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Crit multiplier")).toList().get(0);
    }

    public Attribute getSpecialPowerUses() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Special power uses")).toList().get(0);
    }

    public Attribute getExpToEvolution() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Exp to evolution")).toList().get(0);
    }

    public Attribute getExpValue() {
        return attributes.stream().filter((Attribute a) -> a.getName().equals("Exp value")).toList().get(0);
    }

    public boolean isFainted() {
        return isFainted;
    }

    public double getCurrentHealth() {
        return getHealth().getCurrentValue();
    }

    public void setCurrentHealth(double currentHealth) {
        getHealth().setCurrentValue(currentHealth);
    }

    public boolean getCanEvolve() {
        return canEvolve;
    }

    public double getRemainingSpecialPowerUses() {
        return getSpecialPowerUses().getCurrentValue();
    }

    public void setCurrentPower(double currentPower) {
        getPower().setCurrentValue(currentPower);
    }

    public void setCurrentAgility(double currentAgility) {
        getAgility().setCurrentValue(currentAgility);
    }

    public boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(double damageReduction) {
        this.damageReduction = damageReduction;
    }

    public CreatureType getType() {
        return type;
    }

    public boolean useSpecialPower(Creature opponentCreature, boolean sourceIsPlayer) {
        if (!canMove) {
            UI.print(name + " can't move!\n");
            return false;
        }
        if (getSpecialPowerUses().getCurrentValue() == 0) {
            UI.print(name + " can't use special power!\n");
            return false;
        }
        getSpecialPowerUses().subtractFromCurrent(1);
        performSpecialPower(opponentCreature, sourceIsPlayer);
        return true;
    }

    public boolean attack(Creature other, boolean sourceIsPlayer) {
        if (!canMove) {
            UI.print(name + " can't move!\n");
            return false;
        }

        double damage = Util.deviate(getPower().getCurrentValue(), Constants.DAMAGE_DEVIATION);

        double roll = Util.randomInRange(0, 100);
        boolean crit = false;
        if (roll <= getCritChance().getCurrentValue()) {
            crit = true;
            damage *= getCritMultiplier().getCurrentValue();
        }
        UI.print(name + " attacks " + other.name);

        damage = modifyDamageByInteractionType(damage, type, other.type);

        if (crit) UI.print("Critical hit!");
        other.applyDamage(damage, sourceIsPlayer);
        if (other.isFainted) {
            experience += other.getExpValue().getDefaultValue();
            if (experience >= getExpToEvolution().getDefaultValue()) {
                canEvolve = true;
                UI.print(name + " is ready to evolve!\n");
            }
        }
        return true;
    }

    public boolean evolve(List<Integer> chosenAttributes) {
        if (!canMove) {
            UI.print(name + " can't move!\n");
            return false;
        }

        if (!canEvolve || level == Constants.MAX_EVOLUTION_LEVEL) {
            UI.print(name + " is not ready to evolve or reached maximum level!\n");
            return false;
        }

        level++;
        experience -= getExpToEvolution().getDefaultValue();
        getExpToEvolution().multiplyDefault(Constants.NEXT_LEVEL_EXP_TO_EVOLUTION_MULTIPLIER);
        if (experience < getExpToEvolution().getDefaultValue()) canEvolve = false;
        UI.print(name + " evolved! Level " + (level - 1) + " -> " + level + "\n");

        List<Attribute> modifiableAttributes = attributes.stream()
                .filter(Attribute::isModifiableByPlayer)
                .toList();
        chosenAttributes.forEach(ca -> modifyAttribute(modifiableAttributes.get(ca)));

        revitalize();

        return true;
    }

    public void revitalize() {
        clearStatusEffects();
        isFainted = false;
        attributes.forEach(Attribute::reset);
    }

    public void resolveStatusEffects(boolean sourceIsPlayer) {
        statusEffects.removeIf(StatusEffect::isToRemove);
        statusEffects.forEach(se -> se.resolve(sourceIsPlayer));
    }

    public boolean applyDamage(double damage, boolean isEvadeable, boolean printMessage, boolean sourceIsPlayer) {
        double roll = Util.randomInRange(0, 100);
        if (roll <= getAgility().getCurrentValue() && isEvadeable) {
            UI.print(name + " evaded attack!\n");
            return false;
        } else {
            damage -= damageReduction;
            if (damage < 0) damage = 0;
            getHealth().subtractFromCurrent(damage);
            if (printMessage) UI.print(name + " received " + Util.round(damage) + " damage\n");
            if (sourceIsPlayer) Statistics.increaseDamageDealt(damage);
            else Statistics.increaseDamageReceived(damage);

            if (getHealth().getCurrentValue() <= 0) {
                getHealth().setCurrentValue(0);
                isFainted = true;
                UI.print(name + " fainted!\n");
                if (sourceIsPlayer) Statistics.increaseFaintsDealt();
                else Statistics.increaseFaintsReceived();
            }
            return true;
        }
    }

    public boolean applyDamage(double damage, boolean sourceIsPlayer) {
        return applyDamage(damage, true, true, sourceIsPlayer);
    }

    public void applyStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
    }

    public void clearStatusEffects() {
        statusEffects.clear();
    }

    public double modifyDamageByInteractionType(double damage, CreatureType type, CreatureType otherType) {
        int typeInteraction = CreatureType.getInteraction(type, otherType);
        if (typeInteraction == -1) {
            damage *= Constants.NEGATIVE_INTERACTION_MULTIPLIER;
            UI.print("It's not very effective");
        }
        else if (typeInteraction == 1) {
            damage *= Constants.POSITIVE_INTERACTION_MULTIPLIER;
            UI.print("It's super effective");
        }
        return damage;
    }

    protected void modifyAttribute(Attribute attribute) {
        if (attribute.getName().equals("Special power uses")) attribute.addToDefault(1);
        else attribute.multiplyDefault(Constants.NEXT_LEVEL_ATTRIBUTE_MULTIPLIER);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Type: " + type + ", Power: " + getPower() + ", Health: " + getHealth() +
                ", Agility: " + getAgility() + ", Crit chance: " + getCritChance() + ", Crit multiplier: " + getCritMultiplier() +
                ", Remaining special power uses: " + getRemainingSpecialPowerUses() + ", Experience: " + Util.round(experience) + "\n";
    }
}
















