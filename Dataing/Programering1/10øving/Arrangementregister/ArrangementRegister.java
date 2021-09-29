import java.security.KeyException;
import java.util.ArrayList;
import java.util.List;

public class ArrangementRegister {
    private List<Arrangement> arrangementer;

    public ArrangementRegister(Arrangement... arrangementer) {
        this.arrangementer = new ArrayList<Arrangement>();
        for (Arrangement arrangement : arrangementer) {
            leggTilArrangement(arrangement);
        }
    }

    public void leggTilArrangement(Arrangement arrangement) {
        int id;
        if (arrangementer.size() == 0) {
            id = 0;
        } else {
            id = arrangementer.get(arrangementer.size() - 1).getId();
        }

        arrangementer.add(new Arrangement(id, arrangement.getNavn(), arrangement.getSted(), arrangement.getArrangør(),
                arrangement.getType(), arrangement.getTidspunkt()));
    }

    public boolean fjernArrangement(int id) {
        for (int i = 0; i < arrangementer.size(); i++) {
            if (arrangementer.get(i).getId() == id) {
                arrangementer.remove(i);
                return true;
            }
        }
        return false;
    }

    public Arrangement getArrangementById(int id) throws KeyException {
        for (Arrangement arrangement : arrangementer) {
            if (arrangement.getId() == id) {
                return arrangement;
            }
        }

        throw new KeyException("Id-en finnes ikke i arrangementregisteret");
    }

    public List<Arrangement> finnArrangementerPåSted(String sted) {
        List<Arrangement> resultat = new ArrayList<Arrangement>();

        for (Arrangement arrangement : arrangementer) {
            if (arrangement.getSted().equals(sted)) {
                resultat.add(arrangement.clone());
            }
        }

        return resultat;
    }

    public List<Arrangement> finnArrangementerPåDato(Long dato) {
        List<Arrangement> resultat = new ArrayList<Arrangement>();

        for (Arrangement arrangement : arrangementer) {
            if (arrangement.getTidspunkt() / 10000 == dato) {
                resultat.add(arrangement.clone());
            }
        }

        resultat.sort(new SorterEtterTidsPunkt());

        return resultat;
    }

    public List<Arrangement> finnArrangementerIDatoIntervall(long fomDato, long tomDato) {
        List<Arrangement> resultat = new ArrayList<Arrangement>();

        long fomTidspunkt = fomDato * 10000;
        long tilTidspunkt = (tomDato + 1) * 1000;

        for (Arrangement arrangement : arrangementer) {
            if (fomTidspunkt <= arrangement.getTidspunkt() && arrangement.getTidspunkt() < tilTidspunkt) {
                resultat.add(arrangement.clone());
            }
        }

        resultat.sort(new SorterEtterSted());

        return resultat;
    }

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
}