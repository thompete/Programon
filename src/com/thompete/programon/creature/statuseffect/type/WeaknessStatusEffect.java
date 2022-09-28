package com.thompete.programon.creature.statuseffect.type;

import com.thompete.programon.Constants;
import com.thompete.programon.UI;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class WeaknessStatusEffect extends StatusEffect {

    public WeaknessStatusEffect(int duration, Creature creature) {
        super(Type.WEAKNESS, duration, creature);
    }

    @Override
    public void resolve(boolean sourceIsPlayer) {
        if (durationLeft == 0 || creature.isFainted()) return;
        double power = creature.getPower().getDefaultValue() * Constants.WEAKNESS_STATUS_EFFECT_MULTIPLIER;
        double targetPower = creature.getPower().getCurrentValue() - power;
        if (targetPower < 0) power = creature.getAgility().getCurrentValue();

        if (duration == durationLeft) creature.setCurrentPower(creature.getPower().getCurrentValue() - power);

        UI.print(messageString + ": power temporarily decreased\n");
        durationLeft--;
        if (durationLeft == 0) {
            creature.setCurrentPower(creature.getPower().getCurrentValue() + power);
            toRemove = true;
        }
    }
}
