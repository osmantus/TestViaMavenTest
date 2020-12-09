package testSuites;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
/*import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;*/
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class WebSiteTest {

    /*@BeforeClass
    public void setUpConfig() {
        //Configuration.baseUrl = "https://rozetka.com.ua/";
        //Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
    }*/

    @BeforeMethod
    public void initTest() {
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;

        //System.setProperty("webdriver.gecko.driver", "C:\\driver\\FirefoxDriver\\geckodriver.exe");
        /*Configuration.baseUrl = "https://rozetka.com.ua/";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;*/

        Selenide.open("https://rozetka.com.ua/");

        /*WebDriver driver = WebDriverRunner.driver().getWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
        //driver.get("https://rozetka.com.ua/");

        /*System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1800x900");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get("https://rozetka.com.ua/");
        Selenide.open("https://rozetka.com.ua/");*/
    }

    @Test
    public void pageObjectWebSiteTest() throws InterruptedException {
        SiteMainPageLogic mainSite = new SiteMainPageLogic().categoriesCallingClick();

        NotebooksPageLogic notebooksPageLogic = mainSite.getNotebooksChapterAndOpenIt().findFirstNotebook();
        notebooksPageLogic.buySelectedNotebook();

        mainSite.comparePreBoughtNotebookTitle();
    }
}
