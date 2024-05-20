package Connection;
import java.sql.*;

public class DBConnection
{
    private static final String URL = "jdbc:mysql://localhost:3300/jukeapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2015@Mobilio";

    public static Connection getConnection() throws SQLException,NullPointerException
    {
        Connection connection = null;
        try
         {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //System.out.println("Connected to the Database");
         }
        catch (SQLException e)
         {
            System.out.println("Connection failed. Error: " + e.getMessage());
            throw e;
         }
        return connection;
    }

    public static void closeConnection(Connection connection)
    {
        if (connection != null)
        {
            try
             {
                connection.close();
                System.out.println("Connection closed");
             }
            catch (SQLException e)
             {
                System.out.println("Error while closing connection: " + e.getMessage());
             }
        }
    }
}