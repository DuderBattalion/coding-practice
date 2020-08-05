public class Sqrt {
    public static void main(String[] args) {
//        for (int i = 0; i < 20; i++) {
//            System.out.println(i + ": " + mySqrt(i));
//        }

        System.out.println(mySqrt(2147395599));
        System.out.println(Integer.MAX_VALUE - 2147395599);
    }

    public static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int start = 1;
        int end = x;
        int sqrt = -1;
        while (start < end) {
            int mid = start + (int)Math.floor((end - start) / 2.0);
            if (mid * mid == x) {
                sqrt = mid;
                break;
            }

            if (mid * mid > x) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        if (sqrt < 0) {
            sqrt = start - 1;
        }

        return sqrt;
    }
}
