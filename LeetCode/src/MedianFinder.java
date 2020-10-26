import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> lowNumQueue;
    PriorityQueue<Integer> highNumQueue;

    public MedianFinder() {
        highNumQueue = new PriorityQueue<>();
        lowNumQueue = new PriorityQueue<>(Collections.reverseOrder());
    }

    /**
     * Algorithm
     * lowNumHeap can have at most one element more than highNumHeap.
     * Add to lowNumHeap first.
     * Move top of lowNumHeap to highNumHeap.
     * If highNumHeap has more elements that lowNumHeap, then move top of highNumHeap to lowNumHeap
     */
    public void addNum(int num) {
        lowNumQueue.add(num);
        highNumQueue.add(lowNumQueue.remove());

        if (highNumQueue.size() > lowNumQueue.size()) {
            lowNumQueue.add(highNumQueue.remove());
        }
    }

    public double findMedian() {
        double median = 0;
        if (!lowNumQueue.isEmpty() && lowNumQueue.size() == highNumQueue.size()) {
            median = (lowNumQueue.peek() + highNumQueue.peek()) / 2.0;
        } else if (!lowNumQueue.isEmpty()){
            median = lowNumQueue.peek();
        }

        return median;
    }
}
