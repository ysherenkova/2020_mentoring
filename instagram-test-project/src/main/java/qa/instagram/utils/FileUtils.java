package qa.instagram.utils;

import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class FileUtils {

  private static final int BUFFER_SIZE = 4096;

  static int counter = 1;

  public static void downloadAllPhotos(Map<WebElement, String> photos, String storePath) {
    counter = 1;
    for (Map.Entry<WebElement, String> entry : photos.entrySet()) {
      try { //(и тут внутри объявить поток)
        downloadPhoto(entry.getValue(), storePath);
      } catch (Exception e) {
        System.out.println("ATTENTION! Exception was caught " + e.getMessage());
      }
    }
  }


  private static void downloadPhoto(String url, String storePath) throws IOException {
 //   System.out.println("Start download " + url);

    if(url == null){
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
      fileName = String.valueOf(counter) + ".jpg";
      counter++;

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
