package com.thompete.programon.creature.statuseffect.type;

import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.CreatureType;
import com.thompete.programon.creature.statuseffect.StatusEffect;
import com.thompete.programon.util.Util;

public class FireStatusEffect extends StatusEffect {

    public FireStatusEffect(int duration, Creature creature) {
        super(Type.FIRE, duration, creature);
    }

    @Override
    public void resolve(boolean sourceIsPlayer) {
        if (durationLeft == 0 || creature.isFainted()) return;
        double damage = Util.deviate(Constants.FIRE_STATUS_EFFECT_VALUE, Constants.STATUS_EFFECT_DEVIATION);
        damage = creature.modifyDamageByInteractionType(damage, CreatureType.FIRE, creature.getType());
        creature.applyDamage(damage, false, false, sourceIsPlayer);
        UI.print(messageString + ": gets " + Util.round(damage) + " damage\n");
        durationLeft--;
        if (durationLeft == 0) toRemove = true;
    }
}
