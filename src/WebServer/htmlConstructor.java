package WebServer;

import java.sql.Timestamp;

/**
 * <h3>HTML Constructor Class</h3>
 * Class to create html pages with the correct content
 */
public class htmlConstructor {

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
//                            "<a class=\"nav-item nav-link\" href=\"/static\">Static</a>",
                            "<a class=\"nav-item nav-link\" href=\"toLower\">ToLower</a>",
                            "<a class=\"nav-item nav-link\" href=\"/navi\">Navigation</a>",
                        "</div>",
                    "</nav>"
            );

//    private static String navBegin = String.join("", "",
//            "<body>",
//                "<nav class=\"navbar navbar-dark bg-dark\">",
//                    "<div class=\"container d-flex flex-column flex-md-row justify-content-between\">",
//                        "<a class=\"nav-item nav-link\" href=\"/\">Home</a>",
//                        "<a class=\"nav-item nav-link\" href=\"/temp\">Temperature</a>",
//                        "<a class=\"nav-item nav-link\" href=\"/static\">Static</a>",
//                        "<a class=\"nav-item nav-link\" href=\"toLower\">ToLower</a>",
//                        "<a class=\"nav-item nav-link\" href=\"/navi\">Navigation</a>"
//            );
//
//    private static String navAdd = "";
//
//    private static String navEnd = String.join("", "",
//                    "</div>",
//                "</nav>"
//            );

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

    private static String toLower = String.join("","",
                "<div class=\"container\">",
                    "<h1>toLower Plugin</h1>",
                    "<form class=\"text-center\" style=\"color: #757575;\" action=\"/toLower\" method=\"post\" enctype=\"plain/text\">",
                        "<div class=\"md-form amber-textarea active-amber-textarea\">",
                            "<textarea id=\"form19\" class=\"md-textarea form-control\" name=\"toLowerText\" rows=\"3\"></textarea>",
                            "<label for=\"form19\">Write your toLower Text Here</label>",
                        "</div>",
                    "<button class=\"btn btn-outline-info btn-rounded btn-block z-depth-0 my-4 waves-effect\" type=\"submit\">Send</button>",
                    "</form>"
            );

    private static String lowerText = "";

//    public void addNav(String nav) {
//        navAdd = String.join("", navAdd,
//                    "<a class=\"nav-item nav-link\" href=\"/",nav,"\">",nav,"</a>"
//                );
//    }

    /**
     * Construct the standard home page
     * @return a joined string that represents the homepage
     */
    public String getHome() {
        return String.join("", begin,home,end);
    }

    /**
     * Expand the temperature table that will be displayed for the temperature plugin html page
     * @param id the primary key of the db entry (not needed)
     * @param temp the temperature
     * @param ts the timestamp
     */
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

    /**
     * Append db entries to the xml string that will be shown when a certain day is requested
     * @param id the primary key of the db entry (not needed)
     * @param temp the temperature
     * @param ts the timestamp
     */
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

    /**
     * Set the text that will be displayed in the toLower plugin under the text input field
     * @param s the lowercase text
     */
    public void setLowerText(String s) {
        lowerText = String.join("", "<div class=\"container\">",s, "</div>" );
    }

    /**
     * Create the html page for the temperature plugin
     * @return the html page as string
     */
    public String getTemp() {
        return String.join("",begin,tempBegin,tempContent,tempEnd,end);
    }

    /**
     * Create the xml file as a string - used for getting all timestamps and data of a certain date in the temperature plugin
     * @return the XML for a certain day as a string
     */
    public String getXML() { return String.join("",XMLDeclaration,tempXML,XMLEnd); }

    /**
     * Return the html page for the static file plugin
     * @return the html page for the static file plugin
     */
    public String getStatic() {
        return String.join("",begin,stat,end);
    }

    /**
     * Create the html page for the to Lower plugin
     * reset the input in order to wipe the content if site gets reloaded
     * @return the html page for the to lower plugin as a string
     */
    public String getToLower() {
        String toLowerString = String.join("",begin,toLower,lowerText,end);
        lowerText = "";
        return  toLowerString;
    }
}
