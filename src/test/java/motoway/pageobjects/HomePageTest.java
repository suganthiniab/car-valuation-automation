package motoway.pageobjects;

import motorway.pageobjects.HomePage;
import motorway.util.BrowserManager;
import motoway.pageobjects.util.FileUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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
        try {
            HomePage homePageMotorway = new HomePage(driver);
            homePageMotorway.inputRegistrationNumber(registrationNumber);
            homePageMotorway.clickValueYourCar();


            Assert.assertNotNull(car, "The Car not found in the website");
            String actualResult_makeModel = homePageMotorway.getLabel_displayMakeModel();
            Assert.assertEquals(actualResult_makeModel, car.model);

            String actualResult_displayVariantReg = homePageMotorway.getDisplayVariantReg();
            Assert.assertNotNull(actualResult_displayVariantReg, "registration number null");
            Assert.assertEquals(actualResult_displayVariantReg.replace(" ", ""), car.regNo);

            String actualYear = homePageMotorway.getDisplay_Year();
            Assert.assertEquals(actualYear, car.year);
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }

    }

    @DataProvider(name = "cardata")
    public Object[] getcardata() {
        List<String> list = fileUtil.testReadInputFile();
        return list.toArray();
    }

    @AfterMethod
    public void afterMethod()
    {
        driver.navigate().refresh();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


