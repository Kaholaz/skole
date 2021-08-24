import java.util.Scanner;

/**
 * Oppgave 1 Skriv et program som regner om fra tommer til centimeter. En tomme
 * er 2,54 centimeter. Sett opp testdatasett og prøv ut programmet.
 */
public class oppgave1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hvor mange tommer?");
        double tommer = sc.nextDouble();
        sc.close();
        double cm = tommer * 2.54;

        System.out.println(tommer + " tommer = " + cm + " cm");

    }
}