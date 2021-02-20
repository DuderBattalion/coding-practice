import com.leetcode.util.FenwickTreeSum;

public class Driver {
    public static int[] count;

    public static void main(String[] args) {
//        int[][] matrix = {
//            {3, 0, 1, 4, 2},
//            {5, 6, 3, 2, 1},
//            {1, 2, 0, 1, 5},
//            {4, 1, 0, 1, 7},
//            {1, 0, 3, 0, 5}
//        };
//
////        RangeSumQuery2DImmutable obj = new RangeSumQuery2DImmutable(matrix);
////        int param1 = obj.sumRegion(1, 2, 2, 4);
//
//        RangeSumQuery2DMutable obj = new RangeSumQuery2DMutable(matrix);
//        int param1 = obj.sumRegion(1, 2, 2, 4);
//
//        System.out.println("Param1: " + param1);

        LFUCache obj = new LFUCache(2);
        obj.put(1, 1);
        obj.put(2, 2);

        System.out.println("GET 1: " + obj.get(1));

        obj.put(3, 3);

        System.out.println("GET 2: " + obj.get(2));
        System.out.println("GET 3: " + obj.get(3));

        obj.put(4, 4);

        System.out.println("GET 1: " + obj.get(1));
        System.out.println("GET 3: " + obj.get(3));
        System.out.println("GET 4: " + obj.get(4));
    }
}
