public class Strengemanipulator {
    public static void main(String[] args) {
        NyString nyString = new NyString("denne setningen kan forkortes");
        System.out.println(nyString.forkort());
        System.out.println(nyString.fjern("e"));
    }
}