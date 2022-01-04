import java.util.Scanner;

/**
 * Lag et program som skriver ut en del av multiplikasjonstabellen. Brukeren
 * skal velge hvilken del av tabellen som skal skrives ut.
 */
public class oppgave1 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int start, slutt;

        while (true) {
            System.out.println("Fra hvilket tall vil du ha gange-tabellen?");
            start = fromScannerGetInt();
            System.out.println("Til hvilket tall vil du ha gange-tabellen?");
            slutt = fromScannerGetInt();

            if (start <= slutt) {
                sc.close();
                break;
            } else {
                System.out.println("\nStartverdien må være mindre eller lik sluttverdien...");
                System.out.println("Venligs prøv på nytt!\n");
            }
        }

        for (int tall = start; tall <= slutt; tall++) {
            System.out.println(tall + "-gangen:");
            for (int i = 1; i <= 10; i++) {
                System.out.println(tall + " x " + i + " = " + (tall * i));
            }
        }
    }

    static int fromScannerGetInt() {
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                sc.next();
                System.out.println("Venligst skriv inn et heltall.");
            }
        }
    }
}