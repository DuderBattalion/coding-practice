import java.util.ArrayList;
import java.util.List;

public class PatchingArray {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };

        // Test block
        List<List<Integer>> numberSets = generatePowerSet(nums);
        for (List<Integer> numberSet: numberSets) {
            for (int num: numberSet) {
                System.out.print(num + ",");
            }

            System.out.println();
        }

        System.out.println(numberSets.size());
    }

    /**
     * Algorithm
     * Generate combinations.
     * Calculate number sums that are covered already.
     * Find smallest missing number that isn't covered yet.
     *
     * Add number to existing sets.
     * Calculate new number sums, and update number cover.
     * Keep doing this until all number sums are covered till N.
     */
    public static int minPatches(int[] nums, int n) {
        List<List<Integer>> numberSets = generatePowerSet(nums);
        List<Integer> numberSums = calculateNumberSums(numberSets);

        int patchCount = 0;
        int smallestMissingNumber = getSmallestMissingNumber(numberSums, n);
        while (smallestMissingNumber > 0) {
            numberSets = extendNumberSets(numberSets, smallestMissingNumber);
            numberSums = calculateNumberSums(numberSets);
            patchCount++;

            smallestMissingNumber = getSmallestMissingNumber(numberSums, n);
        }

        return patchCount;
    }

    private static List<List<Integer>> generatePowerSet(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<>();

        double powerSetSize = Math.pow(2, nums.length);
        for (int i = 0; i < powerSetSize; i++) {
            List<Integer> numberSet = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                // Checks jth bit. This will decide if nums[j]
                // should be included in set or not.
                int mask = 1 << j;

                // If jth digit in 'i' is set
                if ((i & mask) > 0) {
                    numberSet.add(nums[j]);
                }
            }

            powerSet.add(numberSet);
        }

        return powerSet;
    }
}
