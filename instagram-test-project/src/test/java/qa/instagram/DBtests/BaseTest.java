package qa.instagram.DBtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.testcontainers.containers.MariaDBContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTest {
  String dbName = "InstagramDB";

  @Rule
  public MariaDBContainer mariaDB = new MariaDBContainer()
          .withDatabaseName(dbName);

  @Before
  public void beforeMethod() {
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

  @After
  public void afterMethod() {

  }
}
