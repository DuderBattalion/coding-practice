import java.util.TreeSet;

public class DataStreamDisjointIntervals {
    TreeSet<Interval> intervals;

    public DataStreamDisjointIntervals() {
        this.intervals = new TreeSet<>();
    }

    public void addNum(int val) {
        Interval interval = new Interval(val, val);

        // Case 1: NUmber already exists
        if (intervals.contains(interval)) {
            return;
        }

        Interval lowerInterval = intervals.lower(interval);
        Interval higherInterval = intervals.higher(interval);

        if (lowerInterval != null && higherInterval != null) {
            int lowerDiff = val - lowerInterval.end;
            int higherDiff = higherInterval.start - val;

            if (lowerDiff == 1 && higherDiff == 1) {
                mergeLowerAndHigher(lowerInterval, higherInterval);
            } else if (lowerDiff == 1) {
                mergeLowerInterval(val, lowerInterval);
            } else if (higherDiff == 1){ // Higher diff == 1
                mergeHigherInterval(val, higherInterval);
            } else  {
                // Case 3: No merge. Just add interval
                intervals.add(interval);
            }
        } else if (lowerInterval != null) {
            int lowerDiff = val - lowerInterval.end;

            if (lowerDiff == 1) {
                mergeLowerInterval(val, lowerInterval);
            } else {
                intervals.add(interval);
            }
        } else if (higherInterval != null) {
            int higherDiff = higherInterval.start - val;

            if (higherDiff == 1) {
                mergeHigherInterval(val, higherInterval);
            } else {
                intervals.add(interval);
            }
        } else {
            intervals.add(interval);
        }
    }

    private void mergeLowerAndHigher(Interval lowerInterval, Interval higherInterval) {
        Interval mergedInterval = new Interval(lowerInterval.start, higherInterval.end);

        intervals.remove(lowerInterval);
        intervals.remove(higherInterval);
        intervals.add(mergedInterval);
    }

    private void mergeLowerInterval(int val, Interval lowerInterval) {
        Interval mergedInterval = new Interval(lowerInterval.start, val);

        intervals.remove(lowerInterval);
        intervals.add(mergedInterval);
    }

    private void mergeHigherInterval(int val, Interval higherInterval) {
        Interval mergedInterval = new Interval(val, higherInterval.end);

        intervals.remove(higherInterval);
        intervals.add(mergedInterval);
    }

    public int[][] getIntervals() {
        if (intervals.isEmpty()) {
            return null;
        }

        int[][] intervalArray = new int[intervals.size()][2];

        int i = 0;
        for (Interval interval: intervals) {
            intervalArray[i][0] = interval.start;
            intervalArray[i][1] = interval.end;

            i++;
        }

        return intervalArray;
    }
}

class Interval implements Comparable<Interval> {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Interval o) {
        return this.start - o.start;
    }
}
