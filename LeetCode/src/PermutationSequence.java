public class PermutationSequence {
    public static void main(String[] args) {

    }

    public String getPermutation(int n, int k) {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= n; i++) {
                double quotient = Math.ceil(k / getFactorial(n - i));
                int remainder = k % n;

                output.append(quotient);


        }
    }
}
