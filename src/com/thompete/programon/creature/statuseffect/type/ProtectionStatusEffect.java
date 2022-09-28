package com.thompete.programon.creature.statuseffect.type;

import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.statuseffect.StatusEffect;
import com.thompete.programon.util.Util;

public class ProtectionStatusEffect extends StatusEffect {

    public ProtectionStatusEffect(int duration, Creature creature) {
        super(Type.PROTECTION, duration, creature);
    }

    @Override
    public void resolve(boolean sourceIsPlayer) {
        if (durationLeft == 0 || creature.isFainted()) return;
        double damageReduction = creature.getHealth().getDefaultValue() * Constants.PROTECTION_STATUS_EFFECT_MULTIPLIER;

        if (durationLeft == duration) creature.setDamageReduction(creature.getDamageReduction() + damageReduction);

        UI.print(messageString + ": " + Util.round(damageReduction) + " damage reduction active\n");
        durationLeft--;
        if (durationLeft == 0) {
            creature.setDamageReduction(creature.getDamageReduction() - damageReduction);
            toRemove = true;
        }
    }
}
