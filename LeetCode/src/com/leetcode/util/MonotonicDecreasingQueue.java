package com.leetcode.util;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotonicDecreasingQueue<T extends Comparable<T>> {
    Deque<T> queue;

    public MonotonicDecreasingQueue() {
        this.queue = new ArrayDeque<>();
    }

    public void push(T t) {
        while (!queue.isEmpty() && queue.peekLast().compareTo(t) < 0) {
            queue.pollLast();
        }

        queue.offerLast(t);
    }

    public T peek() {
        return queue.peekFirst();
    }

    public T pop(T t) {
        T result = null;
        if (t.equals(queue.peekFirst())) {
            result = queue.pollFirst();
        }

        return result;
    }
}
