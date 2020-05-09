package qa.instagram.utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {

  static BasicDataSource ds = new BasicDataSource();

  static {
    ds.setMinIdle(5);
    ds.setMaxIdle(10);
    ds.setMaxOpenPreparedStatements(100);
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

  private DBCPDataSource() {
  }
}
