import java.util.Arrays;

/**
 * Tekstmanipulator er en klasse som kan brukes til enkel tekst- manipulasjon og
 * analyse.
 * 
 * @author Sebastian Bugge
 */
public class TekstManipulator {
    private final String IKKEORD = "[^\\wæøåÆØÅ]+";
    private final String PERIODESKILLETENG = "[.:!?]+";
    private String string;

    /**
     * Gjør en kopi av et TekstManipulator objekt ved å kopiere alle propertiene.
     * 
     * @param tekstManipulator Tekstmanipulator-objektet som ønskes å kopieres.
     */
    TekstManipulator(TekstManipulator tekstManipulator) {
        this(tekstManipulator.string);
    }

    /**
     * Konstruerer et TekstManipulator objekt som kan brukes til å analysere og
     * manipulere tekst.
     * 
     * @param str Teksten som ønskes å analyseres/manipuleres.
     */
    TekstManipulator(String str) {
        string = str;
    }

    /**
     * @return Teksten til TekstManipulator-objektet
     */
    public String getString() {
        return string;
    }

    /**
     * @return Teksten til TekstManipulator-objektet konvertert til store bokstaver.
     */
    public String getStringStore() {
        return string.toUpperCase();
    }

    /**
     * @return {@code String[]} med alle ordene (grupper med tegn som er adskillt av
     *         ikke-bokstav tegn) i teksten.
     */
    public String[] getOrd() {
        return string.split(IKKEORD);
    }

    /**
     * @return Antall ord i teksten.
     */
    public int finnAntallOrd() {
        return getOrd().length;
    }

    /**
     * @return Gjennomsnittlig ordlengde i teksten.
     */
    public double bokstaverPerOrd() {
        // Finner summen på lengden på alle ordene i teksten.
        String[] ord = getOrd();
        return finnGjennomsnittligLengde(ord);
    }

    /**
     * @return Tegn per periode, der en periode er teksten mellom et eller to av
     *         disse symbolene: {@code . : ! ?}
     */
    public double ordPerPeriode() {
        int antallPerioder = string.split(PERIODESKILLETENG).length;

        return (double) finnAntallOrd() / (double) antallPerioder;
    }

    /**
     * Erstatter alle substrenger i teksten som stemmer med en gitt regEx med en
     * gitt erstattning.
     * 
     * @param finn    Substrengen du ønsker å erstatte.
     * @param erstatt Erstattningen for substringen.
     * @return Den nye stringen med erstattet tekst.
     */
    public String byttUt(String finn, String erstatt) {
        return string.replaceAll(finn, erstatt);
    }

    /**
     * Finner gjennomsnittslengden per string i en stringetabell.
     * 
     * @param tegn Stringetabellen som skal undersøkes.
     * @return Gjennomsnittlig lengde på stringene i stringtabellen.
     */
    private static double finnGjennomsnittligLengde(String[] tegn) {
        int sumLengde = Arrays.stream(tegn).map(s -> s.length()).reduce(0, (a, b) -> a + b);
        return (double) sumLengde / (double) tegn.length;
    }
}
