package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import java.sql.*;

/**
 * <h3>Temperature Plugin Class</h3>
 * This plugin handles temperature requests
 */
public class tempPlugin implements Plugin {


    /**
     * Look for temperature keywords from the request path
     * @param req the client request
     * @return a float if the request can be handled by the plugin or 0 if not
     */
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

    /**
     * Create a connection to the sql db
     * Check if an XML file is requested (getTemperature)
     * if yes, determine the date from the url and make a prepared statement for the db
     * send the results to the html constructor for XML creation and finally send a server response with the XML
     * if no, all temperature entries in the db will be displayed as a table
     * @param req the client request
     * @return the correct server response or a 500 internal error if any exception happened
     */
    @Override
    public Response handle(Request req) {
        try (Connection db = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/temperature", "root", "")) {
            htmlConstructor html = new htmlConstructor();
            if (db != null) {
                System.out.println("Connected to the database!");

            } else {
                throw new SQLException("Failed to make connection!");
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

                boolean notEmpty = false;   //if no entries are found we have to respond with http 404
                while(rd.next()) {
                    int id = rd.getInt(1);
                    double temperature = rd.getDouble(2);
                    Timestamp timestamp = rd.getTimestamp(3);
                    // enter the stats into the xml file
                    notEmpty = true;
                    html.appendXML(id,temperature,timestamp);
                }
                rd.close();
                cmd.close();
                myResponse res = new myResponse();

                if(notEmpty) {
                    String XML = html.getXML();
                    res.setStatusCode(200);
                    res.addHeader("Content-Type", "text/xml");
                    res.addHeader("Content-length", String.valueOf(XML.length()));
                    res.addHeader("connection", "close");
                    res.setContentType("text/xml");
                    res.setContent(XML);
                } else {
                    res.setStatusCode(404);
                    res.addHeader("connection", "close");
                }
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
        }
        myResponse res = new myResponse();
        res.setStatusCode(500);
        return res;
    }
}
