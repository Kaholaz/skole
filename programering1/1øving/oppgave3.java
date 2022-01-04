import java.util.Scanner;

/**
 * Skriv et program som leser inn et antall sekunder og beregner hvor mange
 * timer, minutter og sekunder dette er. (Hint: Bruk heltallsdivisjon.) Sett opp
 * testdatasett og prøv ut programmet.
 */
public class oppgave3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hvor mange sekunder?");
        double sekunder = sc.nextDouble();
        sc.close();

        double resterendeSekunder = sekunder;
        int timer = (int) resterendeSekunder / (60 * 60);
        resterendeSekunder = resterendeSekunder % (60 * 60);
        int minutter = (int) resterendeSekunder / 60;
        resterendeSekunder = sekunder % 60;

        System.out.println(sekunder + " sekunder er:");
        System.out.println(timer + " timer");
        System.out.println(minutter + " minutter");
        System.out.println(resterendeSekunder + " sekunder");
    }
}
