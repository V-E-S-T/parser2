package post;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class SELENIUM {

    public static void main(String[] args) {

        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://www.autozone.com/repairinfo/specifications/specificationsMain.jsp");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
}
