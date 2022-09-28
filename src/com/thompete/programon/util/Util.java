package com.thompete.programon.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Util {

    public static int randomInRange(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static double randomInRange(double min, double max) {
        return (new Random().nextDouble() * (max - min + 1)) + min;
    }

    public static double deviate(double value, double deviation) {
        return randomInRange(value - value * deviation, value + value * deviation);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double round(double value) {
        return round(value, 2);
    }
}





















