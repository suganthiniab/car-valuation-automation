package motorway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    By registrationNoTextBox = By.id("vrm-input");
    By evaluateButton = By.xpath("//span[@class='Button-module__label-SKEy']");
    By makeModelLabel = By.xpath("//h1");
    By registrationNoLabel = By.xpath("//div[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']");
    By yearLabel = By.xpath("//ul[@class='HeroVehicle__details-XpAI']/li[1]");
    By checkAgainButton = By.xpath("//span[text()='Check again']");
    By mileageTextBox = By.id("mileage-input");
    By confirmMileageButton = By.xpath("//span[text()='Confirm mileage']");

    WebElement foundElement;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputRegistrationNumber(String registrationNumber) {
        driver.findElement(registrationNoTextBox).sendKeys(registrationNumber);
    }

    public void clickValueYourCar() {
        driver.findElement(evaluateButton).click();

        foundElement = waitForEitherElementPresence(driver, registrationNoLabel, checkAgainButton);
    }

    public boolean isCarFound() {
        return !foundElement.getText().equalsIgnoreCase("Check again");
    }

    public String getLabel_displayMakeModel() {
        return driver.findElement(makeModelLabel).getText();
    }

    public String getDisplayVariantReg() {
        return driver.findElement(registrationNoLabel).getText();
    }

    public String getDisplay_Year() {
        return driver.findElement(yearLabel).getText();
    }

    public void enterMileage(String mileage) {
        driver.findElement(mileageTextBox).sendKeys(mileage);
    }

    public void clickConfirmMileage() {
        driver.findElement(confirmMileageButton).click();
    }

    private WebElement waitForEitherElementPresence(WebDriver driver, By registrationNoLabel, By checkAgainButton) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until((ExpectedCondition<WebElement>) wd -> {
            try {
                return wd.findElement(registrationNoLabel);
            } catch (Exception ignored) {

            }
            try {
                return wd.findElement(checkAgainButton);
            } catch (Exception ignored) {

            }
            return null;
        });
    }
}

