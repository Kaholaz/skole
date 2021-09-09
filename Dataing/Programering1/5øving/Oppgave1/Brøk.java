package Oppgave1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * En klasse som muliggjør grunnleggende aritmetiske operasjoner på brøker uten
 * å endre på eksakte verdier.
 * 
 * @author Sebastian Bugge
 */
public class Brøk {
    private int teller, nevner;

    /**
     * Konstruktør for Brøk. Lager en ny brøk med samme verdier som en annen instans
     * av typen brøk.
     * 
     * @param brøk En Brøk som du ønsker å kopiere.
     */
    public Brøk(Brøk brøk) {
        this.teller = brøk.teller;
        this.nevner = brøk.nevner;
    }

    /**
     * Konstruktør for brøk. Lager et brøk-objekt der telleren er et heltall og
     * nevneren er 1
     * 
     * @param heltall Telleren til brøken på formen {@code heltall/1}
     */
    public Brøk(int heltall) {
        this.teller = heltall;
        this.nevner = 1;
    }

    /**
     * Konstruktør for brøk. Lager et brøk-objekt med nevner og teller på formen
     * {@code teller/nevner}
     * 
     * @param teller Telleren til brøken
     * @param nevner Nevneren til brøken
     * 
     * @throws IllegalArgumentException Kaster en feilmelding om nevnern er null.
     */
    public Brøk(int teller, int nevner) {
        switch (Integer.signum(nevner)) {
            case 1:
                break;
            case 0:
                throw new IllegalArgumentException("Nevneren kan ikke være null");
            case -1:
                teller *= -1;
                nevner = Math.abs(nevner);
                break;
        }
        this.teller = teller;
        this.nevner = nevner;
    }

    /**
     * Finner minste fellesnevneren til to brøker.
     * 
     * @param nevner1 Nevneren til første brøk.
     * @param nevner2 Nevneren til andre brøk.
     * @return Fellesnevneren til de to brøkene.
     */
    public static int finnFellesNevner(int nevner1, int nevner2) {
        // Finner felles nevner
        List<Integer> faktorisert1 = faktoriser(nevner1);
        List<Integer> faktorisert2 = faktoriser(nevner2);

        List<Integer> mangler1 = new ArrayList<Integer>(faktorisert2);
        List<Integer> mangler2 = new ArrayList<Integer>(faktorisert1);

        // Fyller mangler1 og mangler2 med faktorene som er unike for de hendholsvise
        // nevnerene
        {
            int i = 0;
            while (i < mangler1.size()) {
                int tall = mangler1.get(i);
                if (mangler2.contains(tall)) {
                    mangler1.remove(i);
                    mangler2.remove(mangler2.indexOf(tall));
                } else {
                    i++;
                }
            }
        }

        // ganger sammen hva den har og hva den mangler av faktorer for å finne
        // fellesnvevneren:
        int fellesNevner = Stream.concat(faktorisert1.stream(), mangler1.stream()).reduce(1, (a, b) -> a * b);

        // gjør tilsvarende for brøk og sjekker at alt er som det skal (kan droppes):
        int brøkFellesNevner = Stream.concat(faktorisert2.stream(), mangler2.stream()).reduce(1, (a, b) -> a * b);
        if (fellesNevner != brøkFellesNevner) {
            throw new InternalError("Noe rart skjedde. Nevnere: " + nevner1 + ", " + nevner2);
        }

        return fellesNevner;
    }

    /**
     * Finner minste fellesnevneren til to brøker.
     * 
     * @param brøk1 Den første brøken.
     * @param brøk2 Den andre brøken.
     * @return Fellesnevneren til de to brøkene.
     */
    public static int finnFellesNevner(Brøk brøk1, Brøk brøk2) {
        return finnFellesNevner(brøk1.getNevner(), brøk2.getNevner());
    }

