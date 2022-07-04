package sample.market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectDB {

    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//jdbc:mysql://localhost/market
            connect = DriverManager.getConnection("jdbc:mysql://localhost/market", "root", "root");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
