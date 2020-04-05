package qa.instagram.utils;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import qa.instagram.dataTransferObjects.InstagramImageDTO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadUtils {

  private static final int BUFFER_SIZE = 4096;
  private static final AtomicInteger photoNameCounter = new AtomicInteger(0);
  private static final List<InstagramImageDTO> dtosToSave = new ArrayList<>();
  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);


  public static void downloadAllPhotos(Map<WebElement, String> photos, String storePath, int numberOfThreads) {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
    photoNameCounter.set(0);

    for (Map.Entry<WebElement, String> entry : photos.entrySet()) {
      executor.submit(() -> {
        try {
          int name = photoNameCounter.incrementAndGet();

          // store to file system
          downloadPhoto(entry.getValue(), storePath, String.valueOf(name));

          //store to Dto
          createDto(entry.getValue());
        } catch (Exception e) {
          System.out.println("ATTENTION! Exception was caught " + e.getMessage());
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

    try {
      storePhotosToDatabase();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }


  private static void downloadPhoto(String url, String storePath, String fname) throws IOException {
    //   System.out.println("Start download " + url);

    System.out.println("start download photo " + photoNameCounter.get());
    if (url == null) {
      System.out.println("Invalid url parameter: null");
      return;
    }

    URL realUrl = new URL(url);
    HttpURLConnection httpConn = (HttpURLConnection) realUrl.openConnection();
    int responseCode = httpConn.getResponseCode();

    // always check HTTP response code first
    if (responseCode == HttpURLConnection.HTTP_OK) {
//      String contentType = httpConn.getContentType();
//      int contentLength = httpConn.getContentLength();
// todo remove

      // extracts file name from URL
      String fileName = fname + ".jpg";

      //     System.out.println("fileName = " + fileName);
      String saveFilePath = storePath + "/" + fileName;

      // opens input stream from the HTTP connection
      try (InputStream inputStream = httpConn.getInputStream();
           FileOutputStream outputStream = new FileOutputStream(saveFilePath)) {
        //create a folder if not exist
        //noinspection ResultOfMethodCallIgnored
        (new File(storePath)).mkdirs();

        // opens an output stream to save into file

        int bytesRead;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, bytesRead);
        }
        httpConn.disconnect();
      }
    }
  }

  private static void createDto(String url) {
    try {
      byte[] downloaded = downloadUrl(new URL(url));
      InstagramImageDTO dto = new InstagramImageDTO(getFileName(url), downloaded);
      dtosToSave.add(dto);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  private static byte[] downloadUrl(URL toDownload) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try {
      byte[] chunk = new byte[4096];
      int bytesRead;
      InputStream stream = toDownload.openStream();

      while ((bytesRead = stream.read(chunk)) > 0) {
        outputStream.write(chunk, 0, bytesRead);
      }

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    return outputStream.toByteArray();
  }

  public static int getNumberOfFilesInGallery(String galleryAddress) {
    return Objects.requireNonNull(new File(galleryAddress).list()).length;
  }

//  public static void downloadAllPhotosToDB(Map<WebElement, String> photos) {
//    for (Map.Entry<WebElement, String> entry : photos.entrySet()) {
//      downloadPhotoToDB(entry.getValue());
//    }
//  }

  private static void storePhotosToDatabase() throws NoSuchAlgorithmException {
    for (InstagramImageDTO dto : dtosToSave) {
      System.out.println("Storing " + dto.getId() + ", size=" + dto.getImage().length);
      String hashSumOfDTO = HashSumCalculator.shaSum(dto.getImage());
      DBManager.getInstance().insertPhoto(dto);
      String hashSumOfBLOB =
              HashSumCalculator.shaSum(DBManager.getInstance()
                      .getImageArray("SELECT image from " + testConfig.photosTableName() + " WHERE id='" + dto.getId() + "'"));
      Assert.assertEquals(hashSumOfDTO, hashSumOfBLOB);
    }
  }

  private static String getFileName(String url) {
    int idxStart = url.lastIndexOf("/") + 1;
    int idxEnd = url.indexOf("?");
    return url.substring(idxStart, idxEnd);
  }


}