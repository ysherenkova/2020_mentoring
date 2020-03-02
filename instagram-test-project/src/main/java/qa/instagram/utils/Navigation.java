package qa.instagram.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import qa.instagram.core.WebDriverFactory;

import java.time.Duration;

public class Navigation {

  private static Integer STEP = 250;
  public static void scrollDown() {
    //chord allow me to concatanate keys (ctrl + A)
    //Keys.chord()
    JavascriptExecutor jse = (JavascriptExecutor) WebDriverFactory.getDriver();
    jse.executeScript(String.format("window.scrollBy(0,%d)", STEP += 550), "");

  }

}
