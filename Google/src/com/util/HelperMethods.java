package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelperMethods {

  public static List<String> readFile(String path) {
    List<String> lines = new ArrayList<>();
    try(Stream<String> stream = Files.lines(Paths.get(path))) {
      stream.forEach(ln -> {
        lines.add(ln);
      });
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("[HelperMethod]: Could not read file.");
    }

    return lines;
  }

  public static void println(String msg) {
    System.out.println(msg);
  }

  public static void print(String msg) {
    System.out.print(msg);
  }

  public static<T> void printList(List<T> someList) {
    for (T member: someList) {
      println(member.toString());
    }
  }

  public static<T> void printCollection(Collection<T> coll) {
    for (T elem: coll) {
      println(elem.toString());
    }

    println("");
  }

  public static<T> void print2DArray(T[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        print(arr[i][j].toString() + " ");
      }

      println("");
    }
  }
}
