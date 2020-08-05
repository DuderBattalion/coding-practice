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
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid <= (x/mid) && (mid + 1) > (x / (mid + 1))) {
                return mid;
            } else if (mid > (x / mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
