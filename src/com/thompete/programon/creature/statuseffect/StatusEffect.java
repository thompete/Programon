package com.thompete.programon.creature.statuseffect;

import com.thompete.programon.Constants;
import com.thompete.programon.creature.Creature;
import com.thompete.programon.util.Util;

import java.io.Serializable;

public abstract class StatusEffect implements Serializable {

    public enum Type {
        STUMBLE, REGENERATION, FIRE, FROST, PROTECTION, WEAKNESS
    }

    protected Type type;
    protected int duration, durationLeft;
    protected Creature creature;
    protected boolean toRemove;

    protected final String messageString;


    public StatusEffect(Type type, int duration, Creature creature) {
        this.type = type;
        this.duration = duration;
        this.durationLeft = duration;
        this.creature = creature;
        toRemove = false;
        messageString = creature.getName() + " has status effect " + getName();
    }

    public static int getDuration() {
        int duration = Constants.STATUS_EFFECT_DURATION;
        double roll = Util.randomInRange(0, 100);
        if (roll <= Constants.VERY_LONG_STATUS_EFFECT_CHANCE) duration = Constants.VERY_LONG_STATUS_EFFECT_DURATION;
        else if (roll <= Constants.LONG_STATUS_EFFECT_CHANCE) duration = Constants.LONG_STATUS_EFFECT_DURATION;
        return duration;
    }

    public abstract void resolve(boolean sourceIsPlayer);

    public String getName() {
        return type.name();
    }

    public boolean isToRemove() {
        return toRemove;
    }
}
