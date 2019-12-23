package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import java.sql.*;

public class tempPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getPath().contains("temp")) {
                return (float) 0.9;
            }
        }
        return 0;
    }

    @Override
    public Response handle(Request req) {
        try (Connection db = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/temperature", "root", "")) {
            htmlConstructor html = new htmlConstructor();
            if (db != null) {
                System.out.println("Connected to the database!");

            } else {
                System.out.println("Failed to make connection!");
                // throw exception
            }

            PreparedStatement cmd = db.prepareStatement("SELECT * FROM temperature");
            ResultSet rd = cmd.executeQuery();

            while(rd.next())
            {
                int id = rd.getInt(1);
                double temperature = rd.getDouble(2);
                Timestamp timestamp = rd.getTimestamp(3);
                html.appendTable(id,temperature,timestamp);
            }

            rd.close();
            cmd.close();
            // db.close();  // is redundant

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        String htmlString = html.getTemp();
        res.setStatusCode(200);
        res.addHeader("Content-Type", "text/html");
        res.addHeader("Content-length", String.valueOf(htmlString.length()));
        res.addHeader("connection", "keep-alive");
        res.setContentType("text/html");
        res.setContent(htmlString);
        return res;
    }
}
