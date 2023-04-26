package com.thompete.programon.creature;

import com.thompete.programon.NameGenerator;
import com.thompete.programon.creature.type.*;
import com.thompete.programon.Constants;
import com.thompete.programon.util.Util;

public class CreatureGenerator {

    private final NameGenerator nameGenerator;

    public CreatureGenerator(NameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

    public Creature generateCreature() {
        Creature creature = null;

        String name = nameGenerator.getName();
        int roll = Util.randomInRange(0, 5);

        switch (roll) {
            case 0 -> creature = new AirCreature(
                    name,
                    new Attribute("Power", Util.deviate(CreatureType.AIR.getBasePower(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Health", Util.deviate(CreatureType.AIR.getBaseHealth(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Agility", Util.deviate(CreatureType.AIR.getBaseAgility(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit chance", Util.deviate(CreatureType.AIR.getBaseCritChance(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit multiplier", Util.deviate(CreatureType.AIR.getBaseCritMultiplier(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Special power uses", CreatureType.AIR.getBaseSpecialPowerUses())
            );
            case 1 -> creature = new EarthCreature(
                    name,
                    new Attribute("Power", Util.deviate(CreatureType.EARTH.getBasePower(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Health", Util.deviate(CreatureType.EARTH.getBaseHealth(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Agility", Util.deviate(CreatureType.EARTH.getBaseAgility(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit chance", Util.deviate(CreatureType.EARTH.getBaseCritChance(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit multiplier", Util.deviate(CreatureType.EARTH.getBaseCritMultiplier(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Special power uses", CreatureType.EARTH.getBaseSpecialPowerUses())
            );
            case 2 -> creature = new FireCreature(
                    name,
                    new Attribute("Power", Util.deviate(CreatureType.FIRE.getBasePower(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Health", Util.deviate(CreatureType.FIRE.getBaseHealth(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Agility", Util.deviate(CreatureType.FIRE.getBaseAgility(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit chance", Util.deviate(CreatureType.FIRE.getBaseCritChance(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit multiplier", Util.deviate(CreatureType.FIRE.getBaseCritMultiplier(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Special power uses", CreatureType.FIRE.getBaseSpecialPowerUses())
            );
            case 3 -> creature = new IceCreature(
                    name,
                    new Attribute("Power", Util.deviate(CreatureType.ICE.getBasePower(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Health", Util.deviate(CreatureType.ICE.getBaseHealth(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Agility", Util.deviate(CreatureType.ICE.getBaseAgility(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit chance", Util.deviate(CreatureType.ICE.getBaseCritChance(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit multiplier", Util.deviate(CreatureType.ICE.getBaseCritMultiplier(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Special power uses", CreatureType.ICE.getBaseSpecialPowerUses())
            );
            case 4 -> creature = new SteelCreature(
                    name,
                    new Attribute("Power", Util.deviate(CreatureType.STEEL.getBasePower(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Health", Util.deviate(CreatureType.STEEL.getBaseHealth(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Agility", Util.deviate(CreatureType.STEEL.getBaseAgility(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit chance", Util.deviate(CreatureType.STEEL.getBaseCritChance(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit multiplier", Util.deviate(CreatureType.STEEL.getBaseCritMultiplier(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Special power uses", CreatureType.STEEL.getBaseSpecialPowerUses())
            );
            case 5 -> creature = new WaterCreature(
                    name,
                    new Attribute("Power", Util.deviate(CreatureType.WATER.getBasePower(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Health", Util.deviate(CreatureType.WATER.getBaseHealth(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Agility", Util.deviate(CreatureType.WATER.getBaseAgility(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit chance", Util.deviate(CreatureType.WATER.getBaseCritChance(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Crit multiplier", Util.deviate(CreatureType.WATER.getBaseCritMultiplier(), Constants.ATTRIBUTE_DEVIATION)),
                    new Attribute("Special power uses", CreatureType.WATER.getBaseSpecialPowerUses())
            );
        }

        return creature;
    }
}
