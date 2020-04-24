package qa.instagram.UI;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import qa.instagram.core.Credentials;
import qa.instagram.core.CredentialsDataProvider;
import qa.instagram.core.Retry;
import qa.instagram.pages.MyAccountPage;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;
import qa.instagram.utils.FileDownloader;
import qa.instagram.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static qa.instagram.pages.SignInPage.createSignInPage;

@Test
@Listeners(ReportPortalTestNGListener.class)
public class SmokeTest extends BaseTest {


  @Test(groups = {"UI", "Smoke"}, retryAnalyzer = Retry.class, dataProvider = "credentials-data-provider", dataProviderClass = CredentialsDataProvider.class)
  public void loginTest(Object data) {
    Credentials credentials = (Credentials) data;
    logger.info("Started UI Smoke test");
    SignInPage signInPage = createSignInPage();
    System.out.println(credentials.getLogin());
    System.out.println(credentials.getPassword());
    signInPage.setLogin(credentials.getLogin());
    signInPage.setPassword(credentials.getPassword());
    MyFeedPage myFeedPage = signInPage.clickSignInButton();
    myFeedPage.turnOffNotifications();
    MyAccountPage myAccountPage = myFeedPage.clickAccountName();

    Map<WebElement, String> allPhotos = myAccountPage.getAllPhotos();
    Assert.assertEquals(allPhotos.size(), myAccountPage.getNumberOfPost());

    logger.info("Started downloading photos");
    List<String> fileUrls = new ArrayList<>(allPhotos.values());
    FileDownloader downloader = new FileDownloader(testConfig.photoDownloaderThreadLimit());

    logger.info("Started storing photos");
    FileUtils.storeToFs(downloader, fileUrls, (new File(testConfig.galleryAddress()).getAbsolutePath()));
 //   Assert.assertEquals(allPhotos.size(), FileUtils.countFilesInDir(new File(testConfig.galleryAddress()).getAbsolutePath()));
  }
}
