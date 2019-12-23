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
            else if(req.getUrl().getPath().contains("GetTemperature")) {
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

            if(req.getUrl().getPath().contains("GetTemperature")) {
                // create XML
                String[] dateArr = req.getUrl().getPath().split("/");
                String date = "";
                boolean gotDate = false;
                for(String part : dateArr){
                    if(gotDate) {
                        date = String.join("-", date, part);
                    }
                    else if(part.equals("GetTemperature")) {
                        gotDate = true;
                    }
                }

                date = date.substring(1);  //the lazy way to get the first - away
                PreparedStatement cmd = db.prepareStatement("SELECT * FROM temperature WHERE DATE(timestamp) = ?");
                cmd.setString(1, date);

                ResultSet rd = cmd.executeQuery();

                while(rd.next()) {
                    int id = rd.getInt(1);
                    double temperature = rd.getDouble(2);
                    Timestamp timestamp = rd.getTimestamp(3);
                    // enter the stats into the xml file

                    html.appendXML(id,temperature,timestamp);
                }

                rd.close();
                cmd.close();

                myResponse res = new myResponse();

                //String htmlString = html.getml();
                String XML = html.getXML();

                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/xml");
                res.addHeader("Content-length", String.valueOf(XML.length()));
                res.addHeader("connection", "close");
                res.setContentType("text/xml");
                res.setContent(XML);

                return res;
            }
            else {

                PreparedStatement cmd = db.prepareStatement("SELECT * FROM temperature");
                ResultSet rd = cmd.executeQuery();

                while(rd.next()) {
                    int id = rd.getInt(1);
                    double temperature = rd.getDouble(2);
                    Timestamp timestamp = rd.getTimestamp(3);
                    html.appendTable(id,temperature,timestamp);
                }

                rd.close();
                cmd.close();
                // db.close();  // is redundant

                //htmlConstructor html = new htmlConstructor();
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
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            System.out.println("No Entries in database found.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Can not be reached if the temperature Plugin requests are handled correctly
            System.out.println("Temperature plugin could not handle request correctly.");
        }
        return null;
    }
}
