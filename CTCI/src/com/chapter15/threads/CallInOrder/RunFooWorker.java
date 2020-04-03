package com.chapter15.threads.CallInOrder;

public class RunFooWorker implements Runnable {
  private String method;
  private Foo foo;

  public RunFooWorker(String method, Foo foo) {
    this.method = method;
    this.foo = foo;
  }

  @Override
  public void run() {
    switch (method) {
      case "first":
        foo.first();
        break;
      case "second":
        foo.second();
        break;
      case "third":
        foo.third();
        break;
      default:
        throw new RuntimeException("Invalid method name.");
    }

  }
}