package qa.instagram.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;

public class WebDriverModule extends AbstractModule {
  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);

  protected void configure() {
  }

  @Provides
  static WebDriver provideDriver() {
    WebDriverFactory.create(WebDriverFactory.BROWSER_CHROME);
    WebDriverFactory.getDriver().get(testConfig.siteUrl());
    return WebDriverFactory.getDriver();
  }
}
