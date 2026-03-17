import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

public class UrlShortenerServer 
{

    static Map<String, String> urlMap = new HashMap<>();
    static Map<String, Integer> clickCount = new HashMap<>();

    public static void main(String[] args) throws Exception 
	{

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 🔹 SHORTEN API
        server.createContext("/shorten", (HttpExchange exchange) -> 
		{

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            if (exchange.getRequestMethod().equals("POST")) 
				{

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody()));

                String originalUrl = reader.readLine();

                String shortCode = UUID.randomUUID().toString().substring(0, 6);

                urlMap.put(shortCode, originalUrl);
                clickCount.put(shortCode, 0);

                String shortUrl = "http://localhost:8080/" + shortCode;

                String response = "{ \"shortUrl\": \"" + shortUrl + "\" }";

                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // 🔹 REDIRECT API
        server.createContext("/", (HttpExchange exchange) -> 
		{

            String path = exchange.getRequestURI().getPath();

            if (path.length() > 1) {

                String code = path.substring(1);

                if (urlMap.containsKey(code)) 
					{

                    clickCount.put(code, clickCount.get(code) + 1);

                    String originalUrl = urlMap.get(code);

                    exchange.getResponseHeaders().add("Location", originalUrl);
                    exchange.sendResponseHeaders(302, -1);
                }
            }
        });

        server.start();
        System.out.println("✅ Server running at http://localhost:8080");
    }
}