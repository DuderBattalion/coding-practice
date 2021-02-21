import java.util.*;

public class OptimalAccountBalancing {
    /**
     * transactions[x][y] = z; means x gave y $z
     * Algorithm:
     * Consider this as a graph problem.
     * x -> y, with edge weight Z
     * Calculate sum of out edges, which is money lent out
     * Calculate incoming edge sum, which is money borrowed
     * Final balance = Money lent out - money borrowed
     *
     * At the end of the calculations, we have two piles:
     * - positive piles of people with positive balances
     * - Negative pile of people with negative balances
     *
     * We have to mix and match both piles to make it zero in minimum transactions.
     */
    public static int minTransfers(int[][] transactions) {
        List<Integer> balances = calculateBalances(transactions);

        BalanceSplitHelper balanceSplit = splitBalances(balances);
        LinkedList<Integer> positiveBalances = balanceSplit.positiveBalances;
        LinkedList<Integer> negativeBalances = balanceSplit.negativeBalances;

        MinTransactionTracker tracker = new MinTransactionTracker();
        matchWithMinTx(positiveBalances, negativeBalances, 0, tracker);

        return tracker.minTransactions;
    }

    private static List<Integer> calculateBalances(int[][] transactions) {
        Map<Integer, Integer> balanceMap = new HashMap<>();
        for (int i = 0; i < transactions.length; i++) {
            for (int j = 0; j < transactions[0].length; j++) {
                int amount = transactions[i][j];
                balanceMap.merge(i, amount, Integer::sum);
                balanceMap.merge(j, -amount, Integer::sum);
            }
        }

        List<Integer> balances = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: balanceMap.entrySet()) {
            balances.add(entry.getValue());
        }

        return balances;
    }

    private static class BalanceSplitHelper {
        public LinkedList<Integer> positiveBalances;
        public LinkedList<Integer> negativeBalances;

        public BalanceSplitHelper() {
            positiveBalances = new LinkedList<>();
            negativeBalances = new LinkedList<>();
        }
    }

    private static BalanceSplitHelper splitBalances(List<Integer> balances) {
        BalanceSplitHelper splitHelper = new BalanceSplitHelper();
        for (int balance: balances) {
            if (balance > 0) {
                splitHelper.positiveBalances.add(balance);
            } else if (balance < 0) {
                splitHelper.negativeBalances.add(balance);
            }

            // Note: we skipped zero balances
        }

        return splitHelper;
    }

    /**
     * Runs DFS to find minimum transactions needed to get to total zero balance.
     */
    private static void matchWithMinTx(LinkedList<Integer> positiveBalances,
                                       LinkedList<Integer> negativeBalances,
                                       int count, MinTransactionTracker minTxs) {
        if ((positiveBalances.size() == 0 && negativeBalances.size() != 0)
                || (negativeBalances.size() == 0 && positiveBalances.size() != 0)) {
            throw new RuntimeException("[System Error]: This shouldn't happen.");
        }

        if (count > minTxs.minTransactions) {
            // Prune - no point searching further
            return;
        }

        if (positiveBalances.size() == 0 && negativeBalances.size() == 0) {
            if (count < minTxs.minTransactions) {
                minTxs.minTransactions = count;
            }

            return;
        }

        // Try to match positive balance with all negative balances
        int positiveBalance = positiveBalances.get(0);

        ListIterator<Integer> negativeBalancesIter = negativeBalances.listIterator();
        while (negativeBalancesIter.hasNext()){
            int negativeBalance = negativeBalancesIter.next();

            if (positiveBalance > Math.abs(negativeBalance)) {
                // Case 1: Reduce positive balance by negative balance, and null out negative balance
                int newPositiveBalance = positiveBalance + negativeBalance;

                negativeBalancesIter.remove();
                positiveBalances.set(0, newPositiveBalance);
                matchWithMinTx(positiveBalances, negativeBalances,
                        count + 1, minTxs);

                // Backtrack
                positiveBalances.set(0, positiveBalance);
            } else if (positiveBalance < Math.abs(negativeBalance)) {
                // Case 2: Reduce negative balance by positive balance
                // Null out positive balance
                int newNegativeBalance = negativeBalance + positiveBalance;

//                positiveBalances.remove(0);
                positiveBalances.removeFirst();
                negativeBalancesIter.set(newNegativeBalance);
                matchWithMinTx(positiveBalances, negativeBalances, count + 1, minTxs);

                // Backtrack
//                positiveBalances.set(0, positiveBalance);
                positiveBalances.addFirst(positiveBalance);
                negativeBalancesIter.set(negativeBalance);
            } else {
                // Case 3: Positive and negative balances cancel each other out
//                positiveBalances.remove(0);
                positiveBalances.removeFirst();
                negativeBalancesIter.remove();
                matchWithMinTx(positiveBalances, negativeBalances, count + 1, minTxs);

                // Backtrack
//                positiveBalances.set(0, positiveBalance);
                positiveBalances.addFirst(positiveBalance);
                negativeBalancesIter.add(negativeBalance);
            }
        }
    }

    private static class MinTransactionTracker {
        int minTransactions;

        public MinTransactionTracker() {
            minTransactions = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        int[][] transactions = {{0,1,10}, {2,0,5}};
        System.out.println(minTransfers(transactions));
    }
}
