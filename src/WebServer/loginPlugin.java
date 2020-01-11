package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import Database.database;
import oracle.jdbc.OracleTypes;

import java.sql.*;

public class loginPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getPath().contains("login")) {
                return (float) 0.9;
            }
        }
        return 0;
    }

    @Override
    public Response handle(Request req) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        if(req.getMethod().equals("POST")) {
            //call login function with data from form
            System.out.println(req.getContentLength());
            System.out.println(req.getContentString().substring(3,11) + "><" + req.getContentString().substring(21,25) + ">");

            int login = sum4(req.getContentString().substring(3,11),req.getContentString().substring(21,25));
            if(login == 1) {
                //prepared statement here
                getLvaCursor(req.getContentString().substring(3,11));
                String htmlString = html.getLvaList();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
            else {
                System.out.println("Login unsuccessfull.");
                String htmlString = html.getLoginForm();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
        }
        else {
            //int test = sum4(20,30);
            //System.out.println(test);
            String data = getStudents();
            System.out.println(data);

            String htmlString = html.getLoginForm();
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "keep-alive");
            res.setContentType("text/html");
            res.setContent(htmlString);
        }

        return res;
    }

    public int loginCheck(String username, String password) throws SQLException {
        CallableStatement stmt = database.getInstance().connect().prepareCall("{?= call LOGIN_CHECK(?,?)}");
        stmt.setString(2,username);
        stmt.setString(3,password);
        stmt.registerOutParameter(1, Types.INTEGER);
        return stmt.getInt(1);
    }

    public int sum4(String username, String password) throws SQLException {
        CallableStatement stmt=database.getInstance().connect().prepareCall("{?= call sum4(?,?)}");
        stmt.setString(2,username);
        stmt.setString(3,password);
        stmt.registerOutParameter(1,Types.INTEGER);
        stmt.execute();

        System.out.println(stmt.getInt(1));
        return stmt.getInt(1);
    }


    public String getStudents() throws SQLException {
        Connection connect = database.getInstance().connect();
        if(connect.isValid(5000)) {
            PreparedStatement pst = connect.prepareStatement("SELECT * FROM account");
            ResultSet rd = pst.executeQuery();
            String data = "";
            while(rd.next()) {
                int stid = rd.getInt(1);
                int stgid = rd.getInt(2);
                String username = rd.getString(3);
                data = data + String.valueOf(stid) + String.valueOf(stgid) + username;
            }

            rd.close();
            pst.close();
            return data;
        }
        return null;
    }

    public void getLvaCursor(String username) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_lva_info(?,?)}");
        cstmt.setString(1, username);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rd = (ResultSet) cstmt.getObject(2);
        while (rd.next()) {
            String lva = rd.getString("titel");
            String bezeichnung = rd.getString("nachname");
            String lektor = rd.getString("vorname");
            html.appendLVA(lva,bezeichnung,lektor);
        }
    }

}
