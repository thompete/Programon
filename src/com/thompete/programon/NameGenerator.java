package com.thompete.programon;

import com.thompete.programon.util.Util;
import java.util.List;

public class NameGenerator {

    private int minLenth, maxLenght;
    private List<String> vowels;
    private List<String> consonants;

    public NameGenerator(int minLenth, int maxLenght, List<String> vowels, List<String> consonants) {
        this.minLenth = minLenth;
        this.maxLenght = maxLenght;
        this.vowels = vowels;
        this.consonants = consonants;
    }

    public NameGenerator(int minLenth, int maxLenght) {
        this(
                minLenth, maxLenght,
                List.of("a", "e", "i", "o", "u"),
                List.of(
                        "b", "d", "f", "g", "h", "j", "k", "l", "m",
                        "n", "p", "r", "s", "t", "v", "w", "y", "z", "ch", "sh"
                )
        );
    }

    public String getName() {
        StringBuilder name = new StringBuilder();

        int length = Util.randomInRange(minLenth, maxLenght);
        for (int i = 0; i < length; i++) {
            name.append(getSyllable());
        }
        name = new StringBuilder(name.substring(0, 1).toUpperCase() + name.substring(1));

        return name.toString();
    }

    private String getSyllable() {
        StringBuilder syllable = new StringBuilder();

        int roll = Util.randomInRange(1, 100);
        if (roll < Constants.V_FREQ) {
            int index = Util.randomInRange(0, vowels.size() - 1);
            syllable.append(vowels.get(index));
        } else {
            roll = Util.randomInRange(1, 100);
            if (roll < Constants.VCCC_FREQ) {
                int roll2 = Util.randomInRange(1, 100);
                if (roll2 < Constants.REV_FREQ) {
                    int index = Util.randomInRange(0, vowels.size() - 1);
                    syllable.append(vowels.get(index));
                    for (int i = 0; i < 3; i++) {
                        index = Util.randomInRange(0, consonants.size() - 1);
                        syllable.append(consonants.get(index));
                    }
                } else {
                    int index;
                    for (int i = 0; i < 3; i++) {
                        index = Util.randomInRange(0, consonants.size() - 1);
                        syllable.append(consonants.get(index));
                    }
                    index = Util.randomInRange(0, vowels.size() - 1);
                    syllable.append(vowels.get(index));
                }
            } else if (roll < Constants.VCC_FREQ) {
                int roll2 = Util.randomInRange(1, 100);
                if (roll2 < Constants.REV_FREQ) {
                    int index = Util.randomInRange(0, vowels.size() - 1);
                    syllable.append(vowels.get(index));
                    for (int i = 0; i < 2; i++) {
                        index = Util.randomInRange(0, consonants.size() - 1);
                        syllable.append(consonants.get(index));
                    }
                } else {
                    int index;
                    for (int i = 0; i < 2; i++) {
                        index = Util.randomInRange(0, consonants.size() - 1);
                        syllable.append(consonants.get(index));
                    }
                    index = Util.randomInRange(0, vowels.size() - 1);
                    syllable.append(vowels.get(index));
                }
            } else {
                int roll2 = Util.randomInRange(1, 100);
                if (roll2 < Constants.REV_FREQ) {
                    int index = Util.randomInRange(0, vowels.size() - 1);
                    syllable.append(vowels.get(index));
                    index = Util.randomInRange(0, consonants.size() - 1);
                    syllable.append(consonants.get(index));
                } else {
                    int index = Util.randomInRange(0, consonants.size() - 1);
                    syllable.append(consonants.get(index));
                    index = Util.randomInRange(0, vowels.size() - 1);
                    syllable.append(vowels.get(index));
                }
            }
        }

        return syllable.toString();
    }
}













