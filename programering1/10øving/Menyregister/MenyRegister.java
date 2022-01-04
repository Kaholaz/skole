import java.util.ArrayList;
import java.util.HashMap;

/**
 * MenyRegister er en klasse som holder oversikt over et mengde retter og mengde
 * menyer.
 */
public class MenyRegister {
    static public final String[] MENY_NØKKEL = new String[] { "Kald forrett", "Suppe", "Varm forrett", "Fisk hovedrett",
            "Varm mellomrett", "Kald mellomrett", "Hvilerett", "Kjøtt hovedrett", "Garnityr", "Ost", "Søt dessert",
            "Frukt" };
    private HashMap<String, Rett> retter;
    private ArrayList<Meny> menyer;

    /**
     * Konstruktør for menyregister. Lager et tomt register med ingen
     */
    MenyRegister() {
        this(new Rett[0], new Meny[0]);
    }

    /**
     * Konstruktør for menyregister. Du kan ha retter i menyobjektet som ikke
     * gjennspeiles i retter-tabellen. Da legges disse rettene til i menyregisteret
     * på lik måte som det gjøres i {@code this.leggTilMeny()}
     * 
     * @param retter En tabell med retter som du øsnker å ha i menyregisteret
     * @param menyer En tabell menyer som du ønsker å ha i menyregisteret. Rettene i
     *               menyen er referanser til gjøres om til referanser til rettene i
     *               {@code this.retter}
     */
    MenyRegister(Rett[] retter, Meny[] menyer) {
        this.retter = new HashMap<String, Rett>();
        this.menyer = new ArrayList<Meny>();

        // Legger til alle rettene
        for (Rett rett : retter) {
            leggTilRett(rett);
        }

        // Legger til alle menyene
        for (Meny meny : menyer) {
            leggTilMeny(meny);
        }
    }

    /**
     * @return Menyene i menyregister
     */
    public ArrayList<Meny> getMenyer() {
        return menyer;
    }

    /**
     * @return Rettene i menyregisteret. Nøkkelen er navnet på retten i små
     *         bokstaver og innholdet er rettene.
     */
    public HashMap<String, Rett> getRetter() {
        return retter;
    }

    /**
     * Legger til en rett menyregisteret. Metoden gjør en kopi av objektet og lager
     * en ny referanse.
     * 
     * @param rett En rett som du øsnker å legge til.
     */
    public void leggTilRett(Rett rett) {
        retter.put(rett.getNavn().toLowerCase(), rett.clone());
    }

    /**
     * Leger til en meny i menyregisteret. Metoden lager ett nytt menyobjekt og
     * endrer referansene slik at de peker på rettene som er i menyregisteret. Hvis
     * rettene i menyen ikke finnes i menyregisteret, legges de til i oversikten
     * over retter.
     * 
     * @param meny Menyen du ønsker å legge til
     */
    public void leggTilMeny(Meny meny) {
        String[] retterStrings = new String[meny.getRetter().length];
        Rett[] retter = meny.getRetter();

        for (int i = 0; i < retter.length; i++) {
            if (!this.retter.containsKey(retter[i].getNavn().toLowerCase())) {
                leggTilRett(retter[i]);
            }
            retterStrings[i] = retter[i].getNavn();
        }

        this.menyer.add(Meny.lagMeny(this, retterStrings));
    }

    /**
     * Finner en rett gitt navnet på retten
     * 
     * @param navn Navnet på retten
     * @return Retten som et Rett-ojekt
     */
    public Rett finnRett(String navn) {
        return retter.get(navn.toLowerCase());
    }

    /**
     * Finner alle retter av en gitt type
     * 
     * @param type Typen rett (forrett, dessert, ect.). Typen må finnes i
     *             menynøkkelen.
     * @return Tabell med alle rettene av den gitte typen.
     */
    public Rett[] finnRetterAvType(String type) {
        found: {
            for (String menyType : MENY_NØKKEL) {
                if (menyType.equals(type)) {
                    break found;
                }
            }

            // løkken fant ingen match
            throw new IllegalArgumentException("Retttypen finnes ikke i meny nøkkelen");
        }

        return retter.values().stream().filter(r -> (r.getType().equals(type))).toArray(Rett[]::new);
    }

    /**
     * Gir alle rettene innenfor et prisintervall (inklusivt min og max)
     * 
     * @param min minste pris (inklusivt)
     * @param max største pris (inlusivt)
     * @return En tabell med rettene innenfor prisintervallet
     */
    public Meny[] finnMenyMedPrisMellom(double min, double max) {
        return menyer.stream().filter(m -> (m.getPris() >= min && m.getPris() <= max)).toArray(Meny[]::new);
    }

}
