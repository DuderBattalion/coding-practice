package com.misc;

import com.util.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

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

    printBfs(a, new HashSet<>());
  }

  private static void printBfs(GraphNode node, Set<String> processedNodes) {
    if (node == null) {
      return;
    }

    System.out.print(node.id + " ");
    processedNodes.add(node.id);

    Queue<GraphNode> children = new LinkedList<>();
    children.addAll(node.next);

    GraphNode child;
    while(!children.isEmpty()) {
      child = children.poll();
      if (!processedNodes.contains(child.id)) {
        System.out.print(child.id + " ");
        processedNodes.add(child.id);
        children.addAll(child.next);
      }
    }
  }
}
