public class PowXN {
    public static void main(String[] args) {
        System.out.println(myPow(2, 10));
    }

    public static double myPow(double x, int n) {
        if (n < 0) {
            return (1 / myPow(x, n, 1));
        } else {
            return myPow(x, n, 1);
        }
    }

    public static double myPow(double x, int n, double output) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        double subOutput;
        if (n % 2 == 1) { // Odd
            subOutput = myPow(x, (n-1)/2, output);
            output = x * subOutput * subOutput;
        } else { // Even
            subOutput = myPow(x, n/2, output);
            output = subOutput * subOutput;
        }

        return output;
    }
}
