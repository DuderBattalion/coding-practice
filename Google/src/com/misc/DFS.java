package com.misc;

import com.util.GraphNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {

  public static void main(String[] args) {
    GraphNode a = new GraphNode("a");
    GraphNode b = new GraphNode("b");
    GraphNode c = new GraphNode("c");
    GraphNode d = new GraphNode("d");
    GraphNode e = new GraphNode("e");

    a.next.add(b);
    a.next.add(c);

    b.next.add(c);
    b.next.add(d);

    c.next.add(b);
    c.next.add(e);

    d.next.add(b);
    d.next.add(e);

    e.next.add(c);
    e.next.add(d);

    printDfs(a, new HashSet<String>());
  }

  private static void printDfs(GraphNode node, Set<String> processedNodes) {
    if (node == null) {
      return;
    }

    System.out.print(node.id + " ");
    processedNodes.add(node.id);

    for (GraphNode neighbor: node.next) {
      if (!processedNodes.contains(neighbor.id)) {
        printDfs(neighbor, processedNodes);
      }
    }
  }
}
