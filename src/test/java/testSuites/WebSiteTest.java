package testSuites;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;

public class WebSiteTest {

    @BeforeClass
    public void setUpConfig() {
        Configuration.baseUrl = "https://rozetka.com.ua/";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
    }

    @BeforeMethod
    public void initTest() {
        //System.setProperty("webdriver.gecko.driver", "C:\\driver\\FirefoxDriver\\geckodriver.exe");
        /*Configuration.baseUrl = "https://rozetka.com.ua/";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;*/
        open("https://rozetka.com.ua/");
        /*WebDriver driver = WebDriverRunner.driver().getWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
        //driver.get("https://rozetka.com.ua/");
    }

    @Test
    public void pageObjectWebSiteTest() throws InterruptedException {
        SiteMainPageLogic mainSite = new SiteMainPageLogic().categoriesCallingClick();

        NotebooksPageLogic notebooksPageLogic = mainSite.getNotebooksChapterAndOpenIt().findFirstNotebook();
        notebooksPageLogic.buySelectedNotebook();

        mainSite.comparePreBoughtNotebookTitle();
    }
}
