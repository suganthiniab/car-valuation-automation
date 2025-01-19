package motoway.pageobjects;

import motorway.pageobjects.HomePage;
import motorway.util.BrowserManager;
import motoway.pageobjects.util.FileUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Random;


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



            if (homePageMotorway.isCarFound()) {

                String actualResult_makeModel = homePageMotorway.getLabel_displayMakeModel();
                Assert.assertEquals(actualResult_makeModel, car.model);

                String actualResult_displayVariantReg = homePageMotorway.getDisplayVariantReg();
                Assert.assertNotNull(actualResult_displayVariantReg, "registration number null");
                Assert.assertEquals(actualResult_displayVariantReg.replace(" ", ""), car.regNo);

                String actualYear = homePageMotorway.getDisplay_Year();
                Assert.assertEquals(actualYear, car.year);

                Random random = new Random();
                int randomNumber = 10000 + random.nextInt(90001);

                homePageMotorway.enterMileage(""+ randomNumber);
                homePageMotorway.clickConfirmMileage();

            } else{
                Reporter.log("This test is skipped due to unmet precondition." + registrationNumber + " Not found  ", true);
                throw new SkipException("Skipping this test as a precondition failed."+ registrationNumber + " Not found  ");
            }



    }

    @DataProvider(name = "cardatatemp")
    public Object[] getcardata() {
        List<String> list = fileUtil.testReadInputFile();
        return list.toArray();
    }

    @DataProvider(name = "cardata")
    public Object[] getcardata1() {
        Object[] b = {"SG18HTN"};
       return b;
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


