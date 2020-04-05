package qa.instagram.utils;

import org.openqa.selenium.JavascriptExecutor;
import qa.instagram.core.WebDriverFactory;

public class Navigation {

  private static Integer STEP = 250;

  public static void scrollDown() {
    //chord allow me to concatanate keys (ctrl + A)
    //Keys.chord()
    JavascriptExecutor jse = (JavascriptExecutor) WebDriverFactory.getDriver();
    jse.executeScript(String.format("window.scrollBy(0,%d)", STEP += 550), "");
  }
}
