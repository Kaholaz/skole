import java.util.Scanner;

/**
 * Lag en klasse Valuta med minst en konstruktør. Klassen skal ha metoder for å
 * regne fra og til norske kroner.
 * 
 * Lag et klientprogram som oppretter minst tre objekter som representerer
 * forskjellige valutaer. Brukeren skal få tilbud om å regne om flere ulike
 * beløp i de forskjellige valutaene til norske kroner.
 */
public class oppgave1 {
    private static final String FEILMELDING = "Valget stemmer ikke overens med alternativene...\nVenligst prøv på nytt!";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Valuta[] valutaer = { new Valuta(0.11, "US Dollar", "USD"), new Valuta(0.096, "Euro", "EUR"),
                new Valuta(0.99, "Svenske Kroner", "SEK") };

        while (true) {
            String[] valutaerNavn = new String[valutaer.length];
            for (int i = 0; i < valutaer.length; i++) {
                valutaerNavn[i] = valutaer[i].navn;
            }

            int valgt = meny("Hvilken valuta ønsker du å konvertere til/fra?", valutaerNavn, "Avslutt");
            if (valgt == valutaer.length + 1) { // Avslutt ble valgt
                break;
            } else {
                Valuta valuta = valutaer[valgt - 1];
                valgt = meny("Ønsker du å konvertere til eller fra " + valuta.navn.toLowerCase(), // Gir tre valg: fra
                                                                                                  // NOK, til NOK og
                                                                                                  // tilbake
                        new String[] { "Fra " + valuta.navn.toLowerCase() + " til norske kroner",
                                "Fra norske kroner til " + valuta.navn.toLowerCase() },
                        "Tilbake");
                if (valgt == 3) { // tilbake ble valgt
                    continue;
                }
                double penger;
                {
                    boolean feil = false;
                    while (true) {
                        System.out.println("Angi hvor mye penger du har:");
                        if (feil) {
                            System.out.println(FEILMELDING);
                        }
                        if (sc.hasNextDouble()) {
                            penger = sc.nextDouble();
                            break;
                        } else {
                            sc.next();
                            feil = true;
                            continue;
                        }
                    }
                }
                clearScreen();
                if (valgt == 1) { // Formaterer som f.eks slik 32.89 NOK er 3.21 USD med to gjeldende desimaler.
                    System.out.printf("%.2f %s er %.2f %s\n", penger, valuta.symbol, valuta.toNok(penger), "NOK");
                } else {
                    System.out.printf("%.2f %s er %.2f %s\n", penger, "NOK", valuta.fromNok(penger), valuta.symbol);
                }
                sc.next(); // venter på at bruekeren skal trykke noe før programmet hopper opp igjen
            }
        }
        sc.close();
    }

    /**
     * Denne funksjonen viser en valgmeny for brukeren og validerer brukerinputet.
     * 
     * @param spørsmål Spørsmålet som printes øverst i valgmenyen
     * @param valg     Dette er svaralternativene
     * @return Retuernerer et heltall som korresponderer med svaralternativene der 1
     *         er det første alternativet.
     */
    static int meny(String spørsmål, String[] valg) {
        int tall;
        boolean fail = false;
        while (true) {
            clearScreen();
            System.out.println(spørsmål);
            for (int i = 0; i < valg.length; i++) {
                System.out.println((i + 1) + ". " + valg[i]);
            }
            if (fail) {
                System.out.println(FEILMELDING);
            }

            if (sc.hasNextInt()) { // Hvis brukeren ikke taster inn et heltall, blir tallet satt til -1 slik at
                                   // brukeren blir vist feilmeldingen nedenfor.
                tall = sc.nextInt();
            } else {
                sc.next();
                tall = -1;
            }

            if (tall < 1 || tall > valg.length) {
                fail = true;
                continue;
            }
            break;
        }
        return tall;
    }

    /**
     * Denne funksjonen viser en valgmeny for brukeren og validerer brukerinputet.
     * 
     * @param spørsmål  Spørsmålet som printes øverst i valgmenyen
     * @param valg      Dette er svaralternativene
     * @param sisteValg Åpner muligheten for å legge til et siste svaralternativ,
     *                  gjerne 'Avslutt' eller 'Tilbake'
     * @return Retuernerer et heltall som korresponderer med svaralternativene der 1
     *         er det første alternativet.
     */
    static int meny(String spørsmål, String[] valg, String sisteValg) {
        String[] _valg = new String[valg.length + 1];
        for (int i = 0; i < valg.length; i++) {
            _valg[i] = valg[i];
        }
        _valg[valg.length] = sisteValg;
        return meny(spørsmål, _valg);
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Valuta er en klasse som kan brukes til konvertere fra og til en spesifisert
     * valuta til og fra norske kroner. Verdi er hvor mye en norsk krone er vert i
     * den spesifiserte valutaen. Legg også inn navn og symbol.
     */
    static class Valuta {
        double verdi;
        String navn, symbol;

        Valuta(double verdi_, String navn_, String symbol_) {
            verdi = verdi_;
            navn = navn_;
            symbol = symbol_;
        }

        double toNok(double penger) {
            return penger / verdi;
        }

        double fromNok(double penger) {
            return penger * verdi;
        }
    }
}