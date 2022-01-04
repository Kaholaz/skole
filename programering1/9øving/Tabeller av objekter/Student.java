/**
 * Student er en mutable klasse som holder navnet på studenten og hvor mange
 * oppgaver de har gjort.
 */
public class Student {
    private String navn;
    private int antOppg;

    /**
     * Konstruktøren til Student-klassen. Tar inn et navn og lager en ny instans av
     * klassen med dette navnet og setter antall oppgaver studenten har gjort til 0.
     * 
     * @param navn navnet på studenten.
     */
    public Student(String navn) {
        this.navn = navn;
        this.antOppg = 0;
    }

    /**
     * @return Navnet på studenten
     */
    public String getNavn() {
        return navn;
    }

    /**
     * @return Antall oppgaver studenten har gjort.
     */
    public int getAntOppg() {
        return antOppg;
    }

    /**
     * Inkrementerer antall oppgaver en student har gjort med et gitt tall.
     * 
     * @param økning Antall oppgaver du ønsker å inkrementere med.
     */
    public void økAntOppg(int økning) {
        antOppg += økning;
    }

    /**
     * Konverterer studenten til en string. Gir navnet til studenen.
     */
    public String toString() {
        return navn;
    }
}