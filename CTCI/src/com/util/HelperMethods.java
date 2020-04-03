package com.util;

import java.util.ArrayList;

public class HelperMethods {
  public static void print(String msg) {
    System.out.println(msg);
  }

  public static void printNoLineBreak(String msg) {
    System.out.print(msg);
  }

  public static<T> void printArrayList(ArrayList<T> list) {
    for (T member: list) {
      print(member.toString());
    }
  }
}
