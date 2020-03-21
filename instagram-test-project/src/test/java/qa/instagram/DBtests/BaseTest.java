package qa.instagram.DBtests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.testcontainers.containers.MariaDBContainer;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTest {
  static String dbName = "InstagramDB";
  protected TestConfig testConfig = ConfigFactory.create(TestConfig.class);


  @ClassRule
  public static MariaDBContainer mariaDB = new MariaDBContainer()
          .withDatabaseName(dbName);

  @BeforeClass
  public static void beforeMethod() {
    Connection connection;
    Statement statement;

    String connString = mariaDB.getJdbcUrl() + "?user=" + mariaDB.getUsername() + "&password=" + mariaDB.getPassword();

    System.out.println(connString);
    try {
      connection = DriverManager.getConnection(connString);
      System.out.println("connected");
      statement = connection.createStatement();
      statement.executeQuery("CREATE TABLE photos (id INT, timestamp BIGINT, image MEDIUMBLOB)");
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @AfterClass
  public static void afterMethod() {
    WebDriverFactory.tearDown();

  }
}
