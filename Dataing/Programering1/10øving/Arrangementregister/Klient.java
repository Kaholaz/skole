import java.util.Scanner;

public class Klient {
    // alle valgene brukeren kan ta:
    private static final int NYTT_ARRANGEMENT = 1, LIST_ARRANGEMENT_PÅ_STED = 2, LIST_ARRANGEMENT_PÅ_DATO = 3,
            LIST_ARRANGEMENT_I_TIDSINTERVALL = 4, LISTE_OVER_ALLE_ARRANGEMENT = 5, AVSLUTT = 6;

    public static void main(String[] args) {
        Meny meny = new Meny("\n  **ARRANGEMENTREGISTER** \n", "Legg til et arrangement",
                "Vis alle arrangementer på et sted", "Vis alle arrangementer på en dato",
                "Vis alle arrangementer i et tidsintervall", "Vis alle arrangementer", "Avslutt");
        Scanner scanner = new Scanner(System.in);

        Boolean avslutt = false;
        while (!avslutt) {
            int valg = meny.promptSvar(scanner);

            switch (valg) {
                case NYTT_ARRANGEMENT:
                    // TODO
                    break;
                case LIST_ARRANGEMENT_PÅ_STED:
                    // TODO
                    break;
                case LIST_ARRANGEMENT_PÅ_DATO:
                    // TODO
                    break;
                case LIST_ARRANGEMENT_I_TIDSINTERVALL:
                    // TODO
                    break;
                case LISTE_OVER_ALLE_ARRANGEMENT:
                    // TODO
                    break;
                case AVSLUTT:
                    avslutt = true;
                    break;
            }
        }
    }
}
