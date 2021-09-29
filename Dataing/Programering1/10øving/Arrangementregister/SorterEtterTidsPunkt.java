import java.util.Comparator;

public class SorterEtterTidsPunkt implements Comparator<Arrangement> {
    public int compare(Arrangement a, Arrangement b) {
        return (int) (a.getTidspunkt() - b.getTidspunkt());
    }
}