package com.chapter15.threads.asyncfizzbuzz;

import java.util.concurrent.Callable;

public class DivideByFiveChecker implements Callable {
  int num;

  public DivideByFiveChecker(int num) {
    this.num = num;
  }

  @Override
  public String call() throws Exception {
    String result = "";
    if (num % 5 == 0) {
      result = "Buzz";
    }

    return result;
  }
}
