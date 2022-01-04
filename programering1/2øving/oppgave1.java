import java.util.Scanner;

/**
 * Et år er skuddår dersom det er delelig med 4. Unntaket er hundreårene, de må
 * være delelig med 400. Tegn aktivitetsdiagram som viser algoritmen for å finne
 * ut om et år er skuddår. Årstallet skal leses inn fra brukeren. Sett opp
 * testdata. Lag og prøv ut programmet.
 */
public class oppgave1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hvilket år vil du undersøke om er skuddår?");
        int år = sc.nextInt();
        sc.close();

        boolean skuddår = erSkuddår(år);

        System.out.println(år + " er" + ((skuddår) ? "" : " ikke") + " et skuddår.");
    }

    static boolean erSkuddår(int år) {
        if (år % 4 == 0 && (år % 100 != 0 || år % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }
}
