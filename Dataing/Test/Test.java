public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println("Hei på deg");
        } finally {
            System.out.println("test");
        }

        System.out.println("Uten finally");
    }
}