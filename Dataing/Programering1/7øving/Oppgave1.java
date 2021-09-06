import javax.naming.spi.DirStateFactory.Result;

/**
 * Oppgave1
 */
public class Oppgave1 {

}

class NyString {
    private String string;

    NyString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public String forkort() {
        return String.join("", string.split("\\w"));
    }

    public String fjern(String str) {
        String result = str;

        int j = 0;
        for (int i = result.indexOf(str); i != -1; i = result.indexOf(str, j)) {
            result = result.substring(0, i) + result.substring(i + str.length(), result.length() - 1)
            j = i - str.length() + 1;
        }
    }
}