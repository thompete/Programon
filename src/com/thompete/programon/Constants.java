package com.thompete.programon;

public class Constants {

    /* Creatures */

    public static final double WATER_BASE_POWER = 10;
    public static final double WATER_BASE_HEALTH = 100;
    public static final double WATER_BASE_AGILITY = 10;
    public static final double WATER_BASE_CRIT_CHANCE = 3;
    public static final double WATER_BASE_CRIT_MULTIPLIER = 2;
    public static final double WATER_BASE_SPECIAL_POWER_USES = 2;

    public static final double EARTH_BASE_POWER = 10;
    public static final double EARTH_BASE_HEALTH = 130;
    public static final double EARTH_BASE_AGILITY = 8;
    public static final double EARTH_BASE_CRIT_CHANCE = 2;
    public static final double EARTH_BASE_CRIT_MULTIPLIER = 2;
    public static final double EARTH_BASE_SPECIAL_POWER_USES = 1;

    public static final double AIR_BASE_POWER = 10;
    public static final double AIR_BASE_HEALTH = 100;
    public static final double AIR_BASE_AGILITY = 20;
    public static final double AIR_BASE_CRIT_CHANCE = 3;
    public static final double AIR_BASE_CRIT_MULTIPLIER = 2;
    public static final double AIR_BASE_SPECIAL_POWER_USES = 2;

    public static final double FIRE_BASE_POWER = 13;
    public static final double FIRE_BASE_HEALTH = 100;
    public static final double FIRE_BASE_AGILITY = 10;
    public static final double FIRE_BASE_CRIT_CHANCE = 5;
    public static final double FIRE_BASE_CRIT_MULTIPLIER = 2;
    public static final double FIRE_BASE_SPECIAL_POWER_USES = 2;

    public static final double ICE_BASE_POWER = 10;
    public static final double ICE_BASE_HEALTH = 100;
    public static final double ICE_BASE_AGILITY = 10;
    public static final double ICE_BASE_CRIT_CHANCE = 3;
    public static final double ICE_BASE_CRIT_MULTIPLIER = 2;
    public static final double ICE_BASE_SPECIAL_POWER_USES = 3;

    public static final double STEEL_BASE_POWER = 11.5;
    public static final double STEEL_BASE_HEALTH = 90;
    public static final double STEEL_BASE_AGILITY = 15;
    public static final double STEEL_BASE_CRIT_CHANCE = 4;
    public static final double STEEL_BASE_CRIT_MULTIPLIER = 2;
    public static final double STEEL_BASE_SPECIAL_POWER_USES = 3;

    public static final double ATTRIBUTE_DEVIATION = 0.2;
    public static final double DAMAGE_DEVIATION = 0.2;

    public static final double POSITIVE_INTERACTION_MULTIPLIER = 1.33;
    public static final double NEGATIVE_INTERACTION_MULTIPLIER = 0.66;

    public static final double EXP_VALUE_CALCULATION_FACTOR = 1;
    public static final double EXP_TO_EVOLUTION_CALCULATION_FACTOR = 2.5;
    public static final double MAX_EVOLUTION_LEVEL = 2;
    public static final double EVOLUTION_NUMBER_OF_INCREASED_ATTRIBUTES = 2;

    public static final double NEXT_LEVEL_EXP_TO_EVOLUTION_MULTIPLIER = 1.5;
    public static final double NEXT_LEVEL_ATTRIBUTE_MULTIPLIER = 1.25;

    public static final double AIR_SPECIAL_POWER_MULTIPLIER = 2;
    public static final double EARTH_SPECIAL_POWER_MULTIPLIER = 0.5;
    public static final double FIRE_SPECIAL_POWER_MULTIPLIER = 2.5;
    public static final double ICE_SPECIAL_POWER_MULTIPLIER = 1.5;
    public static final double WATER_SPECIAL_POWER_MULTIPLIER = 1.5;

    /* Status effects */

    public static final double STUMBLE_STATUS_EFFECT_MULTIPLIER = 0.5;
    public static final double REGENERATION_STATUS_EFFECT_MULTIPLIER = 0.2;
    public static final double FIRE_STATUS_EFFECT_VALUE = 6;
    public static final double PROTECTION_STATUS_EFFECT_MULTIPLIER = 0.1;
    public static final double WEAKNESS_STATUS_EFFECT_MULTIPLIER = 0.5;

    public static final double STATUS_EFFECT_DEVIATION = 0.2;

    public static final int STATUS_EFFECT_DURATION = 2;
    public static final int LONG_STATUS_EFFECT_DURATION = 3;
    public static final int VERY_LONG_STATUS_EFFECT_DURATION = 4;
    public static final int LONG_STATUS_EFFECT_CHANCE = 20;
    public static final int VERY_LONG_STATUS_EFFECT_CHANCE = 5;

    /* Name Generator */

    public static final int V_FREQ = 35;
    public static final int VCCC_FREQ = 0;
    public static final int VCC_FREQ = 15;
    public static final int REV_FREQ = 20;

    /* Game */

    public static final int NUMBER_OF_ROUNDS = 4;
    public static final int NUMBER_OF_PLAYER_CREATURES = 6;
    public static final int NUMBER_OF_CREATURES_TO_CHOOSE_FROM = 10;
    public static final int REVITALIZE_USES_EASY = 4;
    public static final int REVITALIZE_USES_NORMAL = 4;
    public static final int REVITALIZE_USES_HARD = 3;

    /* Opponent */

    public static final int NUMBER_OF_OPPONENT_CREATURES_EASY = 4;
    public static final int NUMBER_OF_OPPONENT_CREATURES_NORMAL = 5;
    public static final int NUMBER_OF_OPPONENT_CREATURES_HARD = 6;
    public static final int BOSS_NUMBER_OF_CREATURES_INCREASE = 2;
    public static final double OPPONENT_CREATURE_LOW_HEALTH = 0.3;
    public static final int OPPONENT_CHANGE_CREATURE_COOLDOWN = 2;
    public static final double OPPONENT_CHANGE_CREATURE_CHANCE_EASY = 40;
    public static final double OPPONENT_CHANGE_CREATURE_CHANCE_NORMAL = 60;
    public static final double OPPONENT_CHANGE_CREATURE_CHANCE_HARD = 80;
    public static final double OPPONENT_EVOLVE_CREATURE_CHANCE_EASY = 40;
    public static final double OPPONENT_EVOLVE_CREATURE_CHANCE_NORMAL = 60;
    public static final double OPPONENT_EVOLVE_CREATURE_CHANCE_HARD = 80;
    public static final double OPPONENT_SPECIAL_ATTACK_CHANCE_EASY = 30;
    public static final double OPPONENT_SPECIAL_ATTACK_CHANCE_NORMAL = 40;
    public static final double OPPONENT_SPECIAL_ATTACK_CHANCE_HARD = 50;


    public static double getDoubleConstant(String constant) {
        double result = 0;
        try {
            result = Constants.class.getField(constant).getDouble(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getIntConstant(String constant) {
        int result = 0;
        try {
            result = Constants.class.getField(constant).getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
