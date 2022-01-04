import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Oppgave1
 */
public class Oppgave1 {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("Dette programmet er et program der vi kan undersøke tilfeldige fordelinger.");
        int antTall = fåIntSvar("Hvor mange forskjellige tall vil du ha?");
        int antIterasjoner = fåIntSvar("Hvor mange iterasjoner vil du kjøre?");
        sc.close();

        int[] fordeling = new int[antTall]; // en indeks er et tall som kan bli valgt av tilfeldighetsgeneratoren
        for (int i = 0; i < antTall; i++) {
            fordeling[i] = 0;
        }

        for (int i = 0; i < antIterasjoner; i++) { // legger til én hver gang indeksen blir valgt
            int tall = rand.nextInt(antTall);
            fordeling[tall] += 1;
        }

        renderFordeling(fordeling);
    }

    // Stiller et spørsmål og gir et svar. Svaret er i intervallet svar > 0
    static int fåIntSvar(String spørsmål) {
        while (true) {
            System.out.println(spørsmål);

            if (sc.hasNextInt()) {
                int svar = sc.nextInt();
                if (svar > 0) {
                    return svar;
                }
            }

            sc.next();
            System.out.println("Din indata er ugyldig.. prøv på nytt!");
        }
    }

    static void renderFordeling(int[] fordeling) {
        int max = 0; // største tallet
        for (int i : fordeling) {
            max = Integer.max(max, i);
        }

        int maxDigits = beregnAntSiffer(max); // maks antall siffer
        int maxDigitsIndex = beregnAntSiffer(fordeling.length + 1); // maks siffer til indeksen
        int sum = Arrays.stream(fordeling).sum(); // sum av alle elementene (burde være det samme
                                                  // som antall iterasjoner)

        System.out.println("Fordelingen etter " + sum + " iterasjoner ser slik ut:");
        for (int i = 0; i < fordeling.length; i++) {
            int antallStjerner = 100;
            int antallFyllteStjener = (int) Math.round(antallStjerner * ((double) fordeling[i] / sum));

            // eks: 3 (indeksen eller tallet): 92 (antall ganger tallet ble valgt)
            // *************----- (søylen blir fylt etter forholdet mellom ganger tallet ble
            // valgt og antallet iterasjoner).
            System.out.printf("%" + maxDigitsIndex + "d: %" + maxDigits + "d %s\n", i + 1, fordeling[i],
                    propBar(antallFyllteStjener, antallStjerner));
        }
    }

    /**
     * @param num Antall ledd som skal være markert
     * @param max Totalt antall ledd i søylen
     * @return En fordeig utfylt søyle som visualiserer et forhold
     */
    static String propBar(int num, int max) { // git en søyle der det er %num% fylte bokser og %max% totale bokser.
        final String EMPTY_BOX = "─";
        final String FILLED_BOX = "■";

        if (num == 0) {
            return new String(new char[max]).replace("\0", EMPTY_BOX);
        }

        return new String(new char[num]).replace("\0", FILLED_BOX)
                + new String(new char[max - num]).replace("\0", EMPTY_BOX);

    }

    static int beregnAntSiffer(int num) { // gir antall siffer for %num%
        if (num == 0) {
            return 1;
        }
        return ((int) Math.log10(Math.abs(num))) + 1;
    }
}