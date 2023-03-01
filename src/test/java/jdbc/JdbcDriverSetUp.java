package jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDriverSetUp {
    Connection connection;

    public void dbDriverSetUp(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "tania123");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Connection getConnectionDb(){
        return connection;
    }

}
