package qa.instagram.DB;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import qa.instagram.dataTransferObjects.InstagramImageDTO;
import qa.instagram.db.DbPhotoManager;
import qa.instagram.pages.MyAccountPage;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;
import qa.instagram.utils.FileDownloader;
import qa.instagram.utils.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static qa.instagram.pages.SignInPage.createSignInPage;

public class SmokeTest extends BaseTest {
  @Test
  public void connectionTest() {

    SignInPage signInPage = createSignInPage();
    signInPage.setLogin(testConfig.targetAccount());
    signInPage.setPassword(testConfig.targetPassword());
    MyFeedPage myFeedPage = signInPage.clickSignInButton();
    myFeedPage.turnOffNotifications();
    MyAccountPage myAccountPage = myFeedPage.clickAccountName();

    Map<WebElement, String> allPhotos = myAccountPage.getAllPhotos();
    Assert.assertEquals(allPhotos.size(), myAccountPage.getNumberOfPost());

    List<String> fileUrls = new ArrayList<>(allPhotos.values());
    FileDownloader downloader = new FileDownloader(testConfig.photoDownloaderThreadLimit());
    DbPhotoManager dbPhotoManager = new DbPhotoManager(testConfig.photosTableName());

    storeToFs(downloader, fileUrls);
    storeToDb(downloader, dbPhotoManager, fileUrls);

    // Compare counter on the instagram page with count of the records in the DB
    Assert.assertEquals(
            myAccountPage.getNumberOfPost(),
            dbPhotoManager.getNumberOfPhotos());
  }

  private void storeToFs(FileDownloader downloader, List<String> fileUrls) {
    downloader.downloadFileList(fileUrls, (new File(testConfig.galleryAddress()).getAbsolutePath()));
  }

  private void storeToDb(FileDownloader downloader, DbPhotoManager dbPhotoManager, List<String> fileUrls) {
    fileUrls.forEach(fileUrl -> {
      try {
        InstagramImageDTO dto = InstagramImageDTO.create(
                FileUtils.getFileName(fileUrl),
                downloader.downloadToMemory(new URL(fileUrl))
        );
        dbPhotoManager.insertPhotoWithShaCheck(dto);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    });
  }
}
