package com.thompete.programon.creature.type;

import com.thompete.programon.creature.statuseffect.type.FireStatusEffect;
import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Attribute;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureType;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class FireCreature extends Creature {
    public FireCreature(String name, Attribute power, Attribute health, Attribute agility,
                        Attribute critChance, Attribute critMultiplier, Attribute specialPowerUses) {
        super(name, CreatureType.FIRE, power, health, agility, critChance, critMultiplier, specialPowerUses);
    }

    @Override
    public void performSpecialPower(Creature opponentCreature, boolean sourceIsPlayer) {
        UI.print(name + " uses Firestorm");
        double damage = getPower().getCurrentValue() * Constants.FIRE_SPECIAL_POWER_MULTIPLIER;
        damage = modifyDamageByInteractionType(damage, type, opponentCreature.getType());
        boolean result = opponentCreature.applyDamage(damage, sourceIsPlayer);
        if (result) opponentCreature.applyStatusEffect(new FireStatusEffect(StatusEffect.getDuration(), opponentCreature));
    }
}
