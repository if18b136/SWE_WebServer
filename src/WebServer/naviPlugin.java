package WebServer;

import java.io.File;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class naviPlugin implements Plugin {

	public static Map<String, LinkedList<String>> map = new Hashtable<>();
	public static boolean loading = false;

	@Override
	public float canHandle(Request req) {
		if(req.isValid()) {
			if(req.getUrl().getPath().contains("navi")) {
				return (float) 0.9;
			}
		}
		return 0;
	}
	
	@Override
	public Response handle(Request req) {
		Path path = Paths.get("D:\\OSM\\map.osm");
		File file = path.toFile();
		myResponse res = new myResponse();
		htmlConstructor html = new htmlConstructor();

		if(req.getMethod().equals("POST") && req.getContentLength() == 0) {
			processFile(file);
			System.out.print("\r\n" + naviPlugin.map + "\r\n" + "\r\n");
		}

		if(req.getMethod().equals("POST") && req.getContentLength() != 0) {

			String address = req.getContentString().substring(8,req.getContentLength());
			try {
				address = URLDecoder.decode(address,StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if(map.containsKey(address)) {
				LinkedList<String> value = map.get(address);
				for(String data : value) {
					html.addMap(data);
				}
			}
			else {
				String data = address + " not found.";
				html.addMap(data);
			}
		}
		String htmlString = html.getNavi();
		res.setStatusCode(200);
		res.addHeader("Content-Type", "text/html");
		res.addHeader("Content-length", String.valueOf(htmlString.length()));
		res.addHeader("connection", "close");
		res.setContentType("text/html");
		res.setContent(htmlString);
		return res;
	}

    private void processFile(File file) {
        try {
        	naviPlugin.map.clear();
        	SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = parserFactory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                private final Stack<String> eleStack = new Stack<String>();
                String street = "", city = "";

                @Override
                public void startElement(String uri, String localName, String qName,
                    Attributes attrs) throws SAXException {
                  if ("tag".equals(qName) && "way".equals(eleStack.peek())) {
                    if (attrs.getValue("k").equals("addr:city")) {
                    	city = attrs.getValue("v");
                    }
                    if (attrs.getValue("k").equals("addr:street"))
                    {
                    	street = attrs.getValue("v");
                    }
                  }
                  eleStack.push(qName);
                }

                @Override
                public void endElement(String uri, String localName, String qName)
                    throws SAXException {
                	if ("way".equals(qName))
                	{
                		if (!street.isEmpty() && !city.isEmpty())
                		{
                			LinkedList<String> list = new LinkedList();
                			if(!naviPlugin.map.containsKey(street))
                			{
                				list.add(city);
                				naviPlugin.map.put(street,list);
                			}
                			else if (!naviPlugin.map.get(street).contains(city))
                			{
                				list = naviPlugin.map.get(street);
                				list.add(city);
                				naviPlugin.map.put(street,list);
                			}                		
                		}
                		street = "";
                		city = "";
                	}
                	eleStack.pop();
                }
            };

            saxParser.parse(file, handler);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println("Error parsing XML file:");
            e.printStackTrace();
        }
    }
}
