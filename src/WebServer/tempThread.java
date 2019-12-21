package WebServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class tempThread extends Thread {
    private String threadName;

    public tempThread(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        //connect to database and check how may entries there are already in the table
        try (Connection db = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/temperature", "root", "")) {

            if (db != null) {
                System.out.println("Thread status: "  + threadName + " connected to the Temperature database!");
            } else {
                System.out.println("Thread status: "  + threadName + " failed to make Temperature database connection!");
            }

            PreparedStatement cmd = db.prepareStatement("SELECT COUNT(*) FROM temperature");
            ResultSet rd = cmd.executeQuery();
            rd.next();
            int data = rd.getInt(1);
            System.out.println("Thread status: " + threadName + " Temperature database entries: " + data);

            while(data <= 11000) { // 10.950 would be 3 entries per day for the last 10 years

                // create new timestamp - will be completely random!
                long offset = Timestamp.valueOf("2010-01-01 00:00:00").getTime();
                long end = Timestamp.valueOf("2020-01-01 00:00:00").getTime();
                long diff = end - offset + 1;
                Timestamp randomTS = new Timestamp(offset + (long)(Math.random() * diff));

                double min = -10.0;
                double max = 35.0;
                double randomTemp = Math.round((min + Math.random() * (max - min)) * 10) / 10;

                data++; //next id

                cmd = db.prepareStatement("INSERT INTO temperature (id, temperature, timestamp) VALUES (?, ?, ?)");
                cmd.setInt(1, data);
                cmd.setDouble(2,randomTemp);
                cmd.setTimestamp(3,randomTS);

                cmd.executeUpdate();
            }

            rd.close();
            cmd.close();
            // db.close();  // is redundant

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
