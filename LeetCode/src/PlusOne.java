import java.util.*;

public class PlusOne {
    public static void main(String[] args) {
//        int[] digits = { 4, 3, 2, 1 };
//        int[] digits = { 9, 9, 9 };
//        int[] digits = { 9 };
        int[] digits = { 1 };

        int[] sumNum = plusOne(digits);

        Arrays.stream(sumNum).forEach(num -> System.out.print(num + ","));
    }

    public static int[] plusOne(int[] digits) {
        Deque<Integer> numStack = new ArrayDeque<>();

        int sum = 1;
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum += digits[i];

            if (sum > 9) {
                carry = sum - 9;
            } else {
                carry = 0;
            }

            numStack.push(sum % 10);
            sum = carry;
        }

        if (carry > 0) {
            numStack.push(carry);
        }

        int[] sumNum = new int[numStack.size()];
        int i = 0;
        while (!numStack.isEmpty()) {
            sumNum[i] = numStack.pop();
            i++;
        }

        return sumNum;
    }


}
