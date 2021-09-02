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

        int[] fordeling = new int[antTall];
        for (int i = 0; i < antTall; i++) {
            fordeling[i] = 0;
        }

        for (int i = 0; i < antIterasjoner; i++) {
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
        int max = 0;
        for (int i : fordeling) {
            max = Integer.max(max, i);
        }
        int maxDigits = beregnAntSiffer(max);
        int maxDigitsIndex = beregnAntSiffer(fordeling.length + 1);
        int sum = Arrays.stream(fordeling).reduce(0, (a, b) -> a + b);

        System.out.println("Fordelingen etter " + sum + " iterasjoner ser slik ut:");
        for (int i = 0; i < fordeling.length; i++) {
            int nevner = 100;
            int prop = (int) Math.round(nevner * ((double) fordeling[i] / sum));
            System.out.printf("%" + maxDigitsIndex + "d: %" + maxDigits + "d %s\n", i + 1, fordeling[i],
                    propBar(prop, nevner));
        }
    }

    /**
     * @param num Antall ledd som skal være markert
     * @param max Totalt antall ledd i søylen
     * @return En fordeig utfylt søyle som visualiserer et forhold
     */
    static String propBar(int num, int max) {
        final String EMPTY_BOX = "─";
        final String FILLED_BOX = "■";

        if (num == 0) {
            return new String(new char[max]).replace("\0", EMPTY_BOX);
        }

        return new String(new char[num]).replace("\0", FILLED_BOX)
                + new String(new char[max - num]).replace("\0", EMPTY_BOX);

    }

    static int beregnAntSiffer(int num) {
        if (num == 0) {
            return 1;
        }
        return ((int) Math.log10(num)) + 1;
    }
}