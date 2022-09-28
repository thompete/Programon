package com.thompete.programon.creature.type;

import com.thompete.programon.creature.statuseffect.type.WeaknessStatusEffect;
import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Attribute;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureType;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class WaterCreature extends Creature {
    public WaterCreature(String name, Attribute power, Attribute health, Attribute agility,
                         Attribute critChance, Attribute critMultiplier, Attribute specialPowerUses) {
        super(name, CreatureType.WATER, power, health, agility, critChance, critMultiplier, specialPowerUses);
    }

    @Override
    public void performSpecialPower(Creature opponentCreature, boolean sourceIsPlayer) {
        UI.print(name + " uses Downpour");
        double damage = getPower().getCurrentValue() * Constants.WATER_SPECIAL_POWER_MULTIPLIER;
        damage = modifyDamageByInteractionType(damage, type, opponentCreature.getType());
        boolean result = opponentCreature.applyDamage(damage, sourceIsPlayer);
        if (result) opponentCreature.applyStatusEffect(new WeaknessStatusEffect(StatusEffect.getDuration(), opponentCreature));
    }
}
