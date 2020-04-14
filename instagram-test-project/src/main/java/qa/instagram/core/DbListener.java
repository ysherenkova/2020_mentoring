package qa.instagram.core;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testcontainers.containers.MariaDBContainer;
import org.testng.IExecutionListener;
import qa.instagram.utils.DBManager;
import qa.instagram.utils.TestConfig;

public class DbListener implements IExecutionListener {
  protected static Logger logger = LogManager.getLogger(DbListener.class);
  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);


  @Override
  public void onExecutionStart() {
    final MariaDBContainer mariaDB = new MariaDBContainer()
            .withDatabaseName(DBManager.getInstance().getDataBaseName());

    mariaDB.start();

    logger.info("Init DB");
    DBManager.initJdbcTemplate(mariaDB);
    DBManager.getInstance().createTable(testConfig.photosTableName());
  }

}
