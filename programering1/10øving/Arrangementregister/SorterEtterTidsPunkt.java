import java.util.Comparator;

/**
 * Brukes til å sortere en liste med arrangementer etter tid i stigende
 * rekkefølge.
 */
public class SorterEtterTidsPunkt implements Comparator<Arrangement> {
    public int compare(Arrangement a, Arrangement b) {
        return (int) (a.getTidspunkt() - b.getTidspunkt());
    }
}