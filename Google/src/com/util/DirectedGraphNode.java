package com.util;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphNode {
  public List<Edge> next;
  public String val;
  public String id;

  public DirectedGraphNode(String id) {
    next = new ArrayList<>();
    this.id = id;
    this.val = "";
  }

  public void addEdge(DirectedGraphNode to, int cost) {
    next.add(new Edge(this, to, cost));
  }

  public class Edge {
    public DirectedGraphNode from;
    public DirectedGraphNode to;
    public int cost;

    public Edge(DirectedGraphNode from, DirectedGraphNode to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }
}
