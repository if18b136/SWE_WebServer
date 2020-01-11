package Database;

import java.sql.*;

public class database {
    //String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@wi-gate.technikum-wien.at:60476:XE";
    private String user = "sysadmin";
    private String pwd = "sysadmin";
    private static database db = null;

    public static database getInstance() {
        if (db == null)
            db = new database();
        return db;
    }


    public Connection connect() {
        try {
            Connection db = DriverManager.getConnection(url, user, pwd);
            if (db != null) {
                System.out.println("Connected to the database!");
                return db;
            } else {
                System.out.println("Failed to make connection!");
                throw new SQLException("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("database connect function");
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet execute(String query) throws SQLException {
        Connection db = this.connect();
        Statement st = db.createStatement();
        return st.executeQuery(query);
    }

    public void insert(String query) throws SQLException {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
