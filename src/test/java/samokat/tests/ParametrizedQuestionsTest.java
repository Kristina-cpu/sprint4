package samokat.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.pages.SamokatPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

@RunWith(Parameterized.class)
public class ParametrizedQuestionsTest{

    private WebDriver driver;

    private final int questionNumber;
    private final int answerNumber;

    //private final String browserType;
    @Before
    public void setup () throws IOException {
        String appConfigPath = "app.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
        String browserType = appProps.getProperty("browser");

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe"); // Setting system properties of FirefoxDriver

        if (browserType.equals("chrome")){
            driver = new ChromeDriver();
        }else if (browserType.equals("firefox")){
            driver = new FirefoxDriver();
        }else {
            throw new UnsupportedOperationException("не задан браузер");
        }
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, 0 },{ 1, 1 },{ 2, 2 }
        });
    }

    public ParametrizedQuestionsTest(int questionNumber, int answerNumber) {
        this.questionNumber = questionNumber;
        this.answerNumber = answerNumber;
    }

    @Test
    public void testSamokat1() throws InterruptedException {
        SamokatPage samokatPage = new SamokatPage(driver);
        samokatPage.clickOnCookie();
        samokatPage.clickOnQuestion(questionNumber);
        Assert.assertTrue(samokatPage.getAnswer(answerNumber).isDisplayed());

    }
}
