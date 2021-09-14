import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Arbeidstaker er en immutable klasse som arver fra Person. Klassen holder
 * personalia, arbeidsdetaljer, og lønnsdetaljer.
 */
public class ArbTaker extends Person {
    private int arbTakerNr, ansettelsesÅr, månedsLønn;
    private double skatteAndel;

    public static final int ANSETTELSESÅR_DEFAULT = new GregorianCalendar().get(Calendar.YEAR);
    public static final int MÅNEDSLØNN_DEFAULT = 0;
    public static final double SKATTEANDEL_DEFAULT = 0.22;

    /**
     * Genererer et nytt unikt ansettelsesnr på formen YYYYXXX der YYYY er
     * ansattelsesåret og XXX er et unikt nr som genereres i stigende rekkefølge for
     * hvert år.
     * 
     * @param ansettelsesÅr Året personen ble ansatt.
     * @return Et nytt unikt ansattnummer.
     */
    static int getNewUniqueArbTakerNr(int ansettelsesÅr) {
        return 1111;
        // throw new UnsupportedOperationException("Not inplemented");
    }

    /**
     * Konstruktør for ArbTaker klassen som tar inn navnet til en ny arbeidstaker.
     * Siden ansettelsesår, månedslønn og skatteandel ikke er gitt settes de til
     * henholdsvis nåværende år, 0 og 0.22
     * 
     * Det genereres et nytt unikt ansattnummer på formen YYYYXXX der YYYY er
     * ansattelsesåret og XXX er et unikt nr som genereres i stigende rekkefølge for
     * hvert år.
     * 
     * @param etternavn Etternavnet til arbeidstakeren.
     * @param fornavn   Fornavnet til arbeidstakeren.
     * @param fødselsår Fødselsåret til arbeidstakeren.
     */
    public ArbTaker(String etternavn, String fornavn, int fødselsår) {
        this.etternavn = etternavn;
        this.fornavn = fornavn;
        this.fødselsår = fødselsår;

        this.ansettelsesÅr = new GregorianCalendar().get(Calendar.YEAR);
        this.setMånedsLønn(0);
        this.setSkatteAndel(0.22);

        this.arbTakerNr = getNewUniqueArbTakerNr(ansettelsesÅr);
    }

    /**
     * Konstruktør for ArbTaker klassen som tar inn navnet til en ny arbeidstaker og
     * ansettelsesåret. Siden månedslønn og skatteandel ikke er gitt settes de til 0
     * og 0.22
     * 
     * Det genereres et nytt unikt ansattnummer på formen YYYYXXX der YYYY er
     * ansattelsesåret og XXX er et unikt nr som genereres i stigende rekkefølge for
     * hvert år.
     * 
     * @param etternavn     Etternavnet til arbeidstakeren.
     * @param fornavn       Fornavnet til arbeidstakeren.
     * @param fødselsår     Fødselsåret til arbeidstakeren.
     * @param ansettelsesÅr Året arbeidstakeren ble ansatt.
     */
    public ArbTaker(String etternavn, String fornavn, int fødselsår, int ansettelsesÅr) {
        this.etternavn = etternavn;
        this.fornavn = fornavn;
        this.fødselsår = fødselsår;
        this.ansettelsesÅr = ansettelsesÅr;

        this.setMånedsLønn(0);
        this.setSkatteAndel(0.22);

        this.arbTakerNr = getNewUniqueArbTakerNr(ansettelsesÅr);
    }

    /**
     * Konstruktør for ArbTaker klassen som tar inn navnet og lønnsdetaljer til en
     * ny arbeidstaker. Siden ansettelsesår ikke er gitt, settes det til nåværende
     * år.
     * 
     * Det genereres et nytt unikt ansattnummer på formen YYYYXXX der YYYY er
     * ansattelsesåret og XXX er et unikt nr som genereres i stigende rekkefølge for
     * hvert år.
     * 
     * @param etternavn   Etternavnet til arbeidstakeren.
     * @param fornavn     Fornavnet til arbeidstakeren.
     * @param fødselsår   Fødselsåret til arbeidstakeren.
     * @param månedsLønn  Brutto månedslønn i norske kroner.
     * @param skatteAndel Andelen av bruttolønnen som blir skattet av. (Oppgis i
     *                    intervallet [0,1])
     */
    public ArbTaker(String etternavn, String fornavn, int fødselsår, int månedsLønn, double skatteAndel) {
        this.etternavn = etternavn;
        this.fornavn = fornavn;
        this.fødselsår = fødselsår;

        setMånedsLønn(månedsLønn);
        setSkatteAndel(skatteAndel);

        this.ansettelsesÅr = new GregorianCalendar().get(Calendar.YEAR);
        this.arbTakerNr = getNewUniqueArbTakerNr(ansettelsesÅr);
    }

