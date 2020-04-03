package com.chapter15.threads.asyncfizzbuzz;

import com.util.HelperMethods;

import java.util.concurrent.Callable;

public class DivideByThreeChecker implements Callable {
  int num;

  public DivideByThreeChecker(int num) {
    this.num = num;
  }

  @Override
  public String call() throws Exception {
    String result = "";
    if (num % 3 == 0) {
      result = "Fizz";
    }

    return result;
  }
}
