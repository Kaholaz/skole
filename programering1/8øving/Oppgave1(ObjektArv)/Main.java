import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArbTaker nyAnsatt;
        int currentYear = new GregorianCalendar().get(Calendar.YEAR);
        Scanner sc = new Scanner(System.in);

        while (true) {
            int arbTakerNr = Meny.spørInt("Hva er arbeidstaker nummeret til arbeidstakeren?", sc);
            String fornavn = Meny.spørString("Hva er fornavnet til arbeidstakeren?", sc);
            String etternavn = Meny.spørString("Hva er etternavnet til arbeidstakeren?", sc);
            int fødselsår = Meny.spørInt("Hvilket år ble arbeidstakeren født?", 1900, currentYear + 1, sc);
            int ansettelsesÅr = Meny.spørInt("Hvilket år startet arbeidstakeren?", 1900, currentYear + 1, sc);
            int månedsLønn = Meny.spørInt("Hva er månedslønnen til den ansatte? (brutto)", 0, Integer.MAX_VALUE, sc);
            double skatteAndel = Meny.spørDouble("Hva er skatteandelen? (fra 0.0 til 1.0)", 0, 1, sc);

            nyAnsatt = new ArbTaker(arbTakerNr, etternavn, fornavn, fødselsår, ansettelsesÅr, månedsLønn, skatteAndel);

            // Spør brukeren om de er sikre på det de har skrevet inn
            System.out.println("Den ansatte du har lagt inn har følgende detaljer:");

            System.out.println("Etternavn: " + nyAnsatt.getEtternavn());
            System.out.println("Fornavn: " + nyAnsatt.getFornavn());
            System.out.println("Fullt navn: " + nyAnsatt.getFulltNavn());
            System.out.println("Fødselsår: " + nyAnsatt.getFødselsår());
            System.out.println("Alder: " + nyAnsatt.getAlder());

            System.out.println("Arbeidstaker-nummer: " + nyAnsatt.getArbTakerNr());

            System.out.println("Ansettelsesår: " + nyAnsatt.getAnsettelsesÅr());
            System.out.println("Antall år ansatt: " + nyAnsatt.getAntallÅrAnsatt());
            System.out.println((nyAnsatt.harVærtAnsattMerEnn(10)) ? "Den ansatte har vært ansatt i mer en 10 år"
                    : "Den ansatte har ikke vært ansatt i mer en 10 år");

            System.out.println("Månedslønn: " + nyAnsatt.getMånedsLønn());
            System.out.println("Bruttolønn: " + nyAnsatt.getBruttolønn());
            System.out.println("Skatteandel: " + nyAnsatt.getSkatteAndel());
            System.out.println("Skatt pr. måned: " + nyAnsatt.getSkattPrMåned());
            System.out.println("Skatt pr. år: " + nyAnsatt.getSkattPrÅr());

            if (Meny.spørBool("Er du tilfreds med inndataen? [j/n]", sc)) {
                break;
            }
        }

        Meny endreMeny = new Meny("Hva ønsker du å endre?", "Månedslønn", "Skatteandel", "Avslutt");
        int valg;
        while (true) {
            valg = endreMeny.promptSvar(sc);

            if (valg == endreMeny.getSisteValgIndeks()) { // avslutt ble valgt
                break;
            }

            switch (valg) {
                case 1: // månedslønn
                    nyAnsatt.setMånedsLønn(
                            Meny.spørInt("Hva skal den nye månedslønnen være?", 0, Integer.MAX_VALUE, sc));
                    break;
                case 2: // skatteandel
                    nyAnsatt.setSkatteAndel(Meny.spørDouble("Hva skal den nye skatteprosenten være?", 0.0, 1.0, sc));
                    break;
            }

            System.out.println("Dataen til den ansatte ser nå slik ut:\n" + nyAnsatt.toString());
            sc.next();
        }
    }
}
