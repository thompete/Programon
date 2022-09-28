package com.thompete.programon.creature.type;

import com.thompete.programon.creature.statuseffect.type.ProtectionStatusEffect;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Attribute;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureType;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class SteelCreature extends Creature {
    public SteelCreature(String name, Attribute power, Attribute health, Attribute agility,
                         Attribute critChance, Attribute critMultiplier, Attribute specialPowerUses) {
        super(name, CreatureType.STEEL, power, health, agility, critChance, critMultiplier, specialPowerUses);
    }

    @Override
    public void performSpecialPower(Creature opponentCreature, boolean sourceIsPlayer) {
        UI.print(name + " uses Shield");
        opponentCreature.applyStatusEffect(new ProtectionStatusEffect(StatusEffect.getDuration(), this));
    }
}
