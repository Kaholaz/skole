public class Arrangement {
    private String navn, sted, arrangør, type;
    private int id;
    private long tidspunkt;

    public Arrangement(String navn, String sted, String arrangør, String type, long tidspunkt) {
        this.navn = navn;
        this.sted = sted;
        this.arrangør = arrangør;
        this.type = type;
        this.tidspunkt = tidspunkt;
    }

    public Arrangement(int id, String navn, String sted, String arrangør, String type, long tidspunkt) {
        this(navn, sted, arrangør, type, tidspunkt);
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public String getSted() {
        return sted;
    }

    public String getArrangør() {
        return arrangør;
    }

    public String getType() {
        return type;
    }

    public long getTidspunkt() {
        return tidspunkt;
    }

    public int getId() {
        return id;
    }

    public static int compare(Arrangement arrangement1, Arrangement arrangement2) {
        if (arrangement1.getTidspunkt() > arrangement2.getTidspunkt()) {
            return 1;
        } else if (arrangement1.getTidspunkt() < arrangement2.getTidspunkt()) {
            return -1;
        } else {
            return 0;
        }
    }

    public Arrangement clone() {
        return new Arrangement(id, navn, sted, arrangør, type, tidspunkt);
    }
}
