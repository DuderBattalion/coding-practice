public class PowXN {
    public static void main(String[] args) {
        System.out.println(myPow(2, 10));
        System.out.println(myPow(2.1, 3));
        System.out.println(myPow(2, -2));
        System.out.println(myPow(-2, 4));
        System.out.println(myPow(99, Integer.MAX_VALUE));
        System.out.println(myPow(99, Integer.MIN_VALUE));
        System.out.println(myPow(1, Integer.MIN_VALUE));
        System.out.println(myPow(-1, Integer.MIN_VALUE));
    }

    public static double myPow(double x, int n) {
        if (x == 1) {
            return 1;
        }

        if (x == -1) {
            return (n % 2) == 0 ? 1 : -1;
        }

        if (n == Integer.MIN_VALUE) {
            return 0;
        }

        if (n < 0) {
            return (1 / myPowRecurse(x, n * -1));
        } else {
            return myPowRecurse(x, n);
        }
    }

    public static double myPowRecurse(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        double subOutput, output;
        if (n % 2 == 1) { // Odd
            subOutput = myPow(x, (n-1)/2);
            output = x * subOutput * subOutput;
        } else { // Even
            subOutput = myPow(x, n/2);
            output = subOutput * subOutput;
        }

        return output;
    }
}
