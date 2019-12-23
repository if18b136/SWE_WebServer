package WebServer;

import java.sql.Timestamp;

public class htmlConstructor {
    private String body;

    private static String begin = String.join(" ","",
            "<!doctype html>",
            "<html lang=\"en\">",
                "<head>",
                    "<meta charset=\"utf-8\">",
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">",
                    "<meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">",
                    "<link rel=\"icon\" href=\"deploy/MDB/img/mdb-favicon.ico\" type=\"image/x-icon\">",

                    "<title>SWE1</title>",
                    "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">",
                    //Font Awesome -->
                    "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.11.2/css/all.css\">",
                    //Bootstrap core CSS -->
                    "<link rel=\"stylesheet\" href=\"deploy/MDB/css/bootstrap.min.css\">",
                    //Material Design Bootstrap -->
                    "<link rel=\"stylesheet\" href=\"deploy/MDB/css/mdb.min.css\">",
                    //Your custom styles (optional) -->
                    //"<link rel=\"stylesheet\" href=\"deploy/MDB/css/style.css\">",
                    //<!-- MDBootstrap Datatables  -->
                    "<link href=\"deploy/MDB/css/addons/datatables.min.css\" rel=\"stylesheet\">",

                "</head>",
                "<body>",
                    "<nav class=\"navbar navbar-dark bg-dark\">",
                        "<div class=\"container d-flex flex-column flex-md-row justify-content-between\">",
                            "<a class=\"nav-item nav-link\" href=\"/\">Home</a>",
                            "<a class=\"nav-item nav-link\" href=\"/temp\">Temperature</a>",
                            "<a class=\"nav-item nav-link\" href=\"/static\">Static</a>",
                            "<a class=\"nav-item nav-link\" href=\"/lower\">ToLower</a>",
                            "<a class=\"nav-item nav-link\" href=\"/Navi\">Navigation</a>",
                        "</div>",
                    "</nav>"
            );

    private static String end = String.join(" ","",
                //"<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>",
                //    "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>",
                //    "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>",
                //<!-- jQuery -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/jquery.min.js\"></script>",
                //<!-- Bootstrap tooltips -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/popper.min.js\"></script>",
                //<!-- Bootstrap core JavaScript -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/bootstrap.min.js\"></script>",
                //<!-- MDB core JavaScript -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/mdb.min.js\"></script>",
                //<!-- MDBootstrap Datatables  -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/addons/datatables.min.js\"></script>",
                "<script type=\"text/javascript\">",
                    "$(document).ready(function () {",
                        "$('#TEMPERATURE').DataTable();",
                        "$('.dataTables_length').addClass('bs-select');",
                    "});",
                "</script>",
            "</body>",
            "</html>"
            );

    private static String home = String.join(" ","",
                "<div class=\"container\">",
                    "<h1>Softwareprojekt SWE1 Startseite</h1>",
                "</div>"
            );

    private static String tempBegin = String.join("","",
                "<div class=\"container\">",
                    "<h1>Temperature Plugin</h1>",
                "</div>",
                "<div class=\"container\">",
                    "<table id=\"TEMPERATURE\" class=\"table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">",
                        "<thead>",
                            "<tr>",
                                "<th class=\"th-sm\">ID</th>",
                                "<th class=\"th-sm\">Temperature</th>",
                                "<th class=\"th-sm\">Timestamp</th>",
                             "</tr>",
                        "</thead>",
                        "<tbody>"
            );

    private static String tempContent = "";
    private static String tempXML = "";

    private static String XMLDeclaration = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><statistics>";
    private static String XMLEnd = "</statistics>";

    private static String tempEnd = String.join("","",
                        "</tbody>",
                        "<tfoot>",
                            "<tr>",
                                "<th>ID</th>",
                                "<th>Temperature</th>",
                                "<th>Timestamp</th>",
                            "</tr>",
                        "</tfoot>",
                    "</table>",
                "</div>"
            );

    private static String stat = String.join("","",
                "<div class=\"container\">",
                    "<h1>Static Plugin</h1>",
                "</div>"
             );

    public String getHome() {
        return String.join("", begin,home,end);
    }

    public void appendTable(int id, double temp, Timestamp ts) {
        String idString = String.valueOf(id);
        String tempString = String.valueOf(temp);
        String tsString = String.valueOf(ts);
        tempContent = String.join("",tempContent,
                "<tr>",
                    "<td>",idString,"</td>",
                    "<td>",tempString,"</td>",
                    "<td>",tsString,"</td>",
                "</tr>"
        );
    }

    public void appendXML(int id, double temp, Timestamp ts) {
        String idString = String.valueOf(id);
        String tempString = String.valueOf(temp);
        String tsString = String.valueOf(ts);
        String date = tsString.split(" ")[0];
        tempXML = String.join("", tempXML,
                "<date>", date,
                "   <timestamp>", tsString,
                "       <id>", idString, "</id>",
                "       <temperature>", tempString, "</temperature>",
                "   </timestamp>",
                "</date>"
                );
    }

    public String getTemp() {
        return String.join("",begin,tempBegin,tempContent,tempEnd,end);
    }
    public String getXML() { return String.join("",XMLDeclaration,tempXML,XMLEnd); }
    public String getStatic() {
        return String.join("",begin,stat,end);
    }
}
