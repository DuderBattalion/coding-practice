package com.util;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
  public List<GraphNode> next;
  public int val;
  public String id;

  public GraphNode(String id) {
    next = new ArrayList<>();
    this.id = id;
    this.val = 0;
  }
}
