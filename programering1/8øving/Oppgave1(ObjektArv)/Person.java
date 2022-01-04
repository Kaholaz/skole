import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Person er en immutable klasse som holer personaliaen til en person.
 * 
 * @author Sebastian Bugge
 */
public class Person {
    protected String fornavn, etternavn;
    protected int fødselsår;

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public int getFødselsår() {
        return fødselsår;
    }

    /**
     * @return Det fulle navnet til personen på formen "{etternavn}, {fornavn}"
     */
    public String getFulltNavn() {
        return String.format("%s, %s", etternavn, fornavn);
    }

    /**
     * @return Alderen til personen. Metoden tar ikke hensyn til om personen har
     *         hatt bursdag i år, så den virkelige alderen til vedkommende er enten
     *         returverdien eller et år mer.
     */
    public int getAlder() {
        return new GregorianCalendar().get(Calendar.YEAR) - fødselsår;
    }

    @Override
    public String toString() {
        return String.format("Fullt navn: %s; Fødselsår: %d", getFulltNavn(), getFødselsår());
    }
}
