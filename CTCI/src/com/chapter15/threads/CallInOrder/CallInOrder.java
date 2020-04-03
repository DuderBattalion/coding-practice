package com.chapter15.threads.CallInOrder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallInOrder {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(5);

    Foo foo = new Foo();
    RunFooWorker worker1 = new RunFooWorker("first", foo);
    RunFooWorker worker2 = new RunFooWorker("second", foo);
    RunFooWorker worker3 = new RunFooWorker("third", foo);

    // List<Runnable> tasks = new ArrayList<>();
    // tasks.add(worker3);
    // tasks.add(worker2);
    // tasks.add(worker1);

    executor.execute(worker2);
    executor.execute(worker3);
    executor.execute(worker1);

    executor.shutdown();
  }
}
