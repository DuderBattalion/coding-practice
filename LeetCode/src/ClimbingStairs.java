public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }

    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int stepMinusOne = 2, stepMinusTwo = 1;
        for (int i = 2; i < n; i++) {
            int nextStep = stepMinusOne + stepMinusTwo;
            stepMinusTwo = stepMinusOne;
            stepMinusOne = nextStep;
        }

        return stepMinusOne;
    }
}
