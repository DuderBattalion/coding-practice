import java.util.LinkedList;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {6, 9}
        };

        int[] newInterval = { 2, 5 };

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

        boolean isMergePositionFound = false;
        for (int i = 0; i < intervalList.size(); i++) {
            Interval interval = intervalList.get(i);
            if (interval.isOverlap(newIntervalObj)) {
                Interval mergedInterval = mergeInterval(interval, newIntervalObj);
                intervalList.set(i, mergedInterval);

                newIntervalObj = mergedInterval;
                isMergePositionFound = true;
            } else if (isMergePositionFound) {
                // If merge position was previously found, and we run out of overlap
                // then we are done. Break out of loop.
                break;
            }
        }

        return createOutputFormat(intervalList);
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

    private static int[][] createOutputFormat(LinkedList<Interval> intervalList) {
        int[][] output = new int[intervalList.size()][2];

        for (int i = 0; i < intervalList.size(); i++) {
            Interval interval = intervalList.get(i);
            output[i][0] = interval.start;;
            output[i][1] = interval.end;;
        }

        return output;
    }

}
