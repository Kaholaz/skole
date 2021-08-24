import java.util.Scanner;

/**
 * Skriv et program som regner om timer, minutter og sekunder til totalt antall
 * sekunder. Sett opp testdatasett og prøv ut programmet.
 */
public class oppgave2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hvor mange timer?");
        double timer = sc.nextDouble();
        System.out.println("Hvor mange minutter?");
        double minutter = sc.nextDouble();
        System.out.println("Hvor mange sekunder?");
        double sekunder = sc.nextDouble();
        sc.close();

        double totaleSekunder = timer * 60 * 60 + minutter * 60 + sekunder;
        System.out.println("Den totale mengden sekunder er " + totaleSekunder);
    }
}
