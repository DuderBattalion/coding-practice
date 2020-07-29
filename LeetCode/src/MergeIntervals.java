import java.util.*;

public class MergeIntervals {
    public static void main(String[] args) {
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{1, 4}, {4, 5}};
//        int[][] intervals = {{2, 6}, {8, 10}, {1, 3}, {9, 12}};
//        int[][] intervals = {};
        int[][] intervals = { {1, 4}, {2, 3}};

        int[][] mergedIntervals = merge(intervals);

        for (int[] mergedInterval: mergedIntervals) {
            System.out.printf("(%d, %d)", mergedInterval[0], mergedInterval[1]);
            System.out.println();
        }
    }

    /**
     * Algorithm: Modified topological sort
     * Sort the input array by start time using a priority queue.
     * Create a stack for the output list. We need to keep peeking at the last element inserted - hence stack.
     *
     * Implement a data structure 'Interval' to help with sort and overlap check operations.
     *
     * Run through the priority queue. If there is an overlap, merge and add to output stack. If not, add interval
     * to output stack.
     *
     * Once done, we would need to transform output stack into the integer array before returning.
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        PriorityQueue<Interval> inputQueue = createInputFormat(intervals);
        Deque<Interval> mergedIntervals = new ArrayDeque<>();
        mergedIntervals.push(inputQueue.remove());

        while (!inputQueue.isEmpty()) {
            Interval interval = inputQueue.remove();
            if (interval.isOverlap(mergedIntervals.peek())) {
                Interval oldMergeInterval = mergedIntervals.pop();
                Interval newMergeInterval = mergeInterval(oldMergeInterval, interval);
                mergedIntervals.push(newMergeInterval);
            } else {
                mergedIntervals.push(interval);
            }
        }

        return createOutputFormat(mergedIntervals);
    }

    private static class Interval implements Comparable<Interval> {
        public int start;
        public int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * Intervals don't overlap if:
         * a) Interval A ends before Interval B starts
         * b) Interval B ends before Interval A starts
         * Return the negation of this condition
         */
        public boolean isOverlap(Interval interval) {
            if (interval == null) {
                return false;
            }

            return !((this.end < interval.start) || (interval.end < this.start));
        }

        @Override
        public int compareTo(Interval interval) {
            return (this.start - interval.start);
        }
    }

    private static PriorityQueue<Interval> createInputFormat(int[][] intervals) {
        PriorityQueue<Interval> queue = new PriorityQueue<>();

        for (int[] interval: intervals) {
            queue.add(new Interval(interval[0], interval[1]));
        }

        return queue;
    }

    private static int[][] createOutputFormat(Deque<Interval> intervals) {
        int[][] output = new int[intervals.size()][2];

        // Output deque is in a stack - so fill out array in reverse order
        for (int i = intervals.size() - 1; i >= 0; i--) {
            Interval interval = intervals.pop();
            output[i][0] = interval.start;
            output[i][1] = interval.end;
        }

        return output;
    }

    private static Interval mergeInterval(Interval interval1, Interval interval2) {
        return new Interval(
                Math.min(interval1.start, interval2.start),
                Math.max(interval1.end, interval2.end));
    }
}
