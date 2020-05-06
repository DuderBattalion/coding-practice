package com.skiena;

import com.util.DirectedGraphNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GraphShortestPathWithKEdges {

  public static void main(String[] args) {
    DirectedGraphNode a = new DirectedGraphNode("a");
    DirectedGraphNode b = new DirectedGraphNode("b");
    DirectedGraphNode c = new DirectedGraphNode("c");
    DirectedGraphNode d = new DirectedGraphNode("d");
    DirectedGraphNode e = new DirectedGraphNode("e");

    a.addEdge(b, 1);
    a.addEdge(c, 1);

    b.addEdge(c, 1);
    b.addEdge(d, 2);

    c.addEdge(e, 2);

    d.addEdge(e, 2);

    int k = 3;
    PriorityQueue<PathCache> minHeap = new PriorityQueue<>();
    findShortestPath(a, e, k, 0, minHeap, "");

    PathCache result = minHeap.poll();
    if (result != null) {
      System.out.println(
          String.format("Shortest path with %d edges: %d, path: %s",
              k, result.cost, result.path));
    } else {
      System.out.println(String.format("No path found between %s and %s", a.id, e.id));
    }

  }

  private static void findShortestPath(DirectedGraphNode node, DirectedGraphNode target,
                                      int k, int cost, PriorityQueue<PathCache> minHeap,
                                       String currentPath) {
    if (node == null || target == null) {
      return;
    }

    currentPath += node.id;

    if (k == 0 && node.id.equals(target.id)) {
      minHeap.offer(new PathCache(currentPath, cost));
      return;
    }

    for (DirectedGraphNode.Edge edge: node.next) {
      DirectedGraphNode child = edge.to;
      // cost += edge.cost;
      findShortestPath(child, target, k - 1, cost + edge.cost, minHeap, currentPath);
    }
  }

  private static class PathCache implements Comparable<PathCache> {
    public String path;
    public int cost;

    public PathCache(String path, int cost) {
      this.path = path;
      this.cost = cost;
    }

    @Override
    public int compareTo(PathCache o) {
      return this.cost - o.cost;
    }
  }
}