    /**
     * Parser en string og gir et Brøk-objekt
     * 
     * @param string En streng skrevet på formen {teller}/{nevner}. Støtter negativ
     *               teller/nevner.
     * @return Returnerer et Brøk-objekt som samsvarer med den gitte strengen.
     * 
     * @throws NumberFormatException Gir feilmelding dersom strengen ikke ble
     *                               parset.
     */
    public static Brøk parseBrøk(String string) {
        String[] string_split = string.split("/");
        if (string_split.length != 2) {
            throw new NumberFormatException();
        }

        int teller = Integer.parseInt(string_split[0]);
        int nevner = Integer.parseInt(string_split[1]);
        return new Brøk(teller, nevner);
    }

    /**
     * Gir en liste som er overlappingen mellom to lister. Metoden tar høyde for
     * duplicater av samme element.
     * 
     * @param liste1
     * @param liste2
     * @return Returnerer overlappen mellom de to listene.
     */
    private static List<Integer> finnFelles(List<Integer> liste1, List<Integer> liste2) {
        Stream<Integer> stream1 = liste1.stream();
        Stream<Integer> stream2 = liste2.stream();

        // legger sammen strømene og konverterer til et set for å finne alle unike
        // felles elementer
        List<Integer> felles = Stream.concat(stream1, stream2).collect(Collectors.toSet()).stream()
                .collect(Collectors.toList());

        // looper gjennom alle faktorene og ser hvor mange det er av faktoren i hver
        // liste. Tar den minste verdien og adder den til fellesfaktorene.
        List<Integer> result = new ArrayList<Integer>();
        for (Integer faktor : felles) {
            int num = Math.min(Collections.frequency(liste1, faktor), Collections.frequency(liste2, faktor));
            for (int i = 0; i < num; i++) {
                result.add(faktor);
            }
        }

        return result;
    }

    /**
     * Gir en liste med primtallsfaktorene til et gitt tall.
     * 
     * @param num Tallet man ønsker å faktorisere
     * @return Returnerer en liste med alle primtallsfaktorene til tallet i stigende
     *         rekkefølge. Dersom tallet er negativt, er siste element av listen
     *         {@code -1}.
     */
    private static List<Integer> faktoriser(int num) {

        List<Integer> result = new ArrayList<Integer>();
        int absNum = Math.abs(num);

        // Sjekker alle tall mindre en kvadratet av num. Hvis algoritmen finner en
        // faktor, så legges den til faktorene for så å rekursivt kalle funksjonen for
        // den ufaktoriserte delen av tallet. Siden algoriten starter å sjekke 2, og går
        // oppover, og alle sammensatte tall er større enn dens faktorer, er alle
        // faktorene som blir funnet primtall.
        for (int i = 2; i * i <= absNum; i++) {
            if (absNum % i == 0) {
                result.add(i);
                result.addAll(faktoriser(absNum / i));
                break;
            }
        }

        // Ingen faktorer ble funnet i loopen. Dette betyr at den eneste faktorern til
        // tallet er seg selv. (det er altså et primtall)
        if (result.size() == 0) {
            result.add(absNum);
        }

        // Legger til -1 for å ta høyde for negative tall
        if (absNum != num) {
            result.add(-1);
        }

        // Sjekker at faktorene faktisk kan ganges sammen for å få nummeret man startet
        // med
        if (result.stream().reduce(1, (a, b) -> a * b) != num) {
            throw new InternalError("Noe feil skjedde input num: " + num);
        }

        return result;
    }

    public int getNevner() {
        return nevner;
    }

    public int getTeller() {
        return teller;
    }

    /**
     * Legger sammen to brøker.
     * 
     * @param brøk Brøken som instansen av Brøk-objekte legges sammen med.
     * @return Returnerer svaret på regnestykket som et nytt Brøk-objekt.
     */
    public Brøk add(Brøk brøk) {

        int fellesNevner = Brøk.finnFellesNevner(this, brøk);

        // Utvidder begge børkene slik at de begge har felles nevener
        int thisMangler = fellesNevner / this.getNevner();
        Brøk utviddetThis = new Brøk(this).utvidd(thisMangler);

        int brøkMangler = fellesNevner / brøk.getNevner();
        Brøk utviddetBrøk = new Brøk(brøk).utvidd(brøkMangler);

        // summerer tellerne
        int newTeller = utviddetBrøk.getTeller() + utviddetThis.getTeller();
        return new Brøk(newTeller, fellesNevner).forkortet();
    }

