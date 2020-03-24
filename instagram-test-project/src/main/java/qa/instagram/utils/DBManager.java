package qa.instagram.utils;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.MariaDBContainer;
import qa.instagram.dataTransferObjects.InstagramImageDTO;

public class DBManager {

  private static DBManager instance;

  private static TestConfig testConfig = ConfigFactory.create(TestConfig.class);
  private static DriverManagerDataSource dataSource;
  private static JdbcTemplate jdbcTemplate;
  private static MariaDBContainer mariaDBContainer;

  public String getDataBaseName() {
    return testConfig.databaseName();
  }

  private DBManager() {
    testConfig = ConfigFactory.create(TestConfig.class);
  }

  public static void initDb(MariaDBContainer container) {
    mariaDBContainer = container;
    //setup container
    //   String connString = mariaDBContainer.getJdbcUrl()
    //           + "?user=" + mariaDBContainer.getUsername()
    //           + "&password="
    //           + mariaDBContainer.getPassword();

    //setup datasource
    dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
    dataSource.setUsername(mariaDBContainer.getUsername());
    dataSource.setPassword(mariaDBContainer.getPassword());
    dataSource.setUrl(mariaDBContainer.getJdbcUrl());

    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public static DBManager getInstance() {
    if (instance == null) {
      instance = new DBManager();
    }
    return instance;
  }

  public static DriverManagerDataSource getDataSource() {
    return dataSource;
  }


  public void executeQuery(String sqlQuery) {
    jdbcTemplate.execute(sqlQuery);
  }

  public byte[] getImageArray(String sqlQuery) {
    byte[] resultfromDB = jdbcTemplate.queryForObject(sqlQuery, (rs, rowNum) -> rs.getBytes(1));
    return resultfromDB;
  }

  public int getCountOfDBRecords() {
    RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
    jdbcTemplate.query("select * from " + testConfig.tableName(), countCallback);
    int rowCount = countCallback.getRowCount();
    return rowCount;
  }

  public void createTable(String name) {
    executeQuery("DROP TABLE IF EXISTS " + name);
    executeQuery("create table " + name + " (id VARCHAR(100), timestamp LONG, image BLOB(999999))");
  }

  public void insertDtoToDb(InstagramImageDTO dto) {
    System.out.println("jdbc=" + jdbcTemplate);
    jdbcTemplate.update(
            "INSERT INTO " + testConfig.tableName() + " (id, timestamp, image) VALUES (?, ?, ?)",
            dto.getId(), dto.getTimestamp(), dto.getImage());
  }
}
