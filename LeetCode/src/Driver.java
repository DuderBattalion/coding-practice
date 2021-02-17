public class Driver {
    public static int[] count;

    public static void main(String[] args) {
        int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };

        RangeSumQuery2DImmutable obj = new RangeSumQuery2DImmutable(matrix);
        int param1 = obj.sumRegion(1, 2, 2, 4);

        System.out.println("Param1: " + param1);
    }
}
