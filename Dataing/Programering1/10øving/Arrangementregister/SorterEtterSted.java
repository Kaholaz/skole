import java.util.Comparator;

/**
 * Brukes til å sortere etter sted i stigende rekkefølge.<br>
 * <br>
 * Sorterer først etter tid, så sted, så tidspunkt.
 */
public class SorterEtterSted implements Comparator<Arrangement> {
    public int compare(Arrangement a, Arrangement b) {
        int compare = a.getNavn().compareTo(b.getNavn());

        if (compare == 0) {
            compare = a.getType().compareTo(b.getType());
            if (compare == 0) {
                compare = (int) (a.getTidspunkt() - b.getTidspunkt());
            }
        }

        return compare;
    }
}