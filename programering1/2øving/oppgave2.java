import java.util.Scanner;

/**
 * Lag et program som hjelper oss i forhold til følgende problemstilling:
 * Kjøttdeig av merke A koster kr 35,90 for 450 gram, mens kjøttdeig av merke B
 * koster kr 39,50 for 500 gram. Hvilket merke er billigst?
 */
public class oppgave2 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Kjøttdeig merkeA = new Kjøttdeig("merke A");
        Kjøttdeig merkeB = new Kjøttdeig("merke B");

        if (merkeA.kilopris > merkeB.kilopris) {
            System.out.println(merkeB.navn + " er billigst.");
        } else if (merkeA.kilopris < merkeB.kilopris) {
            System.out.println(merkeA.navn + " er billigst.");
        } else {
            System.out.println("Begge merkene er like billig!");
        }

        sc.close();
    }

    /**
     * Kjøttdeig er en klasse for å finne kiloprisen til kjøttdeig. Pris angis i
     * kroner og vekt angis i gram.
     */
    static class Kjøttdeig {
        double pris;
        double vekt;
        double kilopris;
        String navn;

        public Kjøttdeig(String navn) {
            this.navn = navn;
            System.out.println("Hvor mye koster " + navn);
            pris = sc.nextDouble();
            System.out.println("Hvor mange gram veier " + navn);
            vekt = sc.nextDouble();

            kilopris = (pris / vekt) * 1000;
        }
    }
}
