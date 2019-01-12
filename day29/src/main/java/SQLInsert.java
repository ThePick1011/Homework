import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SQLInsert {
    public static void insertIntoDatabase(String name,String username,String password) {
        String mysqlUrl = "jdbc:mysql://localhost:3306/user?rewriteBatchedStatements=true&useSSL=true";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(mysqlUrl, "root", "123456")) {
            PreparedStatement statement = connection.prepareStatement("insert into user (name,username,password)values(?,?,?) ");
            statement.setString(1,name);
            statement.setString(2,username);
            statement.setString(3,password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

