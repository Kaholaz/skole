/**
 * Rett er et objekt som behandler retter.
 */
public class Rett {
    private String navn, type, oppskrift;
    private Float pris;

    /**
     * Konstruktør for et rett-objekt
     * 
     * @param navn      Navnet på retten (entydig)
     * @param type      Type på retten (desert, forrett, ect.)
     * @param oppskrift Oppskriften på retten
     * @param pris      Prisen på retten. Skal være positiv.
     */
    public Rett(String navn, String type, String oppskrift, Float pris) {
        this.navn = navn;
        this.oppskrift = oppskrift;

        // sørger for at prisen er positiv
        if (Math.signum(pris) != -1) {
            this.pris = pris;
        } else {
            throw new IllegalArgumentException("Prisen må være positiv");
        }

        // Sørger for at typen er i menynøkkelen
        found: {
            for (String menyType : MenyRegister.MENY_NØKKEL) {
                // Setter typen til den samsvarende typen i menynøkkelen hvis den finner en
                // match.
                if (menyType.toLowerCase().equals(type.toLowerCase())) {
                    this.type = menyType;
                    break found; // hopper ut av "found"-blokken
                }
            }

            // kaster en feilmelding dersom typen ikke er i menynøkkelen
            throw new IllegalArgumentException("Typen rett må finnes i menynøkkelen!");
        }
    }

    /**
     * @return Navnet på retten
     */
    public String getNavn() {
        return navn;
    }

    /**
     * @return Typen til retten (dessert, hovedredd, ect.)
     */
    public String getType() {
        return type;
    }

    /**
     * @return Oppskriften til retten
     */
    public String getOppskrift() {
        return oppskrift;
    }

    /**
     * @return Prisen til retten
     */
    public Float getPris() {
        return pris;
    }

    /**
     * Lager en kopi av objektet
     */
    @Override
    protected Rett clone() {
        return new Rett(navn, type, oppskrift, pris);
    }

    /**
     * Returenerer navnet på retten på måten:
     * 
     * Navn: navn<br>
     * Type: type<br>
     * Oppskrift: oppskrift<br>
     * Pris: pris<br>
     */
    @Override
    public String toString() {
        String result = "Navn: " + getNavn() + "\n";
        result += "Type: " + getType() + "\n";
        result += "Oppskrift: " + getOppskrift() + "\n";
        result += "Pris: " + getPris() + "\n";

        return result;
    }
}
