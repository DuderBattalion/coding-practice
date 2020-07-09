public class TrappingRainWater {
    public static void main(String[] args) {
//        int[] height = { 0,1,0,2,1,0,1,3,2,1,2,1 };
        int[] height = { 1, 2, 1, 2 };
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int leftMaxHeight = Integer.MIN_VALUE;
        int rightMaxHeight = Integer.MIN_VALUE;

        int totalWater = 0;


        while (start < end) {
            if (height[start] < height[end]) {
                // Move start pointer right
                if (leftMaxHeight < height[start]) {
                    leftMaxHeight = height[start];
                } else {
                    totalWater += (leftMaxHeight - height[start]);
                }

                start++;
            } else {
                if (rightMaxHeight < height[end]) {
                    rightMaxHeight = height[end];
                } else {
                    totalWater += rightMaxHeight - height[end];
                }

                end--;
            }
        }

        return totalWater;
    }
}
