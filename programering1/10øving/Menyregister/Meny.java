import java.util.Arrays;

/**
 * Meny er en klasse som behandler menyer som består av retter
 */
public class Meny {
    private Rett[] retter;

    /**
     * Konstruktør av en meny
     * 
     * @param retter Rettene som er en del av menyen. Rettene må følge menynøkkelen.
     */
    Meny(Rett... retter) {
        int forrige = -1, nåværende = -1;

        for (Rett rett : retter) {
            for (int i = 0; i < MenyRegister.MENY_NØKKEL.length; i++) {
                if (rett.getType() == MenyRegister.MENY_NØKKEL[i]) {
                    nåværende = i;
                    break;
                }
            }

            if (nåværende <= forrige) {
                throw new IllegalArgumentException(
                        "Rettene må følge rekkefølgen av menynøkkelen\n" + MenyRegister.MENY_NØKKEL[nåværende]
                                + " må komme etter i menyen " + MenyRegister.MENY_NØKKEL[forrige]);
            }

            forrige = nåværende;
        }

        this.retter = Arrays.copyOf(retter, retter.length);
    }

    /**
     * Lager et meny-objekt som bruker rettene i et menyregister
     * 
     * @param menyRegister  Registeret med rettene
     * @param retterStrings Navnet på rettene
     * @return Et meny-objekt med de gitte rettene
     */
    public static Meny lagMeny(MenyRegister menyRegister, String... retterStrings) {
        Rett[] retter = new Rett[retterStrings.length];

        for (int i = 0; i < retter.length; i++) {
            if (!menyRegister.getRetter().containsKey(retterStrings[i].toLowerCase())) {
                throw new IllegalArgumentException("Retten " + retter[i] + " finnes ikke i menyregisteret...");
            }

            retter[i] = menyRegister.getRetter().get(retterStrings[i].toLowerCase());
        }

        return new Meny(retter);
    }

    /**
     * @return Rettene i menyen
     */
    public Rett[] getRetter() {
        return retter;
    }

    /**
     * @return Returnerer prisen til menyen som summen av prisen til rettene.
     */
    public float getPris() {
        float pris = 0f;
        for (Rett rett : retter) {
            pris += rett.getPris();
        }

        return pris;
    }

    /**
     * Konverterer til string på denne måten:
     * 
     * Type: Navn,<br>
     * Type: Navn,<br>
     * Type: Navn,<br>
     * Pris: pris
     */
    @Override
    public String toString() {
        String result = "";
        for (Rett rett : retter) {
            result += rett.getType() + ": " + rett.getNavn() + ",\n";
        }
        result += "Pris: " + getPris();
        return result;
    }
}