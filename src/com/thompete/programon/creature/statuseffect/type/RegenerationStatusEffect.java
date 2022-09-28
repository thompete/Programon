package com.thompete.programon.creature.statuseffect.type;

import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.statuseffect.StatusEffect;
import com.thompete.programon.util.Util;

public class RegenerationStatusEffect extends StatusEffect {

    public RegenerationStatusEffect(int duration, Creature creature) {
        super(Type.REGENERATION, duration, creature);
    }

    @Override
    public void resolve(boolean sourceIsPlayer) {
        if (durationLeft == 0 || creature.isFainted()) return;
        double maxRegeneration = creature.getHealth().getDefaultValue() - creature.getCurrentHealth();
        double health = creature.getHealth().getDefaultValue() * Constants.REGENERATION_STATUS_EFFECT_MULTIPLIER;
        if (health > maxRegeneration) health = maxRegeneration;
        creature.setCurrentHealth(creature.getCurrentHealth() + health);
        UI.print(messageString + ": " + Util.round(health) + " HP regenerated\n");
        durationLeft--;
        if (durationLeft == 0) toRemove = true;
    }
}
