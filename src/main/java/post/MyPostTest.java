package post;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class MyPostTest {

    public static void main(String[] args) throws IOException
    {
//        String propsFilename = args.length > 0 ? args[0] : "C:\\Users\\VEST\\Desktop\\parser\\src\\main\\resources\\post.properties";
//        Properties props = new Properties();
//        try (InputStream in = Files.newInputStream(Paths.get(propsFilename)))
//        {
//            props.load(in);
//        }
        Properties props = new Properties();
        props.setProperty("Year", "2010");
        String urlString = "https://www.autozone.com/";
        Object userAgent = "HTTPie/0.9.2";
        Object redirects = "10";
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
//        String result = doPost(new URL(urlString), props,
//                userAgent == null ? null : userAgent.toString(),
//                redirects == null ? -1 : Integer.parseInt(redirects.toString()));

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.0.3705)");
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        System.out.println(connection.getResponseCode());


        System.out.println();
    }

    /**
     * Do an HTTP POST.
     * @param url the URL to post to
     * @param nameValuePairs the query parameters
     * @param userAgent the user agent to use, or null for the default user agent
     * @param redirects the number of redirects to follow manually, or -1 for automatic redirects
     * @return the data returned from the server
     */
    public static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent, int redirects)
            throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //connection.setRequestMethod("GET");
        System.out.println(connection.getContent());

        //connection.setRequestMethod("GET");
       // connection.setDoInput(false);

       // connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

      //  connection.setRequestProperty("User-Agent", userAgent);

        connection.setInstanceFollowRedirects(false);



       // connection.setDoOutput(true);

//        try (PrintWriter out = new PrintWriter(connection.getOutputStream()))
//        {
//            boolean first = true;
//            for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet())
//            {
//                String name = pair.getKey().toString();
//                String value = pair.getValue().toString();
//                out.print(name);
//                out.print('=');
//                out.print(URLEncoder.encode(value, "UTF-8"));
//            }
//        }
     //   String encoding = connection.getContentEncoding();
        //if (encoding == null) encoding = "UTF-8";

//        if (redirects > 0)
//        {
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
//                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
//                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER)
//            {
//                String location = connection.getHeaderField("Location");
//                if (location != null)
//                {
//                    URL base = connection.getURL();
//                    connection.disconnect();
//                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
//                }
//
//            }
//        }
//        else if (redirects == 0)
//        {
//            throw new IOException("Too many redirects");
//        }

        StringBuilder response = new StringBuilder();
        System.out.println(connection.getResponseCode());

//        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())))
//        {
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line+"\n");
//            }
//            br.close();
//            return sb.toString();
//        }
//        catch (IOException e)
//        {
//            System.out.println("жопа");
//            InputStream err = connection.getErrorStream();
//            if (err == null) throw e;
//            try (Scanner in = new Scanner(err))
//            {
//                response.append(in.nextLine());
//                response.append("\n");
//            }
//        }

        return null;
    }
}
