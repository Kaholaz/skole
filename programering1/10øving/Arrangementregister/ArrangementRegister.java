import java.security.KeyException;
import java.util.ArrayList;
import java.util.List;

/**
 * ArrangementRegister er en klasse som holder på et set med arrangementer og
 * som tilbyr en rekke metoder for å undersøke disse.
 */
public class ArrangementRegister {
    private List<Arrangement> arrangementer;
    int nesteId;

    /**
     * Konstruktør til arrangementregisteret. Tar et vilkårlig antall arrangementer
     * og lagrer dem.
     * 
     * @param arrangementer Et vilkårlig antall arrangementer.
     */
    public ArrangementRegister(Arrangement... arrangementer) {
        this.nesteId = 1;

        this.arrangementer = new ArrayList<Arrangement>();
        leggTilArrangementer(arrangementer);
    }

    /**
     * Tar et nytt arrangement og legger det til arrangementregisteret. Sørger for å
     * gi den en ny unik id.
     * 
     * @param arrangement Et nytt arrangement som ønskes å legges til.
     */
    public void leggTilArrangement(Arrangement arrangement) {
        arrangementer.add(new Arrangement(nesteId, arrangement.getNavn(), arrangement.getSted(),
                arrangement.getArrangør(), arrangement.getType(), arrangement.getTidspunkt()));

        nesteId++;
    }

    public void leggTilArrangementer(Arrangement[] arrangementer) {
        for (Arrangement arrangement : arrangementer) {
            leggTilArrangement(arrangement);
        }
    }

    /**
     * Fjerner et arrangement gitt en id.
     * 
     * @param id Id-en til arrangementet.
     * @return Returnerer true dersom arrangementet fantes, og false dersom det ikke
     *         gjorte det.
     */
    public boolean fjernArrangement(int id) {
        for (int i = 0; i < arrangementer.size(); i++) {
            if (arrangementer.get(i).getId() == id) {
                arrangementer.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returnerer et arrangement gitt en id.
     * 
     * @param id Id-en til arrangementet
     * @return Et arrangement gitt id-en. Returnerer null dersom iden ikke finnes i
     *         registeret.
     */
    public Arrangement getArrangementById(int id) throws KeyException {
        for (Arrangement arrangement : arrangementer) {
            if (arrangement.getId() == id) {
                return arrangement;
            }
        }

        return null;
    }

    /**
     * Gir alle arrangementene
     * 
     * @return Alle arrangementene
     */
    public List<Arrangement> getArrangementer() {
        List<Arrangement> resultat = new ArrayList<Arrangement>();

        for (Arrangement arr : arrangementer) {
            resultat.add(arr.clone());
        }

        return resultat;
    }

    /**
     * Finner alle arrangementene gitt et sted sortert etter sted
     * 
     * @param sted Et sted
     * @return Alle arrangementene gitt et sted sortert etter sted
     */
    public List<Arrangement> finnArrangementerPåSted(String sted) {
        List<Arrangement> resultat = new ArrayList<Arrangement>();

        for (Arrangement arrangement : arrangementer) {
            if (arrangement.getSted().equals(sted)) {
                resultat.add(arrangement.clone());
            }
        }

        resultat.sort(new SorterEtterSted());

        return resultat;
    }

    /**
     * Finner alle arrangementene gitt en dato, sortert etter tid. Datoen gis på
     * følgende format: [YYYYMMDD]
     * 
     * @param dato Dato gitt på følgene format [YYYYMMDD]
     * @return Alle arrangementene gitt en dato sortert etter tid
     */
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

    /**
     * Finne alle arrangementene gitt et datointervall. Metoden gir alle
     * arrangementene som faller innenfor det inklusive intervalle fra og med
     * fomDato til og med tomDato. Datoene gis å formatet [YYYYDDMM]
     * 
     * @param fomDato Fra og med dato. Gis på intervallet [YYYYMMDD]
     * @param tomDato Til og med dato. Gis på intervallet [YYYYMMDD]
     * @return Alle arrrangementene gitt et datointervall sortert etter sted.
     */
    public List<Arrangement> finnArrangementerIDatoIntervall(long fomDato, long tomDato) {
        List<Arrangement> resultat = new ArrayList<Arrangement>();

        long fomTidspunkt = fomDato * 10000;
        long tilTidspunkt = (tomDato + 1) * 10000;

        for (Arrangement arrangement : arrangementer) {
            if (fomTidspunkt <= arrangement.getTidspunkt() && arrangement.getTidspunkt() < tilTidspunkt) {
                resultat.add(arrangement.clone());
            }
        }

        resultat.sort(new SorterEtterSted());

        return resultat;
    }
}