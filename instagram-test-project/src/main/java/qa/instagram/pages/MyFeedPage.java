package qa.instagram.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.Navigation;
import qa.instagram.utils.WaitUtils;

public class MyFeedPage {
  @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/section/div[3]/div[1]/div/div[2]/div[1]/a")
  WebElement accountName;

  @FindBy(xpath = "/html/body/div[4]/div/div/div[3]/button[2]")
  WebElement notNowOnPopUp;

  public MyAccountPage clickAccountName() {
    WaitUtils.waitTillAvailable(accountName).click();
    return PageFactory.initElements(WebDriverFactory.getDriver(), MyAccountPage.class);
  }

  public void turnOffNotifications() {
    WaitUtils.waitTillAvailable(notNowOnPopUp);
    notNowOnPopUp.click();
  }

}