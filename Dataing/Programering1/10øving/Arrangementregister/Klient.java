import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Klient {
    // alle valgene brukeren kan ta:
    private static final int NYTT_ARRANGEMENT = 1, LIST_ARRANGEMENT_PÅ_STED = 2, LIST_ARRANGEMENT_PÅ_DATO = 3,
            LIST_ARRANGEMENT_I_TIDSINTERVALL = 4, LISTE_OVER_ALLE_ARRANGEMENT = 5, AVSLUTT = 6;

    public static void main(String[] args) {
        // Legger til alle valgalternativene i den brukerstyrte tekstbaserte menyen.
        Meny meny = new Meny("\n  **ARRANGEMENTREGISTER** \n", "Legg til et arrangement",
                "Vis alle arrangementer på et sted", "Vis alle arrangementer på en dato",
                "Vis alle arrangementer i et tidsintervall", "Vis alle arrangementer", "Avslutt");
        Scanner scanner = new Scanner(System.in);
        ArrangementRegister ar = new ArrangementRegister();

        Boolean avslutt = false;
        while (!avslutt) {
            int valg = meny.promptSvar(scanner);

            switch (valg) {
                case NYTT_ARRANGEMENT: {
                    String navn = Meny.spørString("Hva er navnet på arrangementet?", scanner);
                    String sted = Meny.spørString("Hvilket sted skal arrangementet holdes?", scanner);
                    String arrangør = Meny.spørString("Hvem er arrangøren?", scanner);
                    String type = Meny.spørString("Hva slags type arrangement skal avholdes?", scanner);
                    Long tidspunkt = Meny.spørLong("Når skal arrangementet avholdes? [YYYYMMDDhhmm]", 190000000000L,
                            999912312359L, scanner);

                    Arrangement nyttArrangement = new Arrangement(navn, sted, arrangør, type, tidspunkt);
                    ar.leggTilArrangement(nyttArrangement);
                    System.out.println("Arrangement lagt til!");
                    break;
                }
                case LIST_ARRANGEMENT_PÅ_STED: {
                    String sted = Meny.spørString("Hvilket sted ønsker du å undersøke?", scanner);
                    List<Object> arrangementer = new ArrayList<Object>(ar.finnArrangementerPåSted(sted));

                    System.out.println(stringList(arrangementer, "Fant ingen arrangementer på det gitte stedet."));
                    break;
                }
                case LIST_ARRANGEMENT_PÅ_DATO: {
                    Long dato = Meny.spørLong("Hvilken dato ønsker du å undersøke? [YYYYMMDD]", scanner);
                    List<Object> arrangementer = new ArrayList<Object>(ar.finnArrangementerPåDato(dato));

                    System.out.println(stringList(arrangementer, "Fant ingen arrangementer på den gitte datoen."));
                    break;
                }
                case LIST_ARRANGEMENT_I_TIDSINTERVALL: {
                    long start = Meny.spørLong("Fra hvilken dato vil du undersøke? (inkusiv) [YYYYMMDD]", scanner);
                    long slutt = Meny.spørLong("Til hvilken dato vil du undersøke? (inkusiv) [YYYYMMDD]", scanner);
                    List<Object> arrangementer = new ArrayList<Object>(
                            ar.finnArrangementerIDatoIntervall(start, slutt));

                    System.out.println(stringList(arrangementer, "Fant ingen arrangementer på det gitte stedet."));
                    break;
                }
                case LISTE_OVER_ALLE_ARRANGEMENT: {
                    List<Object> arrangementer = new ArrayList<Object>(ar.getArrangementer());

                    System.out.println(stringList(arrangementer, "Fant ingen arrangementer i arrangementregisteret."));
                    break;
                }
                case AVSLUTT: {
                    avslutt = true;
                    break;
                }
            }
        }
    }

    /**
     * Returnerer en string som representerer en liste med objekter der alle
     * objektene er gjort om til strenger og separert med newline.
     * 
     * @param list        En liste med objekter som skal omgjøres til en streng.
     * @param feilmelding Feilmeldingen som skal returneres dersom listen er tom.
     * @return En streng som representerer en liste med objekter.
     */
    public static String stringList(List<Object> list, String feilmelding) {
        if (list.size() == 0) {
            return feilmelding;
        }

        StringBuilder sb = new StringBuilder();
        for (Object element : list) {
            sb.append(element);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
