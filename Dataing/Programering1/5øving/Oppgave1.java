import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Oppgave1
 */
public class Oppgave1 {
    public static void main(String[] args) {
        System.out.println(new Brøk(1, 2).add(new Brøk(1, 2)));
        System.out.println(new Brøk(2, 5).add(new Brøk(5, -3)));
        System.out.println(new Brøk(6, 12).add(new Brøk(6, 12)));
        System.out.println(new Brøk(1231, 12342).add(new Brøk(3)));
        System.out.println(new Brøk(2).add(new Brøk(3)));
    }
}

class Brøk {
    private int teller, nevner;

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

    public Brøk(Brøk brøk) {
        this.teller = brøk.teller;
        this.nevner = brøk.nevner;
    }

    public Brøk(int heltall) {
        this.teller = heltall;
        this.nevner = 1;
    }

    private static List<Integer> finnFelles(List<Integer> liste1, List<Integer> liste2) {
        List<Integer> felles = liste1.stream().filter(liste2::contains).collect(Collectors.toSet()).stream()
                .collect(Collectors.toList());
        List<Integer> result = new ArrayList<Integer>();
        for (Integer integer : felles) {
            int num = Math.min(Collections.frequency(liste1, integer), Collections.frequency(liste2, integer));
            for (int i = 0; i < num; i++) {
                result.add(integer);
            }
        }
        return result;
    }

    static private List<Integer> faktoriser(int num) {
        List<Integer> result = new ArrayList<Integer>();
        int absNum = Math.abs(num);
        for (int i = 2; i * i <= absNum; i++) {
            if (absNum % i == 0) {
                result.add(i);
                result.addAll(faktoriser(absNum / i));
                break;
            }
        }
        if (result.size() == 0) {
            result.add(absNum);
        }
        if (absNum != num) {
            result.add(-1);
        }
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

    public Brøk add(Brøk brøk) {
        // Finner felles nevner
        List<Integer> thisFaktorisert = faktoriser(this.getNevner());
        List<Integer> brøkFaktorisert = faktoriser(brøk.getNevner());

        List<Integer> thisMangler = new ArrayList<Integer>(brøkFaktorisert);
        List<Integer> brøkMangler = new ArrayList<Integer>(thisFaktorisert);

        {
            int i = 0;
            while (i < thisMangler.size()) {
                int tall = thisMangler.get(i);
                if (brøkMangler.contains(tall)) {
                    thisMangler.remove(i);
                    brøkMangler.remove(brøkMangler.indexOf(tall));
                } else {
                    i++;
                }
            }
        }
        int fellesNevner = Stream.concat(thisFaktorisert.stream(), thisMangler.stream()).reduce(1, (a, b) -> a * b);
        int brøkFellesNevner = Stream.concat(brøkFaktorisert.stream(), brøkMangler.stream()).reduce(1, (a, b) -> a * b);
        if (fellesNevner != brøkFellesNevner) {
            throw new InternalError("Noe rart skjedde. Brøker: " + this.toString() + brøk.toString());
        }

        // Utvidder begge børkene slik at de begge har felles nevener
        Brøk utviddetThis = new Brøk(this);
        Brøk utviddetBrøk = new Brøk(brøk);

        for (Integer integer : thisMangler) {
            utviddetThis = utviddetThis.utvidd(integer);
        }
        for (Integer integer : brøkMangler) {
            utviddetBrøk = utviddetBrøk.utvidd(integer);
        }

        // summerer tellerne
        int newTeller = utviddetBrøk.getTeller() + utviddetThis.getTeller();
        return new Brøk(newTeller, fellesNevner).forkortet();
    }

    public Brøk sub(Brøk brøk) {
        return this.add(new Brøk(brøk.getTeller() * -1, brøk.getNevner());
    }

    public Brøk forkortet() {
        List<Integer> felles = finnFelles(faktoriser(getTeller()), faktoriser(getNevner()));
        Brøk result = new Brøk(this);
        for (Integer integer : felles) {
            result = result.forkort(integer);
        }
        return result;
    }

    public String toString() {
        if (this.getNevner() == 1)
            return Integer.toString(this.getTeller());
        else
            return this.getTeller() + "/" + this.getNevner();
    }

    public int toInt() {
        return this.getTeller() / this.getNevner();
    }

    public double toDouble() {
        return (double) this.getTeller() / (double) this.getNevner();
    }

    private Brøk utvidd(int faktor) {
        return new Brøk(getTeller() * faktor, getNevner() * faktor);
    }

    private Brøk forkort(int faktor) {
        if (teller % faktor != 0 || nevner % faktor != 0) {
            throw new IllegalArgumentException(
                    "Både teller og nevner må være delig på faktoren når du forkorter en brøk.");
        }
        return new Brøk(getTeller() / faktor, getNevner() / faktor);
    }

}