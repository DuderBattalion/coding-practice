public class PowXN {
    public static void main(String[] args) {
        System.out.println(myPow(2, 10));
    }

    public static double myPow(double x, int n) {
        if (n < 0) {
            return (1 / myPowRecurse(x, n));
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
