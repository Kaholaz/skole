import java.util.Random;

public class oppgave2 {
    static Random terning = new Random();

    public static void main(String[] args) {
        Spiller[] spillere = new Spiller[] { new Spiller("Spiller 1"), new Spiller("Spiller 2") };
        int runde = 1;
        while (spillere[0].getSumPoeng() < 100 && spillere[1].getSumPoeng() < 100) {
            System.out.println("Runde " + runde + ":");
            for (Spiller spiller : spillere) {
                spiller.kastTerningen();
                System.out.printf("%s: %d | ", spiller.getNavn(), spiller.getSumPoeng());
            }
            System.out.println();
            runde++;
        }
        Spiller spiller = (spillere[0].getSumPoeng() > 100) ? spillere[0] : spillere[1];
        System.out.println(spiller.getNavn() + " vant!");
    }

    static class Spiller {
        private int sumPoeng;
        private String navn;

        public Spiller(String navn) {
            this.navn = navn;
        }

        public String getNavn() {
            return navn;
        }

        public int getSumPoeng() {
            return sumPoeng;
        }

        public void kastTerningen() {
            int terningkast = terning.nextInt(6) + 1;
            if (terningkast == 1) {
                sumPoeng = 0;
            } else {
                sumPoeng += terningkast;
            }
        }
    }
}
