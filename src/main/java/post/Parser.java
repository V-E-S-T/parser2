package post;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.*;
import java.util.*;


public class Parser {

    public static void main(String[] args) {

        try {
            Document document = Jsoup.connect("https://www.autozone.com/repairinfo/specifications/specificationsMain.jsp")
//                    .data("year", "2005")
//                    .data("make", "Audi")
//                    .data("model", "A4")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/61.0")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Connection", "keep-alive")
                    .header("Accept", "*/*")
                    .post();
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }


//        try{
//            URL url = new URL("https://www.autozone.com");
//            URLConnection connection = url.openConnection();
//            connection.setDoOutput(true);
//            OutputStream out = connection.getOutputStream();
//            //FormUtility form =
//            connection.connect();
//            Map<String, List<String>> headers = connection.getHeaderFields();
//            headers.forEach((key, value) -> {
//                value.forEach((valueVALUE) -> System.out.println(key + ": " + valueVALUE));
//            });
//
//            System.out.println("---------------------------------------");
//
//            String encoding = connection.getContentEncoding();
//            if (encoding == null)
//                encoding = "UTF-8";
//
//            try(Scanner in = new Scanner(connection.getInputStream(), encoding))
//            {
//                for (int n  = 1; in.hasNextLine() && n <= 10; n++)
//                {
//                    System.out.println(in.nextLine());
//                }
//                if (in.hasNextLine())
//                    System.out.println(". . .");
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }
}
