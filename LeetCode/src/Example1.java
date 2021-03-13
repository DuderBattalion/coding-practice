public class Example1 {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 15 == 0) {
                System.out.println("FB");
            } else if (i % 5 == 0) {
                System.out.println("B");
            } else if (i % 3 == 0) {
                System.out.println("F");
            } else {
                System.out.println(i);
            }
        }
    }
}
