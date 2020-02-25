package qa.instagram.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qa.instagram.core.WebDriverFactory;

public class MyFeedPage {
  @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/section/div[3]/div[1]/div/div[2]/div[1]/a")
  WebElement accountName;

  public MyAccountPage clickAccountName() {
    this.accountName.click();
    return PageFactory.initElements(WebDriverFactory.getDriver(), MyAccountPage.class);
  }


}