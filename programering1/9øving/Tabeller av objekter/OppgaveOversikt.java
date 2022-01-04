import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Klassen er mutable og holder gjeldende oversikt over alle studentene og hvor
 * mange oppgaver de har gjort.
 */
public class OppgaveOversikt {
    /**
     * Dette er klasseregisteret. Hver student kan hentes ved hjelp av navnet til
     * vedkommende. På grunn av hvordan dette er implementert, må navnet til hver
     * enkelte student være unikt.
     */
    private Map<String, Student> studenter;

    /**
     * Konstruktøren til oppgaveorversikt. Lager en ny instans med et tomt
     * studentregister.
     */
    public OppgaveOversikt() {
        studenter = new HashMap<String, Student>();
    }

    /**
     * Returnerer keysettet til til studentregisteret
     * 
     * @return Settet med navnet til alle studentene i studentregisteret.
     */
    public Set<String> getStudenter() {
        return studenter.keySet();
    }

    /**
     * Git antall studenter i studentregisteret.
     * 
     * @return Antall studenter i studentregisteret.
     */
    int getAntallStudenter() {
        return studenter.size();
    }

    /**
     * Git antall oppgaver en gitt student har gjort.
     * 
     * @param studentNavn navnet på studenten
     * @return Antall oppgaver den gitte studenten har gjort.
     */
    int finnAntallOppgaver(String studentNavn) {
        Student student = studenter.get(studentNavn);
        return student.getAntOppg();
    }

    /**
     * Legger en ny student til i studentregisteret
     * 
     * @param studentNavn navnet på studenten
     */
    void registrerStudent(String studentNavn) {
        studenter.put(studentNavn.toLowerCase(), new Student(studentNavn));
    }

    /**
     * Øker antall oppgaver en student har gitt med en gitt økning
     * 
     * @param studentNavn Navnet på studenten som du ønsker å øke antall oppgaver på
     * @param økning      antall oppgaver du ønsker å øke antallet med.
     */
    void økAntOppg(String studentNavn, int økning) {
        studenter.get(studentNavn).økAntOppg(økning);
    }

    @Override
    public String toString() {
        String result = "";
        Student[] items = new Student[studenter.size()];
        for (Student student : studenter.values().toArray(items)) {
            result = result.concat(student + ", ");
        }

        return result.substring(0, result.length() - 2);
    }
}
