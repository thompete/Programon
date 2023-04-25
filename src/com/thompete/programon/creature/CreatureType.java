package com.thompete.programon.creature;

import com.thompete.programon.Constants;
import com.thompete.programon.util.Pair;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;


public enum CreatureType implements Serializable {

    WATER(
            "Water",
            Constants.WATER_BASE_POWER,
            Constants.WATER_BASE_HEALTH,
            Constants.WATER_BASE_AGILITY,
            Constants.WATER_BASE_CRIT_CHANCE,
            Constants.WATER_BASE_CRIT_MULTIPLIER,
            Constants.WATER_BASE_SPECIAL_POWER_USES
    ),
    EARTH(
            "Earth",
            Constants.EARTH_BASE_POWER,
            Constants.EARTH_BASE_HEALTH,
            Constants.EARTH_BASE_AGILITY,
            Constants.EARTH_BASE_CRIT_CHANCE,
            Constants.EARTH_BASE_CRIT_MULTIPLIER,
            Constants.EARTH_BASE_SPECIAL_POWER_USES
    ),
    AIR(
            "Air",
            Constants.AIR_BASE_POWER,
            Constants.AIR_BASE_HEALTH,
            Constants.AIR_BASE_AGILITY,
            Constants.AIR_BASE_CRIT_CHANCE,
            Constants.AIR_BASE_CRIT_MULTIPLIER,
            Constants.AIR_BASE_SPECIAL_POWER_USES
    ),
    FIRE(
            "Fire",
            Constants.FIRE_BASE_POWER,
            Constants.FIRE_BASE_HEALTH,
            Constants.FIRE_BASE_AGILITY,
            Constants.FIRE_BASE_CRIT_CHANCE,
            Constants.FIRE_BASE_CRIT_MULTIPLIER,
            Constants.FIRE_BASE_SPECIAL_POWER_USES
    ),
    ICE(
            "Ice",
            Constants.ICE_BASE_POWER,
            Constants.ICE_BASE_HEALTH,
            Constants.ICE_BASE_AGILITY,
            Constants.ICE_BASE_CRIT_CHANCE,
            Constants.ICE_BASE_CRIT_MULTIPLIER,
            Constants.ICE_BASE_SPECIAL_POWER_USES
    ),
    STEEL(
            "Steel",
            Constants.STEEL_BASE_POWER,
            Constants.STEEL_BASE_HEALTH,
            Constants.STEEL_BASE_AGILITY,
            Constants.STEEL_BASE_CRIT_CHANCE,
            Constants.STEEL_BASE_CRIT_MULTIPLIER,
            Constants.STEEL_BASE_SPECIAL_POWER_USES
    );

    private static final Map<Pair<CreatureType, CreatureType>, Integer> interactions = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.WATER, CreatureType.WATER), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.WATER, CreatureType.EARTH), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.WATER, CreatureType.AIR), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.WATER, CreatureType.FIRE), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.WATER, CreatureType.ICE), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.WATER, CreatureType.STEEL), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.EARTH, CreatureType.WATER), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.EARTH, CreatureType.EARTH), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.EARTH, CreatureType.AIR), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.EARTH, CreatureType.FIRE), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.EARTH, CreatureType.ICE), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.EARTH, CreatureType.STEEL), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.AIR, CreatureType.WATER), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.AIR, CreatureType.EARTH), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.AIR, CreatureType.AIR), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.AIR, CreatureType.FIRE), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.AIR, CreatureType.ICE), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.AIR, CreatureType.STEEL), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.FIRE, CreatureType.WATER), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.FIRE, CreatureType.EARTH), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.FIRE, CreatureType.AIR), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.FIRE, CreatureType.FIRE), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.FIRE, CreatureType.ICE), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.FIRE, CreatureType.STEEL), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.ICE, CreatureType.WATER), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.ICE, CreatureType.EARTH), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.ICE, CreatureType.AIR), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.ICE, CreatureType.FIRE), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.ICE, CreatureType.ICE), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.ICE, CreatureType.STEEL), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.STEEL, CreatureType.WATER), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.STEEL, CreatureType.EARTH), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.STEEL, CreatureType.AIR), 1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.STEEL, CreatureType.FIRE), -1),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.STEEL, CreatureType.ICE), 0),
            new AbstractMap.SimpleEntry<>(new Pair<>(CreatureType.STEEL, CreatureType.STEEL), 1)
    );

    private final String name;
    private final double
        basePower,
        baseHealth,
        baseAgility,
        baseCritChance,
        baseCritMultiplier,
        baseSpecialPowerUses;

    CreatureType(String name, double basePower, double baseHealth, double baseAgility, double baseCritChance,
                         double baseCritMultiplier, double baseSpecialPowerUses) {
        this.name = name;
        this.basePower = basePower;
        this.baseHealth = baseHealth;
        this.baseAgility = baseAgility;
        this.baseCritChance = baseCritChance;
        this.baseCritMultiplier = baseCritMultiplier;
        this.baseSpecialPowerUses = baseSpecialPowerUses;
    }

    public static int getInteraction(CreatureType attackingType, CreatureType defendingType) {
        return interactions.get(new Pair<>(attackingType, defendingType));
    }

    public String getName() {
        return name;
    }

    public double getBasePower() {
        return basePower;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public double getBaseAgility() {
        return baseAgility;
    }

    public double getBaseCritChance() {
        return baseCritChance;
    }

    public double getBaseCritMultiplier() {
        return baseCritMultiplier;
    }

    public double getBaseSpecialPowerUses() {
        return baseSpecialPowerUses;
    }

    @Override
    public String toString() {
        return name;
    }
}
