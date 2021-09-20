import java.util.Scanner;

/**
 * Meny er en klase som brukes til å gi en valgmeny til brukeren gjennom
 * terminalvinduet.
 * 
 * @author Sebastian Bugge
 */
class Meny {
    static private final String FEILMELDING = "Valget stemmer ikke overens med alternativene...\nVenligst prøv på nytt!";
    static private final String FEILMELDING_INT = "Venligst skriv inn et heltall...";
    static private final String FEILMELDING_DOUBLE = "Venligst skriv inn et desimaltall";
    private String spørsmål;
    private String[] valg;

    /**
     * Stiller brukeren et spørsmål og returnerer svaret.
     * 
     * @param spørsmål Spørsmålet som stilles brukeren.
     * @param scanner  Scanneren som brukes (vanligvis new Scanner(System.in)).
     * @return Svaret brukeren gir.
     */
    static public String spørString(String spørsmål, Scanner scanner) {
        System.out.println(spørsmål);
        String svar = scanner.nextLine();
        return svar;
    }

    /**
     * Stiller brukeren et spørsmål og returnerer svaret.
     * 
     * @param spørsmål Spørsmålet som stilles brukeren.
     * @param scanner  Scanneren som brukes (vanligvis new Scanner(System.in)).
     * @return Svaret brukeren gir funksjonen sørger for at brukeren taster inn et
     *         heltall.
     */
    static public int spørInt(String spørsmål, Scanner scanner) {
        System.out.println(spørsmål);
        if (scanner.hasNextInt()) {
            return lesHeltall(scanner);
        } else {
            System.out.println(FEILMELDING_INT);
            scanner.next();
            return spørInt(spørsmål, scanner);
        }
    }

    /**
     * Stiller brukeren et spørsmål og returnerer svaret innenfor et intervall,
     * [min, max>.
     * 
     * @param spørsmål Spørsmålet som stilles brukeren.
     * @param min      Minste verdi et gyldig svar kan ha.
     * @param max      Største verdi et gyldig svar kan ha (not-inclusive).
     * @param scanner  Scanneren som brukes (vanligvis new Scanner(System.in)).
     * @return Svaret brukeren gir i intervallet [min, max>. Funksjonen sørger for
     *         at brukeren taster inn et heltall.
     */
    static public int spørInt(String spørsmål, int min, int max, Scanner scanner) {
        int svar = spørInt(spørsmål, scanner);
        for (; svar < min || max <= svar; svar = spørInt(spørsmål, scanner)) {
            System.out.printf("Svaret må finnes i intervallet [%d, %d>\n", min, max);
        }
        return svar;
    }

    /**
     * Stiller brukeren et spørsmål og returnerer svaret.
     * 
     * @param spørsmål Spørsmålet som stilles brukeren.
     * @param scanner  Scanneren som brukes (vanligvis new Scanner(System.in)).
     * @return Svaret brukeren gir funksjonen sørger for at brukeren taster inn et
     *         desimaltall.
     */
    static public double spørDouble(String spørsmål, Scanner scanner) {
        System.out.println(spørsmål);
        if (scanner.hasNextDouble()) {
            return lesDouble(scanner);
        } else {
            System.out.println(FEILMELDING_DOUBLE);
            scanner.next();
            return spørDouble(spørsmål, scanner);
        }
    }

    /**
     * Stiller brukeren et spørsmål og returnerer svaret innenfor et intervall,
     * [min, max].
     * 
     * @param spørsmål Spørsmålet som stilles brukeren.
     * @param min      Minste verdi et gyldig svar kan ha.
     * @param max      Største verdi et gyldig svar kan ha.
     * @param scanner  Scanneren som brukes (vanligvis new Scanner(System.in)).
     * @return Svaret brukeren gir i intervallet [min, max]. Funksjonen sørger for
     *         at brukeren taster inn et desimaltall.
     */
    static public double spørDouble(String spørsmål, double min, double max, Scanner scanner) {
        double svar = spørDouble(spørsmål, scanner);
        for (; svar < min || max <= svar; svar = spørDouble(spørsmål, scanner)) {
            System.out.printf("Svaret må finnes i intervallet [%d, %d>\n", min, max);
        }
        return svar;
    }

