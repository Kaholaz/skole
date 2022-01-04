package Oppgave1;

public class Brøkregner {
    public static void main(String[] args) {
        System.out.println(new Brøk(1, 2).add(new Brøk(1, 2)));
        System.out.println(new Brøk(2, 5).add(new Brøk(5, -3)));
        System.out.println(new Brøk(6, 12).add(new Brøk(6, 12)));
        System.out.println(new Brøk(1231, 12342).add(new Brøk(3)));
        System.out.println(new Brøk(2).add(new Brøk(3)));
        System.out.println(new Brøk(5, 3).sub(new Brøk(2, 3)));
    }
}