package com.thompete.programon;

import com.thompete.programon.util.Util;

public class Statistics {

    private static double damageDealt, damageReceived;
    private static int faintsDealt, faintsReceived, roundsWon;

    public static void increaseDamageDealt(double damage) {
        damageDealt += damage;
    }

    public static void increaseDamageReceived(double damage) {
        damageReceived += damage;
    }

    public static void increaseFaintsDealt() {
        faintsDealt++;
    }

    public static void increaseFaintsReceived() {
        faintsReceived++;
    }

    public static void increaseRoundsWon() {
        roundsWon++;
    }

    public static void reset() {
        damageDealt = damageReceived = 0;
        faintsDealt = faintsReceived = roundsWon = 0;
    }

    public static String get() {
        return "Your statistics:\n" +
                "Damage dealt: " + Util.round(damageDealt) + "\n" +
                "Damage received: " + Util.round(damageReceived) + "\n" +
                "Faints dealt: " + faintsDealt + "\n" +
                "Faints received: " + faintsReceived + "\n" +
                "Rounds won: " + roundsWon + "\n";
    }
}
