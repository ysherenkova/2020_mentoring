package ua.mentoring.addressbook.tests;

import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;
import ua.mentoring.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    Connection connection = null;

    try {
      connection =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=");

      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");

      Groups groups = new Groups();
      while (resultSet.next()) {
        groups.add(new GroupData().withId(resultSet.getInt("group_id"))
                .withName(resultSet.getString("group_name"))
                .withHeader(resultSet.getString("group_header"))
                .withFooter(resultSet.getString("group_footer")));
      }
        resultSet.close();
        statement.close();
        connection.close();
      System.out.println(groups);
      } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
