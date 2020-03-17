package qa.instagram.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:TestConfig.properties"})
public interface TestConfig extends Config {
  @Key("site.url")
  String siteUrl();

  @Key("target.account")
  String targetAccount();

  @Key("target.password")
  String targetPassword();

  @Key("photogallery.address")
  String galleryAddress();

  @Key("numberOfThreads")
  int numberOfThreads();
}
