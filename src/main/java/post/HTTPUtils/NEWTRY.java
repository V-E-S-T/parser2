package post.HTTPUtils;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NEWTRY {

    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);

    public static void main(String[] args) throws IOException {

        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setTimeout(2000);
        webClient.getOptions().setUseInsecureSSL(true);
        // overcome problems in JavaScript
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());

        HtmlPage page = null;

//        https://www.innoq.com/en/blog/webscraping/

        try {
            List<HtmlAnchor> searchResults = new ArrayList<>();
            page = webClient
                    .getPage("https://www.autozone.com/repairinfo/specifications/specificationsMain.jsp");
            System.out.println(page.asXml());
//            final HtmlForm searchForm = htmlElementByXPath(page,
//                    "//form[contains(@action,'pagetreesearch')]",
//                    HtmlForm.class).get();
//            final HtmlInput searchField = htmlElementByXPath(page,
//                    "//input[@name='queryString' and contains(@class ,'medium-field')]",
//                    HtmlInput.class).get();
//            searchField.setValueAttribute("Requirements");
//            HtmlSubmitInput submit = (HtmlSubmitInput) searchForm
//                    .getElementsByAttribute("input", "type", "submit")
//                    .get(0);
//            page = submit.click();
//            Optional<HtmlAnchor> nextLink = null;
//            do {
//                searchResults.addAll(
//                        page.getByXPath("//a[contains(@class,'search-result-link')]")
//                                .stream().map(HtmlAnchor.class::cast)
//                                .collect(Collectors.toList()));
//                nextLink = htmlElementByXPath(page, "//a[@class='pagination-next']",
//                        HtmlAnchor.class);
//                if (nextLink.isPresent()) {
//                    page = nextLink.get().click();
//                }
//            } while (nextLink.isPresent());
//            searchResults.stream().map(a -> a.getTextContent() + "->"
//                    + a.getAttribute("href"))
//                    .forEach(System.out::println);
        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace();
        }
//        HtmlPage htmlPage = webClient.getPage("https://www.autozone.com/repairinfo/specifications/specificationsMain.jsp");
//        System.out.println(htmlPage.asXml());
    }

    public static <T> Optional<T>  htmlElementByXPath(
            DomNode node,
            String xpath,
            Class<T> type) {
        return node.getByXPath(xpath).stream()
                .filter(o->type.isAssignableFrom(o.getClass()))
                .map(type::cast).findFirst();
    }
}
