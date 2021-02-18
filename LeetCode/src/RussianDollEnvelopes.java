import java.util.Arrays;

public class RussianDollEnvelopes {
    /**
     * This is a modified LIS problem. Sort the envelopes based on width.
     * Then, find the longest increasing subsequence based on height.
     * Trick: To make this work, the sorting should break ties in height
     * in descending order, so that envelopes with same width have
     * longer heights come up first (this is to help with finding the LIS).
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 2) {
            return envelopes.length;
        }

        sortEnvelopes(envelopes);
        return getLisOnEnvelopeHeight(envelopes);
    }

    // Sort on height, break ties with height descending
    private void sortEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (int[] obj1, int[] obj2) -> {
            int width1 = obj1[0];
            int height1 = obj1[1];

            int width2 = obj2[0];
            int height2 = obj2[1];

            if (width1 == width2) {
                return -1 * (height1 - height2); // Descending on height
            }

            return width1 - width2;
        });
    }

    // Using O(n^2) algorithm to calculate LIS. Can be done in O(N log N)
    // but more complex.
    // Algo:
    // If num[i] > num[j], find max(lis[j]) + 1, j = 0 .. i-1
    // Once this is done, find max of lis array
    private int getLisOnEnvelopeHeight(int[][] envelopes) {
        int[] lis = new int[envelopes.length];
        lis[0] = 1;

        for (int i = 0; i < envelopes.length; i++) {
            int height = envelopes[i][1];

            int maxLis = 0;
            for (int j = 0; j < i; j++) {
                if (height > envelopes[j][1]) {
                    maxLis = Math.max(maxLis, lis[j]);
                }
            }

            lis[i] = maxLis + 1;
        }

        int maxLis = 0;
        for (int lisCount: lis) {
            maxLis = Math.max(maxLis, lisCount);
        }

        return maxLis;
    }
}
