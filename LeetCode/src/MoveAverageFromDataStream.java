import java.util.ArrayDeque;
import java.util.Deque;

public class MoveAverageFromDataStream {
    Deque<Integer> dequeue;
    double rollingSum;
    int queueSize;

    /** Initialize your data structure here. */
    public MoveAverageFromDataStream(int size) {
        queueSize = size;
        dequeue = new ArrayDeque<>(size);
        rollingSum = 0;
    }

    public double next(int val) {
        dequeue.addLast(val);
        rollingSum += val;

        if (dequeue.size() <= queueSize) {
            return rollingSum / dequeue.size();
        }

        rollingSum = rollingSum - dequeue.removeFirst();
        return rollingSum / queueSize;
    }
}
