package com.thompete.programon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void print(String message) {
        System.out.println(message);
    }

    public static String getInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getChoice(String message, List<String> options) {
        print(message);
        return getChoice(options);
    }

    public static int getChoice(List<String> options) {
        List<Integer> correctInputs = printList(options);

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (!correctInputs.contains(input)) {
            print("Please enter correct number!\n");
            printList(options);
            input = scanner.nextInt();
        }

        return input - 1;
    }

    private static List<Integer> printList(List<String> options) {
        List<Integer> correctInputs = new ArrayList<>();

        for (int i = 1; i <= options.size(); i++) {
            print(i + ". " + options.get(i - 1));
            correctInputs.add(i);
        }

        return correctInputs;
    }
}
