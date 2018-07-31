package post;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;


public class JSOUP {


//        HtmlSelect makeSelect = form.getSelectByName("make");
//        makeSelect.setSelectedAttribute("", true);


    public static void main(String[] args) {

        final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setRefreshHandler(new NiceRefreshHandler(10));
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setAppletEnabled(true);
        webClient.getOptions().setTimeout(60000);
        //webClient.setRedirectEnabled(false);

        //webClient.setJavaScriptEnabled(false);
        //webClient.setPopupBlockerEnabled(true);

        //webClient.setPrintContentOnFailingStatusCode(false);


        final HtmlPage currentPage;
        try {


            currentPage = webClient.getPage("https://www.autozone.com/repairinfo/specifications/specificationsMain.jsp");
            WebResponse response = currentPage.getWebResponse();
            WebRequest request = response.getWebRequest();
            request.getRequestParameters().forEach(nameValuePair -> System.out.println(nameValuePair.getName() + ": " + nameValuePair.getValue()));
//            Thread thread = Thread.currentThread();
//            thread.wait(2000);
            HtmlPage currentPage2 = webClient.getPage(currentPage.getWebResponse().getWebRequest());
            //thread.wait(2000);
            WebResponse response2 = currentPage2.getWebResponse();
            String content = response2.getContentAsString();
            //int status = webClient.getWebConnection().getResponse(new WebRequest(new URL("https://www.autozone.com/repairinfo/specifications/specificationsMain.jsp"))).getStatusCode();
            //String html = Jsoup.connect("http://stackoverflow.com").get().html();
            //currentPage.executeJavaScript("https://www.autozone.com/azdstl49.js");

            //currentPage.executeJavaScript("https://www.autozone.com/repairinfo/specifications/specificationsMain.jsp");

            //System.out.println(currentPage.);
            System.out.println("-------------------------------------------");
            //String pageContent = currentPage.getWebResponse().getContentAsString();
           // System.out.println(content);
           // currentPage.getForms().forEach(htmlForm -> System.out.println(htmlForm.getAcceptAttribute()));
           // currentPage.getBody().getDomElementDescendants().forEach(domElement -> System.out.println(domElement.getNodeName()));


//            final HtmlForm form = currentPage.getFormByName("ymme-form");
//            HtmlSelect yearSelect = form.getSelectByName("year");
//            yearSelect.setSelectedAttribute("9010000", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
