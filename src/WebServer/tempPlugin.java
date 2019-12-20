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
                "jdbc:mysql://127.0.0.1:3306/test", "root", "")) {

            if (db != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

            PreparedStatement cmd = db.prepareStatement("SELECT * FROM test");
            ResultSet rd = cmd.executeQuery();

            while(rd.next())
            {
                int test1 = rd.getInt(1);
                String test2 = rd.getString(2);

                System.out.println(test1 + " : " + test2);
            }

            rd.close();
            cmd.close();
            // db.close();  // is redundant

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        String htmlString = html.getTemp();
        res.setStatusCode(200);
        res.addHeader("Content-Type", "text/html");
        res.addHeader("Content-length", String.valueOf(htmlString.length()));
        res.addHeader("connection", "close");
        res.setContentType("text/html");
        res.setContent(htmlString);
        return res;
    }
}
