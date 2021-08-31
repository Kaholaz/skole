import java.util.Random;

public class Oppgave2 {
    public static void main(String[] args) {
        Tilfeldig tilfeldig = new Tilfeldig();
        System.out.println(new Random().nextInt(1));
        System.out.println(tilfeldig.nesteDesimaltall(1.1, 1.2));
        System.out.println(tilfeldig.nesteHeltall(11, 13));
    }
}

class Tilfeldig {
    Random random;

    public Tilfeldig(long seed) {
        random = new Random(seed);
    }

    public Tilfeldig() {
        random = new Random();
    }

    public int nesteHeltall(int nedre, int øvre) {
        if (nedre >= øvre) {
            throw new IllegalArgumentException("Øvre må være større enn nedre... Øvre: " + øvre + " Nedre: " + nedre);
        }
        int dif = øvre - nedre;
        return random.nextInt(dif + 1) + nedre;
    }

    public double nesteDesimaltall(double nedre, double øvre) {
        if (nedre >= øvre) {
            throw new IllegalArgumentException("Øvre må være større enn nedre... Øvre: " + øvre + " Nedre: " + nedre);
        }
        double dif = øvre - nedre;
        return random.nextDouble() * dif + nedre;
    }
}