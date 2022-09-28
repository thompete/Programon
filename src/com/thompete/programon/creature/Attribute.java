package com.thompete.programon.creature;

import com.thompete.programon.util.Util;

import java.io.Serializable;

public class Attribute implements Serializable {

    private String name;
    private double defaultValue, currentValue;
    private boolean isModifiableByPlayer;

    public Attribute(String name, double defaultValue, boolean isModifiableByPlayer) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.isModifiableByPlayer = isModifiableByPlayer;
        currentValue = defaultValue;
    }

    public Attribute(String name, double defaultValue) {
        this(name, defaultValue, true);
    }

    public String getName() {
        return name;
    }

    public double getDefaultValue() {
        return defaultValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public boolean isModifiableByPlayer() {
        return isModifiableByPlayer;
    }

    public void addToDefault(double value) {
        defaultValue += value;
    }

    public void subtractFromDefault(double value) {
        defaultValue -= value;
    }

    public void multiplyDefault(double value) {
        defaultValue *= value;
    }

    public void divideDefault(double value) {
        defaultValue /= value;
    }

    public void addToCurrent(double value) {
        currentValue += value;
    }

    public void subtractFromCurrent(double value) {
        currentValue -= value;
    }

    public void multiplyCurrent(double value) {
        currentValue *= value;
    }

    public void divideCurrent(double value) {
        currentValue /= value;
    }

    public void reset() {
        currentValue = defaultValue;
    }

    @Override
    public String toString() {
        return "" + Util.round(currentValue);
    }
}
















