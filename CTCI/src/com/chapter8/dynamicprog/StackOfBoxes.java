package com.chapter8.dynamicprog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StackOfBoxes {

  public static void main(String[] args) {
    Box box1 = new Box(1, 1, 1);
    Box box2 = new Box(2, 2, 2);
    Box box3 = new Box(3, 3, 3);
    Box box4 = new Box(4, 4, 4);

    List<Box> boxes = new ArrayList<>();
    boxes.add(box1);
    boxes.add(box2);
    boxes.add(box3);
    boxes.add(box4);

    int[] cache = new int[boxes.size()];
    int tallestStack = calcTallestStack(boxes, cache, );
  }

  private static class Box {
    int w;
    int d;
    int h;

    public Box(int w, int d, int h) {
      this.w = w;
      this.d = d;
      this.h = h;
    }
  }

  /**
   * DP sub-problem:
   * If (i - 1) stacks have optimal solution, then for ith stack, we can choose it or not
   * if current box is smaller than the min w, d and h in the optimal solution.
   */
  private static int calcTallestStack(Queue<Box> boxes, int[] cache, Box minBox, int i) {
    if (boxes.isEmpty()) {
      return 0;
    }

    Box box = boxes.poll();

    // If box is larger than any of the boxes in the stack
    // Then exclude box. Cache is height from (i - 1)th stack.
    if (box.w > minBox.w || box.d > minBox.d || box.h > minBox.h) {
      cache[i] = cache[i - 1];
    } else {
      // Take max of adding box, or excluding it
      // 1) If add box, then stack height is incread,minBox parameters are updated.
      // This will affect choices downstream
      // 2) If box not added, minbox remains the same, but stack height is not increased
      Box updatedMinBox = updateMinBox(box, minBox);
      cache[i] = Math.max(
          box.h + calcTallestStack(new LinkedList<>(boxes), cache, updatedMinBox, i - 1),
          calcTallestStack(new LinkedList<>(boxes), cache, minBox, i - 1));
    }

    return cache[i];
  }

  private static Box updateMinBox(Box box, Box minBox) {
    int minW = Math.max(box.w, minBox.w);
    int minD = Math.max(box.d, minBox.d);
    int minH = Math.max(box.h, minBox.h);

    return new Box(minW, minD, minH);
  }
}
