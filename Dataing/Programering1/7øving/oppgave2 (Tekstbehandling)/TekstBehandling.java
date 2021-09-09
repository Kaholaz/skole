import java.util.Scanner;

public class TekstBehandling {
    public static void main(String[] args) {
        TekstManipulator t = new TekstManipulator(
                "Jeg sitter her og programmerer. Det er ganske morro! Hvordan har du det?");

        Scanner sc = new Scanner(System.in);
        Meny hovedMeny = new Meny("Hva ønsker du å gjøre med teksten?",
                new String[] { "Finn antall ord i teksten", "Finn gjennomsnittlig ordlengde",
                        "Finn gjennomsnittlig antall ord pr. periode (setningner)", "Erstatt et ord",
                        "Vis hele teksten", "Vis hele teksten (store bokstaver)" },
                "Avslutt");

        // initialiserer valg
        int valg = hovedMeny.getSisteValgIndeks();
        while (true) {
            valg = hovedMeny.promptSvar(sc);

            // Hvisker ut alt i terminalen:
            Meny.clearScreen();

            switch (valg) {
                case 1: // "Finn antall ord i teksten"
                    System.out.printf("Antall ord i teksten er %d.\n", t.finnAntallOrd());
                    break;
                case 2: // "Finn gjennomsnittlig ordlengde"
                    System.out.printf("Den gjennomsnittlige ordlengden er %.2f.\n", t.bokstaverPerOrd());
                    break;
                case 3: // "Finn gjennomsnittlig antall ord pr. periode (setningner)"
                    System.out.printf("Gjennomsnittlig ord pr. periode er %.2f.\n", t.ordPerPeriode());
                    break;
                case 4: // "Erstatt et ord"
                    System.out.println("Hvilket ord ønsker du å erstatte?");
                    sc.nextLine(); // Jeg aner ikke hvorfor jeg må ha denne her, men hvis jeg tar den vekk
                                   // ødelegger jeg alt...
                    String finn = sc.nextLine();
                    System.out.println("Hvilket ord ønsker du å erstatte det med?");
                    String erstatt = sc.nextLine();

                    System.out.printf("Den nye teksten er som følger:\n%s\n", t.byttUt(finn, erstatt));
                    break;
                case 5: // "Vis hele teksten"
                    System.out.println(t.getString());
                    break;
                case 6: // "Vis hele teksten (store bokstaver)"
                    System.out.println(t.getStringStore());
                    break;
            }

            if (valg == hovedMeny.getSisteValgIndeks()) { // "Avslutt"
                break;
            } else {
                sc.next(); // venter på input fra brukeren før man går videre
            }

        }
        sc.close();
    }
}