    /**
     * Konstruktør for ArbTaker klassen som tar inn navnet, ansettelsesåret og
     * lønnsdetaljer til en ny arbeidstaker.
     * 
     * Det genereres et nytt unikt ansattnummer på formen YYYYXXX der YYYY er
     * ansattelsesåret og XXX er et unikt nr som genereres i stigende rekkefølge for
     * hvert år.
     * 
     * @param etternavn     Etternavnet til arbeidstakeren.
     * @param fornavn       Fornavnet til arbeidstakeren.
     * @param fødselsår     Fødselsåret til arbeidstakeren.
     * @param ansettelsesÅr Året arbeidstakeren ble ansatt.
     * @param månedsLønn    Brutto månedslønn i norske kroner
     * @param skatteAndel   Andelen av bruttolønnen som blir skattet av. (Oppgis i
     *                      intervallet [0,1])
     */
    public ArbTaker(String etternavn, String fornavn, int fødselsår, int ansettelsesÅr, int månedsLønn,
            double skatteAndel) {
        this.etternavn = etternavn;
        this.fornavn = fornavn;
        this.fødselsår = fødselsår;
        this.ansettelsesÅr = ansettelsesÅr;

        setMånedsLønn(månedsLønn);
        setSkatteAndel(skatteAndel);

        this.arbTakerNr = getNewUniqueArbTakerNr(ansettelsesÅr);
    }

    public int getArbTakerNr() {
        return arbTakerNr;
    }

    public int getAnsettelsesÅr() {
        return ansettelsesÅr;
    }

    public int getMånedsLønn() {
        return månedsLønn;
    }

    /**
     * Setter månedslønnen til en gitt verdi.
     * 
     * @param månedsLønn Brutto månedslønn i norske kroner.
     * 
     * @throws IllegalArgumentException Gir en feilmelding dersom månedslønnen ikke
     *                                  er positiv.
     */
    public void setMånedsLønn(int månedsLønn) {
        if (0 <= månedsLønn) {
            this.månedsLønn = månedsLønn;
        } else {
            throw new IllegalArgumentException("Månedslønnnen må være positiv");
        }
    }

    public double getSkatteAndel() {
        return skatteAndel;
    }

    /**
     * Setter skatteandelen til en gitt verdi.
     * 
     * @param skatteAndel Andel av bruttolønn som skattes av. Oppgis i intervalet
     *                    [0,1]
     * 
     * @throws IllegalArgumentException Gir en feilmelding dersom skatteandelen ikke
     *                                  er i intervallet [0,1]
     */
    public void setSkatteAndel(double skatteAndel) {
        if (0 <= skatteAndel && skatteAndel <= 1) {
            this.skatteAndel = skatteAndel;
        } else {
            throw new IllegalArgumentException("Skatteandelen må være i intervallet [0, 1]");
        }
    }

    public double getSkattPrMåned() {
        return månedsLønn * skatteAndel;
    }

    public int getBruttolønn() {
        return månedsLønn * 12;
    }

    /**
     * Gir skattemengde pr år. Metoden tar hensyn til null skatt i juni og halv
     * skatt i desember.
     */
    public double getSkattPrÅr() {
        return getBruttolønn() - getSkattPrMåned() * 10.5;
    }

    /**
     * @return Antall år en ansatt har vært ansatt. Tar ikke hensyn til
     *         anettelsesdato, så virkelig antall år ansatt kan enten være
     *         returverdien eller et år mer.
     */
    public int getAntallÅrAnsatt() {
        return new GregorianCalendar().get(Calendar.YEAR) - ansettelsesÅr;
    }

    /**
     * Sjekker om en ansatt har vært ansatt i mer en et gitt antall år.
     * 
     * @param år Antall det undersøkes om den ansatte har vært ansatt i.
     * @return {@code true} dersom den ansatte har vært ansatt i mer enn det gitte
     *         antall år, og {@code false} dersom vedkommende ikke har vært det.<br>
     *         <br>
     *         Merk at getAntallÅrAnsatt ikke tar høyde for ansettelsesdato, så
     *         funksjonen kan returnere {@code false} også når en ansatt i
     *         realiteten har vært ansatt i mer en den gitte mengden år.
     */
    public boolean harVærtAnsattMerEnn(int år) {
        return (år > getAntallÅrAnsatt());
    }

    @Override
    public String toString() {
        return super.toString().concat(
                String.format("; Ansettelsesår: %d; Månedslønn (brutto): %d; Skatteprosent: %.2f%%, Ansattnr.: %d",
                        getAnsettelsesÅr(), getMånedsLønn(), getSkatteAndel() * 100, getArbTakerNr()));
    }
}