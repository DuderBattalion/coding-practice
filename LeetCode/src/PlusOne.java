import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PlusOne {
    public static void main(String[] args) {
        int[] digits = { 1, 2, 3 };
        int[] sumNum = plusOne(digits);

        Arrays.stream(sumNum).forEach(num -> System.out.print(num + ","));
    }

    public static int[] plusOne(int[] digits) {
        Queue<Integer> numQ = new LinkedList<>();

        int sum = 1;
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum += digits[i];
            numQ.add(sum);

            if (sum > 9) {
                carry = sum - 9;
            } else {
                carry = 0;
            }

            sum = carry;
        }

        if (carry > 0) {
            numQ.add(carry);
        }

        int[] sumNum = new int[numQ.size()];
        int i = 0;
        while (!numQ.isEmpty()) {
            sumNum[i] = numQ.remove();
            i++;
        }

        return sumNum;
    }


}
