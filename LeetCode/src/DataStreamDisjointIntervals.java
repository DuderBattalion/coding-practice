import java.util.TreeSet;

public class DataStreamDisjointIntervals {
    TreeSet<Interval> intervals;

    public DataStreamDisjointIntervals() {
        this.intervals = new TreeSet<>();
    }

    public void addNum(int val) {
        Interval interval = new Interval(val, val);

        Interval floor = intervals.floor(interval);
        Interval ceiling = intervals.ceiling(interval);

        if (isSubset(interval, floor) || isSubset(interval, ceiling)) {
            return;
        }

        boolean floorMergeable = isFloorMergeable(interval, floor);
        boolean ceilingMergeable = isCeilingMergeable(interval, ceiling);

        // Cases
        // 0) No ceiling or floor, add interval
        // 4) Interval subset of floor or ceiling - do nothing
        // 1) Interval partially intersects floor and ceiling - merge floor and ceiling
        // 2) Interval intersects with floor but not ceiling - merge floor and interval
        // 3) Interval intersects with ceiling but not floor - merge interval and ceiling
        // 5) Interval is disjoint - add interval
        if ((floor == null && ceiling == null) || isDisjoint(interval, floor, ceiling)) {
            intervals.add(interval);
        } else if (floorMergeable && ceilingMergeable) {
            Interval newInterval = new Interval(floor.start, ceiling.end);

            intervals.remove(floor);
            intervals.remove(ceiling);
            intervals.add(newInterval);
        } else if (floorMergeable) {
            Interval newInterval = new Interval(floor.start, interval.end);

            intervals.remove(floor);
            intervals.add(newInterval);
        } else if (ceilingMergeable) {
            Interval newInterval = new Interval(interval.start, ceiling.end);

            intervals.remove(ceiling);
            intervals.add(newInterval);
        } else {
            System.out.println("ERROR: This shouldn't happen.");
        }
    }

    private boolean isSubset(Interval interval, Interval parentInterval) {
        if (parentInterval == null) {
            return false;
        }

        if (interval == null) {
            return true;
        }

        return (parentInterval.start <= interval.start && parentInterval.end >= interval.end);
    }

    private boolean isDisjoint(Interval interval, Interval floor, Interval ceiling) {
        if (interval == null) {
            return false;
        }

        boolean disjoint;
        if (floor == null && ceiling == null) {
            disjoint = true;
        } else if (floor != null && ceiling != null) {
            disjoint = (floor.end < (interval.start - 1) && ceiling.start > (interval.end + 1));
        } else if (floor != null) {
            disjoint = floor.end < (interval.start - 1);
        } else {
            disjoint = ceiling.start > (interval.end + 1);
        }

        return disjoint;
    }

    private boolean isFloorMergeable(Interval interval, Interval floor) {
        if (interval == null || floor == null) {
            return false;
        }

        return (floor.end >= (interval.start - 1));
    }

    private boolean isCeilingMergeable(Interval interval, Interval ceiling) {
        if (interval == null || ceiling == null) {
            return false;
        }

        return (ceiling.start <= (interval.start + 1));
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
