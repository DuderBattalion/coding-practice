import java.util.Arrays;
import java.util.List;

public class EditDistance {
    public static void main(String[] args) {
//        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));
    }

    public static int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        initDp(dp);

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertCost = dp[i][j - 1];
                    int deleteCost = dp[i - 1][j];
                    int replaceCost = dp[i - 1][j - 1];

                    int minCost = findMin(insertCost, deleteCost, replaceCost);
                    dp[i][j] = 1 + minCost;
                }
            }
        }

//        debugDp(dp);
        return dp[dp.length - 1][dp[0].length - 1];
    }

    private static void initDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
    }

    private static void debugDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static int findMin(int insertCost, int deleteCost, int replaceCost) {
        int[] costArr = new int[3];
        costArr[0] = insertCost;
        costArr[1] = deleteCost;
        costArr[2] = replaceCost;

        Arrays.sort(costArr);
        return costArr[0];
    }


}
