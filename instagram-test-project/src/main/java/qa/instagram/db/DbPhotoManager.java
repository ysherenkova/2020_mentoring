package qa.instagram.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import qa.instagram.dataTransferObjects.InstagramImageDTO;
import qa.instagram.utils.DBManager;
import qa.instagram.utils.FileDownloader;
import qa.instagram.utils.FileUtils;
import qa.instagram.utils.HashSumCalculator;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DbPhotoManager {
  private final String photosTableName;
  protected static Logger logger = LogManager.getLogger(DbPhotoManager.class);


  public DbPhotoManager(String photosTableName) {
    this.photosTableName = photosTableName;
  }

  public synchronized void insertPhoto(InstagramImageDTO dto) {
    logger.info("jdbc=" + DBManager.getInstance().getJdbcTemplate());
    DBManager.getInstance().getJdbcTemplate().update(
            "INSERT INTO " + photosTableName + " (id, timestamp, image) VALUES (?, ?, ?)",
            dto.getId(), dto.getTimestamp(), dto.getImage());
  }

  public synchronized void insertPhotoWithShaCheck(InstagramImageDTO dto) {
    logger.info("Storing " + dto.getId() + ", size=" + dto.getImage().length);
    try {
      String hashSumOfDTO = HashSumCalculator.shaSum(dto.getImage());
      insertPhoto(dto);
      String hashSumOfBLOB =
              HashSumCalculator.shaSum(DBManager.getInstance()
                      .getImageArray("SELECT image from " + photosTableName + " WHERE id='" + dto.getId() + "'"));
      Assert.assertEquals(hashSumOfDTO, hashSumOfBLOB);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public int getNumberOfPhotos() {
    return DBManager.getInstance().getCountOfDBRecords(photosTableName);
  }

  public synchronized void storeToDb(FileDownloader downloader, List<String> fileUrls) {
    fileUrls.forEach(fileUrl -> {
      try {
        InstagramImageDTO dto = InstagramImageDTO.create(
                FileUtils.getFileName(fileUrl),
                downloader.downloadToMemory(new URL(fileUrl))
        );
        insertPhotoWithShaCheck(dto);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    });
  }
}
