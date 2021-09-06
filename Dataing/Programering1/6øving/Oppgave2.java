import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Oppgave2
 */
public class Oppgave2 {
    static Scanner sc = new Scanner(System.in);
    static final String FEILMELDING = "Valget stemmer ikke overens med alternativene...\nVenligst prøv på nytt!";

    public static void main(String[] args) {
        String tekst = "ååå";
        Tekstanalyse t = new Tekstanalyse(tekst);

        while (true) {
            int valg = meny("Hva ønsker du å vite om teksten?",
                    new String[] { "Antall forskjellige unike bokstaver", "Totalt antall bokstaver",
                            "Hvor stor andel av teksten som ikke er bokstaver", "Antall forekomster av en gitt bokstav",
                            "Hvilken/hvilke bokstaver som forekommer oftest" },
                    "Avslutt");

            if (valg == 6) {
                break;
            }

            switch (valg) {
                case 1:
                    System.out.printf("Antall forskjellige bokstaver er %d.", t.finnAntallUnike());
                    break;
                case 2:
                    System.out.printf("Antall bokstaver er %d.", t.finnAntallBokstaver());
                    break;
                case 3:
                    System.out.printf("Andelen av teksten som ikke er bokstaver er %.2f%%.",
                            t.finnAndelIkkeBokstaver() * 100);
                    break;
                case 4:
                    System.out.println("Hvilken bokstav ønsker du å undersøke?");
                    String in = sc.next();
                    char bokstav = in.charAt(0);
                    System.out.printf("Andelen av teksten som er bokstaven '%s' er %d.", bokstav,
                            t.finnForekomster(bokstav));
                    break;
                case 5:
                    String[] chars = t.finnVanligsteBokstav();
                    System.out.printf("%s er [%s]",
                            ((chars.length == 1) ? "Den vanligste bokstaven" : "De vanligste bokstavene"),
                            String.join(", ", chars));
                    break;
                default:
                    throw new IllegalStateException("valg har en uventet verdi");
            }
            sc.next();
        }
    }

    /**
     * Denne funksjonen viser en valgmeny for brukeren og validerer brukerinputet.
     * 
     * @param spørsmål Spørsmålet som printes øverst i valgmenyen
     * @param valg     Dette er svaralternativene
     * @return Retuernerer et heltall som korresponderer med svaralternativene der 1
     *         er det første alternativet.
     */
    static int meny(String spørsmål, String[] valg) {
        int tall;
        boolean fail = false;
        while (true) {
            clearScreen();
            System.out.println(spørsmål);
            for (int i = 0; i < valg.length; i++) {
                System.out.println((i + 1) + ". " + valg[i]);
            }
            if (fail) {
                System.out.println(FEILMELDING);
            }

            if (sc.hasNextInt()) { // Hvis brukeren ikke taster inn et heltall, blir tallet satt til -1 slik at
                                   // brukeren blir vist feilmeldingen nedenfor.
                tall = sc.nextInt();
            } else {
                sc.next();
                tall = -1;
            }

            if (tall < 1 || tall > valg.length) {
                fail = true;
                continue;
            }
            break;
        }
        return tall;
    }

    /**
     * Denne funksjonen viser en valgmeny for brukeren og validerer brukerinputet.
     * 
     * @param spørsmål  Spørsmålet som printes øverst i valgmenyen
     * @param valg      Dette er svaralternativene
     * @param sisteValg Åpner muligheten for å legge til et siste svaralternativ,
     *                  gjerne 'Avslutt' eller 'Tilbake'
     * @return Retuernerer et heltall som korresponderer med svaralternativene der 1
     *         er det første alternativet.
     */
    static int meny(String spørsmål, String[] valg, String sisteValg) {
        String[] _valg = new String[valg.length + 1];
        for (int i = 0; i < valg.length; i++) {
            _valg[i] = valg[i];
        }
        _valg[valg.length] = sisteValg;
        return meny(spørsmål, _valg);
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class Tekstanalyse {
    private int[] antallTegn = new int[30];

    Tekstanalyse(String string) {
        analyser(string);
    }

    Tekstanalyse() {
        nullstill();
    }

    public int[] getAntallTegn() {
        return antallTegn;
    }

    public int[] getAntallBokstaver() {
        int[] result = new int[29];

        for (int i = 0; i < result.length; i++) {
            result[i] = getAntallTegn()[i];
        }
        return result;
    }

    public void analyser(String string) {
        nullstill();
        String lowerString = string.toLowerCase();
        for (int i = 0; i < lowerString.length(); i++) {
            char tegn = lowerString.charAt(i);
            antallTegn[finnIndeks(tegn)] += 1;
        }
    }

    public int finnAntallUnike() {
        int result = 0;
        for (int bokstaver : getAntallBokstaver()) {
            if (bokstaver != 0) {
                result++;
            }
        }
        return result;
    }

    public int finnAntallBokstaver() {
        return Arrays.stream(getAntallBokstaver()).sum();
    }

    public double finnAndelIkkeBokstaver() {
        return (double) getAntallTegn()[29] / (double) Arrays.stream(getAntallTegn()).sum();
    }

    public int finnForekomster(char tegn) {
        int index = finnIndeks(tegn);
        if (index != 29) {
            return getAntallTegn()[finnIndeks(tegn)];
        } else {
            throw new IllegalArgumentException("Parameteren tegn må være en bokstav fra a-å");
        }
    }

    public String[] finnVanligsteBokstav() {
        int max = Arrays.stream(getAntallBokstaver()).reduce(0, (a, b) -> Math.max(a, b));
        List<Character> bokstaver = new ArrayList<Character>();
        for (int i = 0; i < getAntallBokstaver().length; i++) {
            if (getAntallBokstaver()[i] == max) {
                bokstaver.add(finnBokstav(i));
            }
        }
        String[] result = new String[bokstaver.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = String.valueOf(bokstaver.get(i));
        }
        return result;
    }

    private void nullstill() {
        for (int i = 0; i < getAntallTegn().length; i++) {
            antallTegn[i] = 0;
        }

    }

    private int finnIndeks(char tegn) {
        if (tegn >= 97 && 122 >= tegn) { // a-z
            return (int) tegn - 97;
        } else if (tegn == 230) { // æ
            return 26;
        } else if (tegn == 248) { // ø
            return 27;
        } else if (tegn == 229) { // å
            return 28;
        } else { // alt annet
            return 29;
        }
    }

    private char finnBokstav(int indeks) {
        if (indeks < 0 || indeks >= getAntallBokstaver().length) {
            throw new IllegalArgumentException("Indeksen må være mellom i intervallet [0, 28]");
        }
        if (indeks < 26) {
            return (char) (indeks + 97);
        } else if (indeks == 26) {
            return 'æ';
        } else if (indeks == 27) {
            return 'ø';
        } else if (indeks == 28) {
            return 'å';
        }
        throw new IllegalArgumentException("Indeksen må være mellom i intervallet [0, 28]");
    }
}