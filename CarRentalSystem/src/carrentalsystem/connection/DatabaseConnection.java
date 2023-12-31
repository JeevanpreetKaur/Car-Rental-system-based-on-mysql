package carrentalsystem.connection;
import java.sql.*;
public class DatabaseConnection {
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");        
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentaldb", "root", "root");
            return conn;
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return null;
    }
}
