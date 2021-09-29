import java.util.Comparator;

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