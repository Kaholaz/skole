import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Oppgave2
 */
public class Oppgave2 {

    public static void main(String[] args) {

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

    public char[] finnVanligsteBokstav() {
        int max = Arrays.stream(getAntallBokstaver()).reduce(0, (a, b) -> Math.max(a, b));
        List<Character> bokstaver = new ArrayList<Character>();
        for (int i = 0; i < getAntallBokstaver().length; i++) {
            if (getAntallBokstaver()[i] == max) {
                bokstaver.add(finnBokstav(i));
            }
        }
        char[] result = new char[bokstaver.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bokstaver.get(i);
        }
        return result;
    }

    public void nullstill() {
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