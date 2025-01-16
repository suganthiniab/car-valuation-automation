package motorway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage{

    private WebDriver driver;

    static String modleName_h1 = "//h1";
    static String regNo = "//div[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']";
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

//    public WebElement getTxtbox_vehicleRegistration(){
//         return driver.findElement(By.id("vrm-input"));
//        //return txtbox_vehicleRegistration;
//    }
//
//    public WebElement getValueYourCarButton() {
//        return driver.findElement(By.xpath("//span[@class='Button-module__label-SKEy']"));
//    }

    public String getLabel_displayMakeModel() {
        return driver.findElement(By.xpath(modleName_h1)).getText();
    }

    public String getDisplayVariantReg() {
        return driver.findElement(By.xpath(regNo)).getText();
    }

    public String getDisplay_Year() {
        return driver.findElement(By.xpath(year)).getText();
    }
}

