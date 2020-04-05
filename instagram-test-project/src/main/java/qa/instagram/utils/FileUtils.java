package qa.instagram.utils;

import java.io.File;
import java.util.Objects;

public class FileUtils {
  public static int countFilesInDir(String dirPath) {
    return Objects.requireNonNull(new File(dirPath).list()).length;
  }

  public static String getFileName(String url) {
    int idxStart = url.lastIndexOf("/") + 1;
    int idxEnd = url.indexOf("?");
    return url.substring(idxStart, idxEnd);
  }
}
