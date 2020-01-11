package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import Database.database;

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
                getLVAs(req.getContentString().substring(3,11));
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

        /*if(req.getMethod().equals("POST")) {
            if(req.getContentString().substring(0,4).equals("user")) {
                html.setLoginAs(req.getContentString().substring(5));
                System.out.println(req.getContentString().substring(5));
                String htmlString = html.getLoginForm();

                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
            else {
//                System.out.println(req.getContentString());
//                System.out.println(req.getContentString().substring(3,11));
//                System.out.println(req.getContentString().substring(21,25));
//                System.out.println(req.getContentString().substring(31));
                String type="";
                switch(req.getContentString().charAt(32)) {
                    case 'y':
                        type = "sysadmin";
                        break;
                    case 'g':
                        type = "sgo";
                        break;
                    case 'e':
                        type = "lektor";
                        break;
                    case 't':
                        type = "student";
                        break;
                }
                Database.database db = new database();
                db.connect(type, type);
                if(loginCheck(type,type,req.getContentString().substring(3,11),req.getContentString().substring(21,25))) {
                    String htmlString = html.getloggedIn(req.getContentString().substring(3,11));
                    res.setStatusCode(200);
                    res.addHeader("Content-Type", "text/html");
                    res.addHeader("Content-length", String.valueOf(htmlString.length()));
                    res.addHeader("connection", "keep-alive");
                    res.setContentType("text/html");
                    res.setContent(htmlString);
                }
                else {
                    String htmlString = html.getLoginWrong();
                    res.setStatusCode(200);
                    res.addHeader("Content-Type", "text/html");
                    res.addHeader("Content-length", String.valueOf(htmlString.length()));
                    res.addHeader("connection", "keep-alive");
                    res.setContentType("text/html");
                    res.setContent(htmlString);
                }
                return res;
            }
        }
        else {
            String htmlString = html.getLoginForm();
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "keep-alive");
            res.setContentType("text/html");
            res.setContent(htmlString);
        }
        return res;

         */
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

    public void getLVAs(String un) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        Connection connect = database.getInstance().connect();
        PreparedStatement pst = connect.prepareStatement("SELECT Titel FROM LVA INNER JOIN studiengang ON studiengang.Studiengang_ID = LVA.fk_studiengang_Id INNER JOIN account on account.fk_studiengang_ID = studiengang.studiengang_id WHERE USERNAME = ?");
        pst.setString(1,un);
        ResultSet rd = pst.executeQuery();
        while(rd.next()) {
            String lva = rd.getString(1);
            html.appendLVA(lva);
        }
    }

}
