package motorway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    static String makeModel_header = "//h1";
    static String registrationText_id = "vrm-input";
    static String evaluvateButton_xpath = "//span[@class='Button-module__label-SKEy']";
    static String registrationNo_xpath = "//div[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']";
    static String year_xpath = "//ul[@class='HeroVehicle__details-XpAI']/li[1]";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputRegistrationNumber(String registrationNumber) {
        driver.findElement(By.id(registrationText_id)).sendKeys(registrationNumber);
    }

    public void clickValueYourCar() {
        driver.findElement(By.xpath(evaluvateButton_xpath)).click();

        try {
            //wait for valuation complete
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(registrationNo_xpath)));
        } catch (TimeoutException e) {
            System.out.println(" Element is not visible");
            throw new AssertionError("Car registration lookup failed for " + driver.findElement(By.id(registrationText_id)).getText());
        }
    }

    public String getLabel_displayMakeModel() {
        return driver.findElement(By.xpath(makeModel_header)).getText();
    }

    public String getDisplayVariantReg() {
        return driver.findElement(By.xpath(registrationNo_xpath)).getText();
    }

    public String getDisplay_Year() {
        return driver.findElement(By.xpath(year_xpath)).getText();
    }
}

