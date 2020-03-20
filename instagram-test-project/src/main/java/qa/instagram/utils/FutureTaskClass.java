package qa.instagram.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureTaskClass implements Runnable {
  private String url;
  private String storePath;
  AtomicInteger counter = new AtomicInteger(0);


  @Override
  public void run() {

  }

  public FutureTaskClass (String url, String storePath) {
    this.url = url;
    this.storePath = storePath;
  }
}
