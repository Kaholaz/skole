import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static OppgaveOversikt studenter = new OppgaveOversikt();

    public static void main(String[] args) {
        Meny meny = new Meny("Hva ønsker du å gjøre?", "Finn antall registrerte studenter",
                "Finn antall oppgaver som en bestemt student har løst", "Registrer en ny student",
                "Øk antall oppgaver for en bestemt student", "Avslutt");

        while (true) {
            int valg = meny.promptSvar(scanner);

            // går ut av løkken hvis avslutt ble valgt
            if (valg == meny.getSisteValgIndeks()) {
                break;
            }

            String studentNavn;

            switch (valg) {
                case 1: // antall registrerte studenter
                    System.out.println("Antall studenter er " + studenter.getAntallStudenter());
                    break;
                case 2: // antall løste oppgaver
                    studentNavn = spørStudentNavn();
                    System.out.printf("%s har gjort %d oppgaver.\n", studentNavn,
                            studenter.finnAntallOppgaver(studentNavn));
                    break;
                case 3: // registrer ny student
                    studentNavn = spørNyttStudentNavn();
                    studenter.registrerStudent(studentNavn);
                    System.out.println("Student registrert!");
                    break;
                case 4: // Øk antall oppgaver for en student
                    studentNavn = spørStudentNavn();
                    int økning = Meny.spørInt("Hvor mange oppgaver ønsker du å legge til?", 1,
                            Integer.MAX_VALUE - studenter.finnAntallOppgaver(studentNavn), scanner);
                    studenter.økAntOppg(studentNavn, økning);
                    System.out.println("Oppgaver lagt til!");
                    break;
            }

            scanner.next();
        }

        System.out.println(studenter);

    }

    /**
     * Spør brukeren om navnet på en student og sørger for at navnet finnes i
     * studentregisteret.
     * 
     * @return Navnet på en student i studentregisteret
     */
    private static String spørStudentNavn() {
        while (true) {
            String studentNavn = Meny.spørString("Hva er navnet til studenten:", scanner);

            if (studenter.getStudenter().contains(studentNavn.toLowerCase())) {
                return studentNavn.toLowerCase();
            } else {
                System.out.println("Det finnes ingen elev som heter " + studentNavn);
                System.out.println("Venligst prøv på nytt!");
            }
        }
    }

    /**
     * Spør brukeren om navnet på en student og sørger for at navnet IKKE finnes i
     * studentregisteret.
     * 
     * @return Navnet på en student som ikke er i studentregisteret
     */
    private static String spørNyttStudentNavn() {
        while (true) {
            String studentNavn = Meny.spørString("Hva er navnet til studenten:", scanner);

            if (studenter.getStudenter().contains(studentNavn.toLowerCase())) {
                System.out.println("Studenten finnes allerede!");
                System.out.println("Venligst skriv in et navn som ikke allerede er brukt...");
            } else {
                return studentNavn;
            }
        }
    }
}
