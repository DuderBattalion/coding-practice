import com.leetcode.util.MonotonicDecreasingQueue;

public class MaximumSlidingWindow {
    public static void main(String[] args) {
        int[] nums = { 1,3,-1,-3,5,3,6,7 };

        int[] maxValues = maxSlidingWindow(nums, 3);
        for (int num: maxValues) {
            System.out.print(num + ", ");
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k > nums.length) {
            k = nums.length;
        }

        MonotonicDecreasingQueue<Integer> monotonicQueue = new MonotonicDecreasingQueue<>();
        for (int i = 0; i < k - 1; i++) {
            monotonicQueue.push(nums[i]);
        }

        int[] maxValues = new int[nums.length - (k - 1)];
        for (int i = k - 1; i < nums.length; i++) {
            monotonicQueue.push(nums[i]);
            maxValues[i - (k - 1)] = monotonicQueue.peek();
            monotonicQueue.pop(nums[i]);
        }

        return maxValues;
    }
}
