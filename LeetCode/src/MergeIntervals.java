import java.util.*;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedIntervals = merge(intervals);

        for (int[] mergedInterval: mergedIntervals) {
            System.out.printf("(%d, %d)", mergedInterval[0], mergedInterval[1]);
            System.out.println();
        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        PriorityQueue<Interval> inputQueue = createInputFormat(intervals);

        Deque<Interval> mergedIntervals = new LinkedList<>();
        mergedIntervals.push(inputQueue.remove());

        int i = 1;
        while (i < intervals.length) {
            Interval interval = inputQueue.remove();
            if (interval.isOverlap(mergedIntervals.peek())) {
                Interval oldMergeInterval = mergedIntervals.pop();
                Interval newMergeInterval = mergeInterval(oldMergeInterval, interval);
                mergedIntervals.push(newMergeInterval);
            } else {
                mergedIntervals.push(interval);
            }

            i++;
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

        public boolean isOverlap(Interval interval) {
            if (interval == null) {
                return false;
            }

            return (this.start <= interval.start && this.end >= interval.start)
                    || (this.start <= interval.end && this.end >= interval.end);
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
        for (int i = 0; i < intervals.size(); i++) {
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
