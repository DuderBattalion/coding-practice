package com.chapter15.threads.asyncfizzbuzz;

import com.util.HelperMethods;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NumberGenerator implements Runnable {
  int maxNum;
  ExecutorService executorService;

  public NumberGenerator(int num) {
    maxNum = num;
    executorService = Executors.newFixedThreadPool(4);
  }

  @Override
  public void run() {
    try {
      StringBuilder result = new StringBuilder();
      for (int i = 1; i <= maxNum; i++) {
        Future<String> fizzFtr = executorService.submit(new DivideByThreeChecker(i));
        Future<String> buzzFtr = executorService.submit(new DivideByFiveChecker(i));

        String fizz = fizzFtr.get();
        String buzz = buzzFtr.get();

        if (fizz.isEmpty() && buzz.isEmpty()) {
          result.append(i);
        } else {
          result.append(fizz);
          result.append(buzz);
        }

        result.append(", ");
      }

      HelperMethods.print(result.toString());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    } finally {
      executorService.shutdown();
    }



  }
}
