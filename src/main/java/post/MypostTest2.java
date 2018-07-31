package post;

import post.HTTPUtils.HTMLTag;
import post.HTTPUtils.ParseHTML;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

import static post.HTTPUtils.Includes.BUFFER_SIZE;

public class MypostTest2 {

    public static void main(String[] args) throws IOException
    {
        String propsFilename = args.length > 0 ? args[0] : "D:\\Users7\\Admin\\Desktop\\parser2\\src\\main\\resources\\post2.properties";
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(propsFilename)))
        {
            props.load(in);
        }
        String urlString = props.remove("url").toString();
        Object userAgent = props.remove("User-Agent");
        Object redirects = props.remove("redirects");
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String result = doPost(new URL(urlString), props,
                userAgent == null ? null : userAgent.toString(),
                redirects == null ? -1 : Integer.parseInt(redirects.toString()));
        System.out.println(result);
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

        //URL u = new URL()
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept", "*/*");
        //connection.setRequestProperty("atg-rest-depth", "2");
        if (userAgent != null)
            connection.setRequestProperty("User-Agent", userAgent);

        if (redirects >= 0)
            connection.setInstanceFollowRedirects(true);

        connection.setDoOutput(true);

        try (PrintWriter out = new PrintWriter(connection.getOutputStream()))
        {
            boolean first = true;
            for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet())
            {
                if (first) first = false;
                else out.print('&');
                String name = pair.getKey().toString();
                String value = pair.getValue().toString();
                out.print(name);
                out.print('=');
                out.print(URLEncoder.encode(value, "UTF-8"));
            }
        }
        String encoding = connection.getContentEncoding();
        if (encoding == null) encoding = "UTF-8";

        if (redirects > 0)
        {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER)
            {
                String location = connection.getHeaderField("Location");
                if (location != null)
                {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
                }

            }
        }
        else if (redirects == 0)
        {
            throw new IOException("Too many redirects");
        }

        StringBuilder response = new StringBuilder();
        try (InputStream is = connection.getInputStream(); Scanner in = new Scanner(is, encoding))
        {

            ParseHTML parse = new ParseHTML(is);
            //StringBuilder buffer = new StringBuilder();

            int ch;
            while ((ch = parse.read()) != -1)
            {
                if (ch == 0)
                {
                    HTMLTag tag = parse.getTag();
                    if (tag.getName().equalsIgnoreCase("script") && tag.getAttributeValue("src")!=null)
                    {
                        String src = tag.getAttributeValue("src");
                        URL u = new URL(url,src);
                        String include = downloadPage(u);
                        response.append("<script>");
                        response.append(include);
                        response.append("</script>");
                    }
                    else
                    {
                        response.append(tag.toString());
                    }
                }
                else
                {
                    response.append((char)ch);
                }
            }



//            while (in.hasNextLine())
//            {
//                response.append(in.nextLine());
//                response.append("\n");
//            }
        }
        catch (IOException e)
        {
            InputStream err = connection.getErrorStream();
            if (err == null) throw e;
            try (Scanner in = new Scanner(err))
            {
                response.append(in.nextLine());
                response.append("\n");
            }

        }

        return response.toString();
    }

    /**
     * This method downloads the specified URL into a Java
     * String. This is a very simple method, that you can
     * reused anytime you need to quickly grab all data from
     * a specific URL.
     *
     * @param url The URL to download.
     * @return The contents of the URL that was downloaded.
     * @throws IOException Thrown if any sort of error occurs.
     */
    public static String downloadPage(URL url) throws IOException
    {
        StringBuilder result = new StringBuilder();
        byte buffer[] = new byte[BUFFER_SIZE];

        InputStream s = url.openStream();
        int size = 0;

        do
        {
            size = s.read(buffer);
            if (size != -1)
                result.append(new String(buffer, 0, size));
        } while (size != -1);

        return result.toString();
    }
}
