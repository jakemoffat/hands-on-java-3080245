package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
  // This class will handle connecting to the database
  // As well as reading and writing from it

  public static Connection connect() {
    // Static method that will return the database connection
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("Connection Granted");
    } catch(SQLException exception) {
      exception.printStackTrace();
    }

    return connection;

  }

  public static void main(String[] args) {
    connect();
  }

}
