public class Oppgave3 {

    public static void main(String[] args) {
        Matrise a = new Matrise(new int[][] { new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 }, new int[] { 7, 8, 9 } });
        Matrise b = new Matrise(
                new int[][] { new int[] { 10, 11, 12 }, new int[] { 13, 14, 15 }, new int[] { 16, 17, 18 } });
        Matrise c = new Matrise(new int[][] { new int[] { 1, 2 }, new int[] { 3, 4 }, new int[] { 5, 6 } });
        System.out.println(a.add(b));
        System.out.println(a.multi(b));
        System.out.println(a.multi(c));
        System.out.println(c.transponer());
        System.out.println(a.add(c));
    }
}

class Matrise {
    int n;
    int m;
    private int[][] matrise;

    Matrise(Matrise matrise) {
        this.n = matrise.n;
        this.m = matrise.m;

        int[][] array2d = new int[getN()][getM()];

        for (int n = 0; n < getN(); n++) {
            for (int m = 0; m < getM(); m++) {
                array2d[n][m] = matrise.get(n, m);
            }
        }

        this.matrise = array2d.clone();
    }

    Matrise(int[][] array2d) {
        int ySize = array2d.length;
        int xSize = array2d[0].length;

        for (int[] row : array2d) {
            if (row.length != xSize) {
                throw new IllegalArgumentException("Alle radene må ha samme lengde...");
            }
        }

        this.matrise = array2d.clone();
        setN(ySize);
        setM(xSize);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int[] getDimensjon() {
        return new int[] { getN(), getM() };
    }

    public int get(int n, int m) {
        return matrise[n][m];
    }

    public void set(int n, int m, int verdi) {
        matrise[n][m] = verdi;
    }

    public String toString() {
        String result = "[[";

        for (int n = 0; n < getN(); n++) {

            if (n != 0) {
                result = result.concat("\n");
            }

            for (int m = 0; m < getM(); m++) {
                result = result.concat(this.get(n, m) + ", ");
            }

            result = result.concat("]");
        }

        result = result.concat("]");
        return result;
    }

    Matrise add(Matrise matrise) {
        if (!(this.getN() == matrise.getN() && this.getM() == matrise.getM())) {
            throw new IllegalArgumentException("Dimensjonene til de to matrisene må være like");
        }
        int[][] array2d = new int[getN()][getM()];

        // adderer element for element
        for (int n = 0; n < getN(); n++) {
            for (int m = 0; m < getM(); m++) {
                array2d[n][m] = this.get(n, m) + matrise.get(n, m);
            }
        }

        return new Matrise(array2d);
    }

    Matrise multi(Matrise matrise) {
        if (this.getM() != matrise.getN()) {
            throw new IllegalArgumentException("Dimensjonene stemmer ikke overens");
        }

        int p = this.getM() - 1;
        int[][] array2d = new int[this.getN()][matrise.getM()];

        // gjør krysspeiling
        for (int n = 0; n < this.getN(); n++) {
            for (int m = 0; m < matrise.getM(); m++) {
                array2d[n][m] = 0;
                for (int i = 0; i < p; i++) {
                    array2d[n][m] += this.get(n, p) * matrise.get(p, m);
                }
            }
        }

        return new Matrise(array2d);
    }

    public Matrise transponer() {
        int[][] array2d = new int[getM()][getN()];
        for (int n = 0; n < getN(); n++) {
            for (int m = 0; m < getM(); m++) {
                array2d[m][n] = this.get(n, m);
            }
        }

        return new Matrise(array2d);
    }
}