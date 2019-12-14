package WebServer;

public class htmlConstructor {
    private String body;
    private static String begin = String.join(" ","",
            "<!doctype html>",
            "<html lang=\"en\">",
                "<head>",
                    "<meta charset=\"utf-8\">",
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">",
                    "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">",

                    "<title>SWE1</title>",
                "</head>",
                "<body>",
                    "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">",
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
            "<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>",
                "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>",
                "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>",
            "</body>",
            "</html>"
            );

    private static String home = String.join(" ","",
                "<div class=\"container\">",
                    "<h1>Softwareprojekt SWE1 Startseite</h1>",
                "</div>"
            );

    private static String temp = String.join("","",
                "<div class=\"container\">",
                    "<h1>Temperature Plugin</h1>",
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

    public String getTemp() {
        return String.join("",begin,temp,end);
    }

    public String getStatic() {
        return String.join("",begin,stat,end);
    }
}
