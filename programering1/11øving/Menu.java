import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private String question;
    private String[] options;

    /**
     * Awaits input from the user and makes sure the user inputs an iteger.
     * 
     * @param scanner The scanner object used retrive user input.
     * @return The number the user entered.
     */
    public static int promptInt(Scanner scanner) {
        int out;
        if (scanner.hasNextInt()) {
            out = scanner.nextInt();
            scanner.nextLine(); // gets rid of the unwanted newline character at the end of the user input
        } else {
            System.out.println("Please submit an integer!");
            scanner.next(); // gets rid of the unwanted user input.
            out = promptInt(scanner);
        }
        return out;
    }

    /**
     * Awaits input from the user and makes sure the user inputs an iteger between
     * min (inclusive) and max (exclusive).
     * 
     * @param scanner The scanner object used to retrive user input.
     * @param min     The lower bound for the user input (inclusive).
     * @param max     The upper bound for hte user input (exclusive).
     * @return The number the user entered.
     */
    public static int promptInt(Scanner scanner, int min, int max) {
        int out = promptInt(scanner);

        // makes sure that the final output is between min and max
        while (out < min || max <= out) {
            System.out.println(out + " is not a valid option...");
            out = promptInt(scanner);
        }

        return out;
    }

    /**
     * Awaits input from the user and makes sure the user inputs a floating point
     * descimal number.
     * 
     * @param scanner The scanner object used retrive user input.
     * @return The number the user entered.
     */
    public static float promptFloat(Scanner scanner) {
        float out;
        if (scanner.hasNextFloat()) {
            out = scanner.nextFloat();
            scanner.nextLine(); // gets rid of the unwanted newline character at the end of the user input
        } else {
            System.out.println("Please submit a floating point descimal number!");
            scanner.next(); // gets rid of the unwanted user input.
            out = promptFloat(scanner);
        }
        return out;
    }

    /**
     * Prompts the user with a question and makes sure the answer given is an
     * integer.
     * 
     * @param scanner  The scanner object used retrive user input.
     * @param question The question the user is prompted with.
     * @return An integer givern by the user.
     */
    public static int askInt(Scanner scanner, String question) {
        System.out.println(question);
        return promptInt(scanner);
    }

    /**
     * Prompts the user with a question and makes sure the answer given is a
     * floating point descimal number.
     * 
     * @param scanner  The scanner object used retrive user input.
     * @param question The question the user is prompted with.
     * @return A float given by the user.
     */
    public static float askFloat(Scanner scanner, String question) {
        System.out.println(question);
        return promptFloat(scanner);
    }

    /**
     * Prompts the user with a question and returns the ansewer given by the user.
     * 
     * @param scanner  The scanner object used retrive user input.
     * @param question The question the user is prompted with.
     * @return A string given by the user.
     */
    public static String askString(Scanner scanner, String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Constructor used for an instance of the Menu object. Takes a scanner, the
     * qestion you want to prompt the user with, and the options the user should be
     * presented with.
     * 
     * @param scanner  The scanner object used to retrive user input.
     * @param question The question you want to prompt the user with.
     * @param options  The options the user is given.
     */
    public Menu(Scanner scanner, String question, String... options) {
        this.scanner = scanner;
        this.question = question;
        this.options = options;
    }

    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     */
    public int promptUser() {
        System.out.println(question);

        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s\n", i + 1, options[i]);
        }

        return Menu.promptInt(scanner, 1, options.length + 1);
    }
}
