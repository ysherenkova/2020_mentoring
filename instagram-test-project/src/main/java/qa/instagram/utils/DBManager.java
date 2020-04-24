package qa.instagram.utils;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.MariaDBContainer;

public class DBManager {

  private static DBManager instance;

  private static TestConfig testConfig = ConfigFactory.create(TestConfig.class);
  private static JdbcTemplate jdbcTemplate;

  public DBManager() {
    testConfig = ConfigFactory.create(TestConfig.class);
  }

  public static DBManager getInstance() {
    if (instance == null) {
      instance = new DBManager();
    }
    return instance;
  }

  public static void initJdbcTemplate(MariaDBContainer container) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
    dataSource.setUsername(container.getUsername());
    dataSource.setPassword(container.getPassword());
    dataSource.setUrl(container.getJdbcUrl());

    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public String getDataBaseName() {
    return testConfig.databaseName();
  }

  public void executeQuery(String sqlQuery) {
    jdbcTemplate.execute(sqlQuery);
  }

  public byte[] getImageArray(String sqlQuery) {
    return jdbcTemplate.queryForObject(sqlQuery, (rs, rowNum) -> rs.getBytes(1));
  }

  public int getCountOfDBRecords(String tableName) {
    RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
    jdbcTemplate.query("SELECT * FROM " + tableName, countCallback);
    return countCallback.getRowCount();
  }

  public void dropTable(String name) {
    executeQuery("DROP TABLE IF EXISTS " + name);
  }

  public void createTable(String name) {
    executeQuery("DROP TABLE IF EXISTS " + name);
    executeQuery("CREATE TABLE " + name + " (id VARCHAR(100), timestamp LONG, image BLOB(999999))");
  }
}
