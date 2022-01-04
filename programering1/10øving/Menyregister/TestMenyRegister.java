public class TestMenyRegister {

    public static void main(String[] args) {
        MenyRegister testData = new MenyRegister();

        testData.leggTilMeny(new Meny(new Rett("Salat", "Kald forrett", "1 isbergsalat", 20f),
                new Rett("Fisketaco", "Fisk hovedrett", "1 fisk, 1 taco", 50f),
                new Rett("Biff", "Kjøtt hovedrett", "1 biff", 80f),
                new Rett("Pannekaker", "Søt dessert", "1 egg, 2 mel, 3 melk, 4 sukker", 10f)));
        testData.leggTilRett(new Rett("Kjøtttaco", "Kjøtt hovedrett", "1 kjøtt, 1 taco", 60f));
        testData.leggTilRett(new Rett("Gauda", "Ost", "1 Gauda (oppskjært)", 10f));
        testData.leggTilRett(new Rett("Iskrem", "Søt dessert", "Royal lakris", 15f));

        testData.leggTilMeny(Meny.lagMeny(testData, "Kjøtttaco", "Pannekaker"));
        testData.leggTilMeny(Meny.lagMeny(testData, "Fisketaco", "Gauda", "Iskrem"));

        for (Meny meny : testData.getMenyer()) {
            System.out.println(meny + "\n");
        }

        System.out.println(testData.finnRett("Pannekaker"));

        for (Rett rett : testData.finnRetterAvType("Kjøtt hovedrett")) {
            System.out.println(rett);
        }

        for (Meny meny : testData.finnMenyMedPrisMellom(65f, 75f)) {
            System.out.println(meny);
        }
    }
}
