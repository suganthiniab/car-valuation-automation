package motoway.pageobjects;

import motorway.util.BrowserManager;
import motoway.pageobjects.util.FileUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import motorway.pageobjects.HomePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePageTest {

    private WebDriver driver;
    private FileUtil fileUtil;

    @BeforeTest
    public void setup() {
        fileUtil = new FileUtil();
        driver = BrowserManager.getBrowserManager("chrome", "https://motorway.co.uk/");
    }

    @Test(dataProvider = "cardata")
    public void testCarEvaluate(String registrationNumber) {
        Map<String, Car> map = fileUtil.readOutputFile();
        Car car = map.get(registrationNumber);

        HomePage homePageMotorway = new HomePage(driver);
        homePageMotorway.inputRegistrationNumber(registrationNumber);
        homePageMotorway.clickValueYourCar();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String actualResult_makeModel = homePageMotorway.getLabel_displayMakeModel();
        Assert.assertEquals(actualResult_makeModel, car.model);

        String actualResult_displayVariantReg = homePageMotorway.getDisplayVariantReg();
        Assert.assertEquals(actualResult_displayVariantReg, car.regNo);

        String actualYear = homePageMotorway.getDisplay_Year();
        Assert.assertEquals(actualYear, car.year);

        driver.navigate().refresh();
    }

    @DataProvider(name = "cardata")
    public Object[] getcardata() {

        List<String> list = new ArrayList<>();
        list.add("AD58 VNF");//fileUtil.testReadInputFile();
        return list.toArray();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


