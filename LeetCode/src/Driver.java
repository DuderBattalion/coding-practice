public class Driver {
    public static int[] count;

    public static void main(String[] args) {
//        int[] nums = { 1, 3, 7, 2, 6 };

//        int[] nums = { 1, 3, 7, 2, 6, 3, 4, 2 };

//        int[] nums = {  };

        int[] nums = { 1, 1, 1, 2, 3, 2, 3, 5, 4 };

        DataStreamDisjointIntervals dataStream = new DataStreamDisjointIntervals();
        for (int num: nums) {
            dataStream.addNum(num);

            int[][] intervals = dataStream.getIntervals();
            for (int[] interval: intervals) {
                System.out.print("[ " + interval[0] + ", " + interval[1] + " ],");
            }

            System.out.println();
        }
    }
}
