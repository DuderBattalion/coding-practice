package com.chapter15.threads.CallInOrder;

import com.util.HelperMethods;

public class Foo {
  boolean isFirstDone;
  boolean isSecondDone;

  public Foo() {
    isFirstDone = false;
    isSecondDone = false;
  }

  public synchronized void first() {
    HelperMethods.print("Executing first method.");
    isFirstDone = true;

    notifyAll();
  }

  public synchronized void second() {
    try {
      while (!isFirstDone) {
        wait();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    HelperMethods.print("Executing second method.");
    isSecondDone = true;

    notifyAll();
  }

  public synchronized void third() {
    try {
      while (!isSecondDone) {
        wait();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    HelperMethods.print("Executing third method.");
  }
}