    /**
     * Stiller brukeren et spørsmål med et binært svar.
     * 
     * @param spørsmål   Spørsmålet som blir stilt brukeren.
     * @param sanneSvar  Svaralternativer som gir {@code true}.
     * @param usanneSvar Svaralternativer som gir {@code false}.
     * @param scanner    Scanneren som brukes (vanligvis new Scanner(System.in)).
     * @return Gir en boolean verdi som samsvarer med svaret brukeren skrev inn.
     */
    static public boolean spørBool(String spørsmål, String[] sanneSvar, String[] usanneSvar, Scanner scanner) {
        for (String svar;;) {

            System.out.println(spørsmål);
            svar = scanner.nextLine();

            // Sjekker om svaret er et av mulige gitte usanne svaralternativer.
            for (String usantSvar : usanneSvar) {
                if (usantSvar.toLowerCase().equals(svar.toLowerCase())) {
                    return false;
                }
            }

            // Sjekker om svaret er et av mulige gitte sanne svaralternativer.
            for (String santSvar : sanneSvar) {
                if (santSvar.toLowerCase().equals(svar.toLowerCase())) {
                    return true;
                }
            }

            // Hvis svaret ikke stemmer overens med gitte svaralternativer, gis en
            // feilmelding og brukeren blir pressentert med gyldig inndata.
            System.out.println(FEILMELDING);
            System.out.print("Mulige sanne svar er: [");
            for (String santSvar : sanneSvar) {
                System.out.print(santSvar.toLowerCase() + ", ");
            }
            System.out.print("]. Mulige usanne svar er: [");
            for (String usantSvar : usanneSvar) {
                System.out.print(usantSvar.toLowerCase() + ", ");
            }
            System.out.println("]");
        }

    }

    /**
     * Stiller brukeren et spørsmål med et binært svar. Svaralternativene er [ja, j]
     * og [nei, n].
     * 
     * @param spørsmål Spørsmålet som blir stilt brukeren.
     * @param scanner  Scanneren som brukes (vanligvis new Scanner(System.in)).
     * @return Gir en boolean verdi som samsvarer med svaret brukeren skrev inn.
     */
    static public boolean spørBool(String spørsmål, Scanner scanner) {
        String[] sanneSvar = new String[] { "ja", "j" };
        String[] usanneSvar = new String[] { "nei", "n" };
        return spørBool(spørsmål, sanneSvar, usanneSvar, scanner);
    }

    static public int lesHeltall(Scanner scanner) {
        int out = scanner.nextInt();
        scanner.nextLine();
        return out;
    }

    static public double lesDouble(Scanner scanner) {
        double out = scanner.nextDouble();
        scanner.nextLine();
        return out;
    }

    /**
     * Konstruktør for Menyklassen. Her spesifiserer du spørsmålet du vil stille
     * brukeren og valgene de har. Vanlig prosedyre er å benytte "Avslutt",
     * "Tilbake" eller tilsvarende for siste svaralternativ.
     * 
     * @param spørsmål Spørsmålet som skal stilles brukeren
     * @param valg     Svaralternativene brukeren får
     */
    public Meny(String spørsmål, String... valg) {
        this.spørsmål = spørsmål;
        this.valg = valg;
    }

    /**
     * @return Returnerer indeksen til siste svaralternativ (1-indeksert)
     */
    public int getSisteValgIndeks() {
        return valg.length;
    }

    /**
     * Printer ut en meny der brukeren blir stilt et spørsmål og gir brukeren
     * svaralternativer. Spørsmålet og svaralternativene samsvarer med det som ble
     * angit i konstruksjonen av instansen.
     * 
     * @param scanner Scanner som leser svaret brukeren gir (scanneren bør være av
     *                typen {@code new Scanner(System.in)})
     * @return Returnerer et svar som samsvarer med hva brukeren valgte
     *         (1-indeksert)
     */
    public int promptSvar(Scanner scanner) {
        int tall;
        boolean førsteLoop = true;
        do {
            clearScreen();

            // Printer menyen:
            System.out.println(spørsmål);
            for (int i = 0; i < valg.length; i++) {
                System.out.println((i + 1) + ". " + valg[i]);
            }

            // Viser feilmeldingen hvis mer enn en løkke var nødvendig:
            if (!førsteLoop) {
                System.out.println(FEILMELDING);
            }

            // Hvis brukeren ikke taster inn et heltall, blir tallet satt til -1 slik at
            // betingelsene for løkken ikke blir møtt.
            if (scanner.hasNextInt()) {
                tall = lesHeltall(scanner);
            } else {
                scanner.next(); // nullstiller scanneren
                tall = -1;
            }

            førsteLoop = false;
        } while (tall < 1 || valg.length < tall);

        return tall;
    }

    // Fjerner alt fra terminalvinduet
    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}