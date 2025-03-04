package org.example;

public class ColoredText {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    public static void printColoredText(String text, String color) {
        switch (color.toLowerCase()) {
            case "red":
                System.out.println(RED + text + RESET);
                break;
            case "green":
                System.out.println(GREEN + text + RESET);
                break;
            case "yellow":
                System.out.println(YELLOW + text + RESET);
                break;
            case "blue":
                System.out.println(BLUE + text + RESET);
                break;
            default:
                System.out.println(text);
        }
    }
}
