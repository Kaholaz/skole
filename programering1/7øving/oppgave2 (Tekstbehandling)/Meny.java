import java.util.Scanner;

/**
 * Meny er en klase som brukes til å gi en valgmeny til brukeren gjennom
 * terminalvinduet.
 * 
 * @author Sebastian Bugge
 */
class Meny {
    private final String FEILMELDING = "Valget stemmer ikke overens med alternativene...\nVenligst prøv på nytt!";
    private String spørsmål;
    private String[] valg;

    /**
     * Konstruktør for Menyklassen. Her spesifiserer du spørsmålet du vil stille
     * brukeren og valgene de har.
     * 
     * @param spørsmål Spørsmålet som skal stilles brukeren
     * @param valg     Svaralternativene brukeren får
     */
    public Meny(String spørsmål, String[] valg) {
        this.spørsmål = spørsmål;
        this.valg = valg;
    }

    /**
     * Konstruktør for Menyklassen. Her spesifiserer du spørsmålet du vil stille
     * brukeren og valgene de har. Her har du mulighet til å legge til et siste
     * svaralternativ som kommer på slutten av alle de andre svaralternativene.
     * 
     * @param spørsmål  Spørsmålet som skal stilles brukeren
     * @param valg      Svaralternativene brukeren får
     * @param sisteValg Siste svaralternativ
     */
    public Meny(String spørsmål, String[] valg, String sisteValg) {
        this(spørsmål, valg);

        String[] _valg = new String[this.valg.length + 1];
        for (int i = 0; i < this.valg.length; i++) {
            _valg[i] = this.valg[i];
        }
        _valg[this.valg.length] = sisteValg;

        this.valg = _valg;
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
                tall = scanner.nextInt();
            } else {
                scanner.next(); // nullstiller scanneren
                tall = -1;
            }

            førsteLoop = false;
        } while (tall < 1 || valg.length < tall);

        return tall;
    }

    /**
     * Printer ut en meny der brukeren blir stilt et spørsmål og gir brukeren
     * svaralternativer. Spørsmålet og svaralternativene samsvarer med det som ble
     * angit i konstruksjonen av instansen.
     * 
     * @return Returnerer et svar som samsvarer med hva brukeren valgte
     *         (1-indeksert)
     */
    public int promptSvar() {
        Scanner sc = new Scanner(System.in);
        int svar = promptSvar(sc);
        sc.close();
        return svar;
    }

    // Printer magiske tegn som jeg kopierte fra nettet som hvisker vekk alt i
    // terminalvinduet.
    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}