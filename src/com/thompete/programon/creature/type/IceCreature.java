package com.thompete.programon.creature.type;

import com.thompete.programon.creature.statuseffect.type.FrostStatusEffect;
import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Attribute;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureType;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class IceCreature extends Creature {
    public IceCreature(String name, Attribute power, Attribute health, Attribute agility,
                       Attribute critChance, Attribute critMultiplier, Attribute specialPowerUses) {
        super(name, CreatureType.ICE, power, health, agility, critChance, critMultiplier, specialPowerUses);
    }

    @Override
    public void performSpecialPower(Creature opponentCreature, boolean sourceIsPlayer) {
        UI.print(name + " uses Snowball");
        double damage = getPower().getCurrentValue() * Constants.ICE_SPECIAL_POWER_MULTIPLIER;
        damage = modifyDamageByInteractionType(damage, type, opponentCreature.getType());
        boolean result = opponentCreature.applyDamage(damage, sourceIsPlayer);
        if (result) opponentCreature.applyStatusEffect(new FrostStatusEffect(StatusEffect.getDuration(), opponentCreature));
    }
}
