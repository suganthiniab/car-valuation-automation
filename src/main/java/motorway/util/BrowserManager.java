package motorway.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class BrowserManager {

    public static WebDriver getBrowserManager(String browserType, String baseURL) {
        WebDriver driver = null;
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browserType.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
        } else {
            System.out.println("Please provide correct browser type");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get(baseURL);
        return driver;
    }
}
