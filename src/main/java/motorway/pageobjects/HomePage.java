package motorway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage{

    private WebDriver driver;

    static String makeModel = "//h1";
    static String registrationNo = "//div[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']";
    static String year = "//ul[@class='HeroVehicle__details-XpAI']/li[1]";


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void inputRegistrationNumber(String registrationNumber)
    {
        driver.findElement(By.id("vrm-input")).sendKeys(registrationNumber);
    }

    public void clickValueYourCar()
    {
        driver.findElement(By.xpath("//span[@class='Button-module__label-SKEy']")).click();
    }

    public String getLabel_displayMakeModel() {
        return driver.findElement(By.xpath(makeModel)).getText();
    }

    public String getDisplayVariantReg() {
        return driver.findElement(By.xpath(registrationNo)).getText();
    }

    public String getDisplay_Year() {
        return driver.findElement(By.xpath(year)).getText();
    }
}