    /**
     * Substraherer to brøker.
     * 
     * @param brøk Brøken som trekkes fra instansen av Brøk-objektet.
     * @return Returnerer svaret på regnestykket som et nytt Brøk-objekt.
     */
    public Brøk sub(Brøk brøk) {
        return this.add(new Brøk(brøk.getTeller() * -1, brøk.getNevner()));
    }

    /**
     * Multipliserer to brøker.
     * 
     * @param brøk Brøken som instansen av Brøk-objekte multipliseres med.
     * @return Returnerer svaret på regnestykket som et nytt Brøk-objekt.
     */
    public Brøk multi(Brøk brøk) {
        return new Brøk(this.teller * brøk.teller, this.nevner * brøk.nevner).forkortet();
    }

    /**
     * Dividerer to brøker.
     * 
     * @param brøk Brøken som instansen av Brøk-objekte deles på.
     * @return Returnerer svaret på regnestykket som et nytt Brøk-objekt.
     */
    public Brøk div(Brøk brøk) {
        return this.multi(new Brøk(brøk.nevner, brøk.teller));
    }

    /**
     * Forkorter en brøk så mye det går.
     * 
     * @return Returnerer et nytt forkortet brøk-objekt med samme verdi som den
     *         orginale brøken.
     */
    public Brøk forkortet() {
        // Hvis telleren er 0, blir brøken forkortet til 0/1
        if (teller == 0) {
            return new Brøk(0);
        }

        // finner felles faktorer for telleren og nevneren
        List<Integer> felles = finnFelles(faktoriser(getTeller()), faktoriser(getNevner()));

        // Går gjennom alle faktorene og forkorter
        Brøk result = new Brøk(this);
        for (Integer faktor : felles) {
            result = result.forkort(faktor);
        }

        return result;
    }

    /**
     * Konverterer objektet til en strengerepresentasjon
     * 
     * @return En strengerepresentasjon av instansen på formen {@code teller/nenver}
     */
    public String toString() {
        if (this.getNevner() == 1) {
            return Integer.toString(this.getTeller());
        } else {
            return this.getTeller() + "/" + this.getNevner();
        }
    }

    /**
     * Konverterer til et heltal
     * 
     * @return Returnerer nærmeste heltall (rundet ned)
     */
    public int toInt() {
        return this.getTeller() / this.getNevner();
    }

    /**
     * Konverterer til desimaltall
     * 
     * @return Returnerer verdien av brøken på desimaltall
     */
    public double toDouble() {
        return (double) this.getTeller() / (double) this.getNevner();
    }

    /**
     * Utvidder brøken ved å gange både telleren og nevneren med en en gitt faktor.
     * 
     * @param faktor Faktoren som brøken utviddes med.
     * @return Et nytt brøkobjekt med verdiene til den utviddede brøken.
     */
    private Brøk utvidd(int faktor) {
        return new Brøk(getTeller() * faktor, getNevner() * faktor);
    }

    /**
     * Forkorter brøken ved å gange både telleren og nevneren med en gitt faktor.
     * 
     * @param faktor Faktoren som brøken forkortes med.
     * @return Et nytt brøkobjekt med verdiene til den forkortede brøken.
     * 
     * @throws IllegalArgumentException Kaster en feilmelding dersom ikke både
     *                                  telleren og nevneren er delig med faktoren.
     */
    private Brøk forkort(int faktor) {
        if (teller % faktor != 0 || nevner % faktor != 0) {
            throw new IllegalArgumentException(
                    "Både teller og nevner må være delig på faktoren når du forkorter en brøk.");
        }

        return new Brøk(getTeller() / faktor, getNevner() / faktor);
    }

}