package qa.instagram.db;

import org.junit.Assert;
import qa.instagram.dataTransferObjects.InstagramImageDTO;
import qa.instagram.utils.DBManager;
import qa.instagram.utils.HashSumCalculator;

import java.security.NoSuchAlgorithmException;

public class DbPhotoManager {
  private String photosTableName;

  public DbPhotoManager(String photosTableName) {
    this.photosTableName = photosTableName;
  }

  public void insertPhoto(InstagramImageDTO dto) {
    System.out.println("jdbc=" + DBManager.getInstance().getJdbcTemplate());
    DBManager.getInstance().getJdbcTemplate().update(
            "INSERT INTO " + photosTableName + " (id, timestamp, image) VALUES (?, ?, ?)",
            dto.getId(), dto.getTimestamp(), dto.getImage());
  }

  public void insertPhotoWithShaCheck(InstagramImageDTO dto) {
    System.out.println("Storing " + dto.getId() + ", size=" + dto.getImage().length);
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
}
