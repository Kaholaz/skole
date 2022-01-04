public class Arrangement {
    private String navn, sted, arrangør, type;
    private int id;
    private long tidspunkt;

    /**
     * Konstruktøren til arrangement klassen. Det er anbefalt at man bruker denne
     * konstruktøren.
     * 
     * @param navn      Navnet på arrangementet
     * @param sted      Stedet arrangementet skal avholdes
     * @param arrangør  Arrangøren av arrangementet
     * @param type      Typen arrangement
     * @param tidspunkt Tidspunktet arrangementet skal starte formatert slik:
     *                  [YYYYMMDDhhmm]
     */
    public Arrangement(String navn, String sted, String arrangør, String type, long tidspunkt) {
        this.navn = navn;
        this.sted = sted;
        this.arrangør = arrangør;
        this.type = type;
        this.tidspunkt = tidspunkt;
    }

    /**
     * Konstruktøren til arrangement klassen. Denne konstruktøren brukes primært av
     * et arrangementregister. Det er derfor anbefalt å bruke konstruktøren der man
     * ikke trenger å spesifisere id.
     * 
     * @param id        En unik id som gis hvert arrangement.
     * @param navn      Navnet på arrangementet
     * @param sted      Stedet arrangementet skal avholdes
     * @param arrangør  Arrangøren av arrangementet
     * @param type      Typen arrangement
     * @param tidspunkt Tidspunktet arrangementet skal starte formatert slik:
     *                  [YYYYMMDDhhmm]
     */
    public Arrangement(int id, String navn, String sted, String arrangør, String type, long tidspunkt) {
        this(navn, sted, arrangør, type, tidspunkt);
        this.id = id;
    }

    /**
     * @return Navnet på arrangementet
     */
    public String getNavn() {
        return navn;
    }

    /**
     * @return Stedet arrangementet skal avholdes.
     */
    public String getSted() {
        return sted;
    }

    /**
     * @return Arrangøren av arrangementet
     */
    public String getArrangør() {
        return arrangør;
    }

    /**
     * @return Typen arrangement
     */
    public String getType() {
        return type;
    }

    /**
     * @return Tidspunktet arrangementet starter formatert slik: [YYYYMMDDhhmm]
     */
    public long getTidspunkt() {
        return tidspunkt;
    }

    /**
     * @return Den unike id-en til arrangementet.
     */
    public int getId() {
        return id;
    }

    /**
     * En strengrepresentasjon av arrangementet.
     */
    @Override
    public String toString() {
        return String.format("Id: %d; Navn: %s; Sted: %s; Arrangør: %s; Type: %s; Tidspunkt: %d", id, navn, sted,
                arrangør, type, tidspunkt);
    }

    /**
     * Kloner et arrangement og gir et nytt arrangement med like parametere.
     */
    public Arrangement clone() {
        return new Arrangement(id, navn, sted, arrangør, type, tidspunkt);
    }
}
