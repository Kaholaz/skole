import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class LeggTilArbTaker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Meny meny = new Meny("Hva slags informasjon har du om den nye arbeidstakeren?",
                "Fornavn, etternavn og fødselsår", "Fornavn, etternavn, fødselsår og ansettelsesår",
                "Fornavn, etternavn, fødselsår, månedslønn og skatteprosent",
                "Fornavn, etternavn, fødselsår, ansettelsesår, månedslønn og skatteprosent", "Avslutt");

        // Nåværende år
        int currentYear = new GregorianCalendar().get(Calendar.YEAR);

        // Presenterer menyen for brukeren og lagrer svaret deres i valg variabelen. Så
        // lenge valget ikke er avslutt, kjøres dette i loop. Spør om nytt svar hver
        // loop.
        for (int valg = meny.promptSvar(sc); valg != meny.getSisteValgIndeks(); valg = meny.promptSvar(sc)) {
            String fornavn = Meny.spørString("Hva er fornavnet til arbeidstakeren?", sc);
            String etternavn = Meny.spørString("Hva er etternavnet til arbeidstakeren?", sc);
            int fødselsår = Meny.spørInt("Hvilket år ble arbeidstakeren født?", 1900, currentYear + 1, sc);

            // initialiserer variabler og fyller dem med standardverdier.
            int ansettelsesÅr = ArbTaker.ANSETTELSESÅR_DEFAULT;
            int månedsLønn = ArbTaker.MÅNEDSLØNN_DEFAULT;
            double skatteAndel = ArbTaker.SKATTEANDEL_DEFAULT;

            // Spør om detaljer hvis brukeren spesifiserte at de hadde disse.
            if (valg == 2 || valg == 4) {
                ansettelsesÅr = Meny.spørInt("Hvilket år startet arbeidstakeren?", 1900, currentYear + 1, sc);
            }

            if (valg == 3 || valg == 4) {
                månedsLønn = Meny.spørInt("Hva er månedslønnen til den ansatte? (brutto)", 0, Integer.MAX_VALUE, sc);
                skatteAndel = Meny.spørDouble("Hva er skatteandelen? (fra 0.0 til 1.0)", 0, 1, sc);
            }

            // Opretter en ny ansatt med de gitte detaljene.
            ArbTaker nyAnsatt = new ArbTaker(etternavn, fornavn, fødselsår, ansettelsesÅr, månedsLønn, skatteAndel);

            // Spør brukeren om de er sikre på det de har skrevet inn
            System.out.println("Den ansatte du har lagt inn har følgende detaljer:");
            System.out.println(nyAnsatt);
            boolean lagre = Meny.spørBool("Stemmer detaljene og ønsker du å lagre dem? [J/N]", sc);

            if (lagre) {
                // Database.leggTil(nyAnsatt);
                System.out.println("Arbeidstakeren er lagt til.");
                sc.next();
            }
        }
    }
}