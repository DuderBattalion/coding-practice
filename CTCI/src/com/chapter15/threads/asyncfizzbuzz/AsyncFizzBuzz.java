package com.chapter15.threads.asyncfizzbuzz;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncFizzBuzz {

  public static void main(String[] args) {
    int N = 20;

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(new NumberGenerator(N));

    executorService.shutdown();
  }
}
