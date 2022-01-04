import java.util.Scanner;

public class oppgave2 {
    public static void main(String[] args) {
        String input;
        long num;

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Hvilket tall vil du undersøke om er et primtall? [skriv 'avslutt' for å avslutte]");
            input = sc.nextLine();
            if (input.toLowerCase() == "avslutt") {
                sc.close();
                break;
            }
            try {
                num = Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("FEIL! Skriv in et heltall!");
                System.out.println("Venligst prøv på nytt...\n");
                continue;
            }
            System.out.println(num + " er " + (isPrime(num) ? "" : "ikke ") + "et primtall");
        }
    }

    static Boolean isPrime(long num) {
        if (num < 2) {
            return false;
        } else if (num < 4) {
            return true;
        }

        if (num % 2 == 0) {
            return false;
        }

        for (long i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
