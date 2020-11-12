import java.util.*;

public class PatchingArraySubOptimal {
    public static void main(String[] args) {
        // Test block

        // Test - generatePowerSet(nums);
//        int[] nums = { 1, 2, 3, 4 };
//        List<List<Integer>> numberSets = generatePowerSet(nums);
//        for (List<Integer> numberSet: numberSets) {
//            for (int num: numberSet) {
//                System.out.print(num + ",");
//            }
//
//            System.out.println();
//        }
//
//        System.out.println(numberSets.size());

        // Test - calculateNumberSums()
//        List<List<Integer>> numberSets = new ArrayList<>();
//
//        List<Integer> numberSet1 = new ArrayList<>();
//        numberSet1.add(1);
//        numberSet1.add(2);
//        numberSet1.add(3);
//
//        List<Integer> numberSet2 = new ArrayList<>();
//        numberSet2.add(4);
//        numberSet2.add(5);
//        numberSet2.add(6);
//
//        numberSets.add(numberSet1);
//        numberSets.add(numberSet2);
//
//        System.out.println(calculateNumberSums(numberSets));

        // Test - getFirstMissingNumber()
//        SortedSet<Integer> sortedNumbers = new TreeSet<>();
//        sortedNumbers.add(3);
//        sortedNumbers.add(2);
//        sortedNumbers.add(2);
//        sortedNumbers.add(1);
//        sortedNumbers.add(5);
//
//        System.out.println(getFirstMissingNumber(sortedNumbers, 100));
//        System.out.println(getFirstMissingNumber(sortedNumbers, 3));

//        int[] nums = { 1, 3 };
//        System.out.println(minPatches(nums, 6));

//        int[] nums = { 1, 5, 10 };
//        System.out.println(minPatches(nums, 20));

//        int[] nums = { 1, 2, 2 };
//        System.out.println(minPatches(nums, 5));
//
//        int[] nums = {  };
//        System.out.println(minPatches(nums, 7));

        int[] nums = { 1, 2, 31, 33 };
        System.out.println(minPatches(nums, 2147483647));

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
        Set<Integer> numberSums = calculateNumberSums(numberSets);

        int patchCount = 0;
        int missingNumber = getFirstMissingNumber(numberSums, 1, n);
        while (missingNumber > 0) {
            numberSets = extendNumberSets(numberSets, missingNumber);
            numberSums = calculateNumberSums(numberSets);
            patchCount++;

            missingNumber = getFirstMissingNumber(numberSums, missingNumber, n);
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

            if (!numberSet.isEmpty()) {
                powerSet.add(numberSet);
            }
        }

        return powerSet;
    }

    private static Set<Integer> calculateNumberSums(List<List<Integer>> numberSets) {
        Set<Integer> numberSums = new HashSet<>();

        for (List<Integer> numberSet: numberSets) {
            int count = 0;
            for (int num: numberSet) {
                count += num;
            }

            numberSums.add(count);
        }

        return numberSums;
    }

    // TODO - start index
    private static int getFirstMissingNumber(Set<Integer> numbers, int start, int n) {
        if (numbers.isEmpty()) {
            return start;
        }

        Integer missingNum = null;
        for (int i = start; i <= n; i++) {
            if (!numbers.contains(i)) {
                missingNum = i;
                break;
            }
        }

        return missingNum == null ? -1 : missingNum;
    }

    private static List<List<Integer>> extendNumberSets(
            List<List<Integer>> numberSets, int missingNumber) {
        List<List<Integer>> extendedSets = new ArrayList<>(numberSets);

        List<Integer> extendedSet = new ArrayList<>();
        extendedSet.add(missingNumber);
        extendedSets.add(extendedSet);

        for (List<Integer> numberSet: numberSets) {
            extendedSet = new ArrayList<>(numberSet);
            extendedSet.add(missingNumber);

            extendedSets.add(extendedSet);
        }

        return extendedSets;
    }
}
