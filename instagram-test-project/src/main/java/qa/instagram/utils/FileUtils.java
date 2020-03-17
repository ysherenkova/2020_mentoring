package qa.instagram.utils;

import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FileUtils {

  private static final int BUFFER_SIZE = 4096;

  static AtomicInteger counter = new AtomicInteger(1);

  public static void downloadAllPhotos(Map<WebElement, String> photos, String storePath, int numberOfThreads) {
    counter.set(0);
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);

    for (Map.Entry<WebElement, String> entry : photos.entrySet()) {
      executor.submit(new Runnable() {
        @Override
        public void run() {
          try {
            int name = counter.incrementAndGet();

            downloadPhoto(entry.getValue(), storePath, String.valueOf(name));
          } catch (Exception e) {
            System.out.println("ATTENTION! Exception was caught " + e.getMessage());
            e.printStackTrace();
          }
        }
      });
    }
    executor.shutdown();
    try {
      executor.awaitTermination(600, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  private static void downloadPhoto(String url, String storePath, String fname) throws IOException {
    //   System.out.println("Start download " + url);

    System.out.println("start download photo " +counter.get());
    if (url == null) {
      System.out.println("Invalid url parameter: null");
    }

    URL realUrl = new URL(url);
    HttpURLConnection httpConn = (HttpURLConnection) realUrl.openConnection();
    int responseCode = httpConn.getResponseCode();

    // always check HTTP response code first
    if (responseCode == HttpURLConnection.HTTP_OK) {
      String fileName = "";
      String contentType = httpConn.getContentType();
      int contentLength = httpConn.getContentLength();

      // extracts file name from URL
      fileName = fname + ".jpg";

      //     System.out.println("fileName = " + fileName);

      // opens input stream from the HTTP connection
      InputStream inputStream = httpConn.getInputStream();
      String saveFilePath = storePath + "/" + fileName;

      //create a folder if not exist
      (new File(storePath)).mkdirs();

      // opens an output stream to save into file
      FileOutputStream outputStream = new FileOutputStream(saveFilePath);

      int bytesRead = -1;
      byte[] buffer = new byte[BUFFER_SIZE];
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }

      outputStream.close();
      inputStream.close();
      httpConn.disconnect();
    }
  }

  public static int getNumberOfFilesInGallery(String galleryAddress) {
    File files = new File(galleryAddress);
    return files.list().length;
  }
}
