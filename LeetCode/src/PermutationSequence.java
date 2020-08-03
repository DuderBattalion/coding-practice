import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PermutationSequence {
    public static void main(String[] args) {
//        System.out.println(getPermutation(4, 9));
        System.out.println(getPermutation(4, 9));
    }

    public static String getPermutation(int n, int k) {
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }



        StringBuilder output = new StringBuilder();
        for (int i = n - 1; i > 0; i--) {
                if (k <= 0) {
                    break;
                }

                double nFact = getFactorial(i);
                int quotient = (int) Math.ceil(k/nFact);
                k -= (quotient - 1) * (int) nFact;

                int token = nums.remove(quotient - 1);
                output.append(token);
        }

        nums.forEach(output::append);
        return output.toString();
    }

    private static double getFactorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }

        double factorial = 1.0;
        for (int i = 2; i <= num; i++) {
            factorial *= i;
        }

        return factorial;
    }
}
