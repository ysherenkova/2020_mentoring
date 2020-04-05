package qa.instagram.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.Navigation;

import java.util.HashMap;
import java.util.Map;

public class MyAccountPage {

  @SuppressWarnings("unused")
  @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[1]/span/span")
  WebElement numberOfPosts;


  public int getNumberOfPost () {
    return Integer.parseInt(numberOfPosts.getText());
  }

  public Map<WebElement, String> getAllPhotos () {
    Map<WebElement, String> elements = new HashMap<>();
    int number = getNumberOfPost();
    while (elements.size() < number) {
      WebDriverFactory.getDriver().findElements(By.xpath("//div[@class = 'KL4Bh']/img"))
              .forEach(webElement -> {
                if (!elements.containsKey(webElement)) {
                  elements.put(webElement, webElement.getAttribute("src"));
                }
              });
      Navigation.scrollDown();
    }
  return elements;
}


}
