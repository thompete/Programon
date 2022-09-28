package com.thompete.programon.creature.statuseffect.type;

import com.thompete.programon.UI;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.creature.statuseffect.StatusEffect;

public class FrostStatusEffect extends StatusEffect {

    public FrostStatusEffect(int duration, Creature creature) {
        super(Type.FROST, duration, creature);
    }

    @Override
    public void resolve(boolean sourceIsPlayer) {
        if (durationLeft == 0 || creature.isFainted()) return;

        if (durationLeft == duration) creature.setCanMove(false);

        UI.print(messageString + ": can't move\n");
        durationLeft--;
        if (durationLeft == 0) {
            creature.setCanMove(true);
            toRemove = true;
        }
    }
}
