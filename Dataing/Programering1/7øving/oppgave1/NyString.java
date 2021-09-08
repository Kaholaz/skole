class NyString {
    private String string;

    NyString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    /**
     * Forkorter strengen i instansen av NyStreng (første bokstaven i hvert ord)
     * 
     * @return En streng som består av forbokstaven til hvert av ordene
     */
    public String forkort() {
        String[] split = getString().split("\\s+");

        String resultat = "";
        for (String string : split) {
            resultat = resultat.concat(string.substring(0, 1));
        }

        return resultat;
    }

    /**
     * Fjerner alle forekoster av en gitt streng fra hele strengen til NyStreng
     * 
     * @param str Strengen du vil fjærne forekonmster av
     * @return En streng uten forekomster av den gitte strengen ({@code str})
     */
    public String fjern(String str) {
        return getString().replace(str, "");
    }
}