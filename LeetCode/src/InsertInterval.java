import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class InsertInterval {
    public static void main(String[] args) {
//        int[][] intervals = {
//                {1, 3},
//                {6, 9}
//        };
//
//        int[] newInterval = { 2, 5 };

//        int[][] intervals = {
//                {1, 2},
//                {3, 5},
//                {6, 7},
//                {8, 10},
//                {12, 16},
//                {18, 20}
//        };
//
//        int[] newInterval = {4, 8};

//        int[][] intervals = {{2, 3}};
//        int[] newInterval = {4, 8};

//        int[][] intervals = {{4, 8}};
//        int[] newInterval = {2, 3};

//        int[][] intervals = {{4, 8}};
//        int[] newInterval = {2, 5};

        int[][] intervals = {{3, 5}, {12, 15}};
        int[] newInterval = {6, 6};

        int[][] output = insert(intervals, newInterval);
        for (int[] interval: output) {
            System.out.printf("(%d, %d)", interval[0], interval[1]);
            System.out.println();
        }
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] output = new int[1][];
            output[0] = newInterval;

            return output;
        }

        Interval newIntervalObj = new Interval(newInterval[0], newInterval[1]);
        LinkedList<Interval> intervalList = createInputFormat(intervals);
        Deque<Interval> output = new ArrayDeque<>();

        // Special Case: Insert at head
        boolean isMergedAtHead = false;
        if (newIntervalObj.end < intervalList.get(0).start) {
            output.push(newIntervalObj);
            isMergedAtHead = true;
        }

        boolean isMergePositionFound = false;
        boolean isInserted = false;
        for (int i = 0; i < intervalList.size(); i++) {
            Interval interval = intervalList.get(i);
            if (interval.isOverlap(newIntervalObj)) {
                Interval mergedInterval = mergeInterval(interval, newIntervalObj);
//                intervalList.set(i, mergedInterval);

                if (isMergePositionFound) {
                    // Continuation of merge, pop last output node
                    // and push in new merged interval
                    output.pop();
                }

                output.push(mergedInterval);

                newIntervalObj = mergedInterval;
                isMergePositionFound = true;
            } else if (!isInserted && (i + 1) < intervalList.size()
                    && canInsert(interval, intervalList.get(i + 1), newIntervalObj)) {
                output.push(interval);
                output.push(newIntervalObj);

                isInserted = true;
            } else {
                output.push(interval);
            }
        }

        // Special case: Add to tail
        if (!isMergePositionFound && !isMergedAtHead && !isInserted) {
            output.push(newIntervalObj);
        }

        return createOutputFormat(output);
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

    private static LinkedList<Interval> createInputFormat(int[][] intervals) {
        LinkedList<Interval> intervalList = new LinkedList<>();

        for (int[] interval: intervals) {
            intervalList.add(new Interval(interval[0], interval[1]));
        }

        return intervalList;
    }

    private static Interval mergeInterval(Interval interval1, Interval interval2) {
        return new Interval(
                Math.min(interval1.start, interval2.start),
                Math.max(interval1.end, interval2.end));
    }

    private static boolean canInsert(Interval interval1, Interval interval2, Interval interval) {
        if (interval1 == null || interval2 == null || interval == null) {
            return false;
        }

        return ((interval.start > interval1.start) && (interval.end < interval2.start));
    }

    private static int[][] createOutputFormat(Deque<Interval> outputStack) {
        int[][] output = new int[outputStack.size()][2];

        int i = outputStack.size() - 1;
        while(!outputStack.isEmpty()) {
            Interval interval = outputStack.pop();
            output[i][0] = interval.start;
            output[i][1] = interval.end;

            i--;
        }

        return output;
    }

}
