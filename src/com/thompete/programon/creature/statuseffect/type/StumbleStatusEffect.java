package com.thompete.programon.creature.statuseffect.type;

import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class StumbleStatusEffect extends StatusEffect {

    public StumbleStatusEffect(int duration, Creature creature) {
        super(Type.STUMBLE, duration, creature);
    }

    @Override
    public void resolve(boolean sourceIsPlayer) {
        if (durationLeft == 0 || creature.isFainted()) return;
        double agility = creature.getAgility().getDefaultValue() * Constants.STUMBLE_STATUS_EFFECT_MULTIPLIER;
        double targetAgility = creature.getAgility().getCurrentValue() - agility;
        if (targetAgility < 0) agility = creature.getAgility().getCurrentValue();

        if (durationLeft == duration) creature.setCurrentAgility(creature.getAgility().getCurrentValue() - agility);

        UI.print(messageString + ": agility temporarily decreased\n");
        durationLeft--;
        if (durationLeft == 0) {
            creature.setCurrentAgility(creature.getAgility().getCurrentValue() + agility);
            toRemove = true;
        }
    }
}
