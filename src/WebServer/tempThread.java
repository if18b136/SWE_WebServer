package WebServer;

import java.sql.*;

public class tempThread extends Thread {

    /**
     * <h3>Temperature Thread</h3>
     *  * Thread for Temperature Calculation and entries
     * @param threadName    private String number ot the temperature thread
     */
    private String threadName;

    public tempThread(String name) {
        /**
         * The Constructor of the tempThread class
         * Only used for thread number allocation
         * @param name      The number of the temperature thread converted to local
         */
        this.threadName = name;
    }

    @Override
    public void run() {
        /**
         * Method to create database connection, get entries and update the database
         * New entries are created until a certain number is reached (11.000)
         * 10.950 would be 3 entries per day for the last 10 years - so we round up
         * The timestamps and temperatures are randomly generated
         *
         * @param db        Connect to the database with admin rights
         * @param cmd       Prepared statement - the sql syntaax for DML
         * @param rd        Execute the before defined prepared statement
         * @param data      Current number of database entries at login time
         * @param offset    First possible timestamp for the random day
         * @param end       Last possible timestamp for the random day
         * @param diff      the 10 year span in which timestamps can be generated
         * @param randomTS  the randomised timestamp
         * @param min       minimum temperature
         * @param max       maximum temperature
         * @exception SQLException On sql error
         * @see SQLException
         */
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

            while(data < 11000) {

                long offset = Timestamp.valueOf("2010-01-01 00:00:00").getTime();
                long end = Timestamp.valueOf("2020-01-01 00:00:00").getTime();
                long diff = end - offset + 1;
                Timestamp randomTS = new Timestamp(offset + (long)(Math.random() * diff));

                double min = -10.0;
                double max = 35.0;
                double randomTemp = Math.round((min + Math.random() * (max - min)) * 10) / 10.0;

                data++;

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


