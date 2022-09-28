package com.thompete.programon.creature.type;

import com.thompete.programon.creature.statuseffect.type.RegenerationStatusEffect;
import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Attribute;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureType;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class EarthCreature extends Creature {
    public EarthCreature(String name, Attribute power, Attribute health, Attribute agility,
                         Attribute critChance, Attribute critMultiplier, Attribute specialPowerUses) {
        super(name, CreatureType.EARTH, power, health, agility, critChance, critMultiplier, specialPowerUses);
    }

    @Override
    public void performSpecialPower(Creature opponentCreature, boolean sourceIsPlayer) {
        UI.print(name + " uses Vitality");
        double health = opponentCreature.getHealth().getDefaultValue() * Constants.EARTH_SPECIAL_POWER_MULTIPLIER;
        if (opponentCreature.getCurrentHealth() < health) opponentCreature.setCurrentHealth(health);
        opponentCreature.applyStatusEffect(new RegenerationStatusEffect(StatusEffect.getDuration(), this));
    }
}
