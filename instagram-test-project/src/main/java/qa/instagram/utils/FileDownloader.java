package qa.instagram.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FileDownloader {
  private final static int BUFFER_SIZE = 4096;
  private final AtomicInteger fileNameCounter = new AtomicInteger(0);

  private int numberOfThreads = 5;

  public FileDownloader() {
  }

  public FileDownloader(int threadsCount) {
    this.numberOfThreads = threadsCount;
  }

  public void downloadFileList(List<String> fileUrls, String storePath) {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
    fileNameCounter.set(0);

    //create a folder if not exist
    //noinspection ResultOfMethodCallIgnored
    (new File(storePath)).mkdirs();

    for (String fileUrl : fileUrls) {
      executor.submit(() -> {
        try {
          // store to file system
          downloadFile(fileUrl, storePath);
        } catch (Exception e) {
          System.out.println("Photo download exception: " + e.getMessage());
          e.printStackTrace();
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

  private void downloadFile(String url, String storeDir) throws IOException {
    System.out.println("Downloading file " + fileNameCounter.get());
    if (url == null) {
      System.out.println("Invalid url parameter: null");
      return;
    }

    URL realUrl = new URL(url);
    HttpURLConnection httpConn = (HttpURLConnection) realUrl.openConnection();
    int responseCode = httpConn.getResponseCode();

    // always check HTTP response code first
    if (responseCode == HttpURLConnection.HTTP_OK) {
      String saveFilePath = storeDir + "/" + fileNameCounter.incrementAndGet() + ".jpg";
      storeOnFileSystem(httpConn, saveFilePath);
    }
  }

  private void storeOnFileSystem(HttpURLConnection httpConn, String filePath) throws IOException {
    // opens input stream from the HTTP connection
    try (InputStream inputStream = httpConn.getInputStream();
         FileOutputStream outputStream = new FileOutputStream(filePath)) {

      // opens an output stream to save into file
      int bytesRead;
      byte[] buffer = new byte[BUFFER_SIZE];
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }
      httpConn.disconnect();
    }
  }

  public byte[] downloadToMemory(URL toDownload) {

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      byte[] chunk = new byte[4096];
      int bytesRead;
      InputStream stream = toDownload.openStream();

      while ((bytesRead = stream.read(chunk)) > 0) {
        outputStream.write(chunk, 0, bytesRead);
      }

      return outputStream.toByteArray();

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }
}
