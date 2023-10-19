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

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParametrizedQuestionsTest{

    private WebDriver driver;

    private final int questionNumber;
    private final int answerNumber;

    private final String browserType;
    @Before
    public void setup () {
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
                {"firefox", 0, 0 }, { "firefox",1, 1 }
                ,{"chrome", 0, 0 },{"chrome", 1, 1 }
        });
    }



    public ParametrizedQuestionsTest(String browserType,int questionNumber,
                                  int answerNumber) {
        this.browserType = browserType;
        this.questionNumber = questionNumber;
        this.answerNumber = answerNumber;
    }


    @Test
    public void testSamokat1() throws InterruptedException {
        // драйвер для браузера Chrome
        SamokatPage samokatPage = new SamokatPage(driver);
        samokatPage.clickOnCookie();
        samokatPage.clickOnQuestion(questionNumber);
//        Thread.sleep(500);
        Assert.assertTrue(samokatPage.getAnswer(answerNumber).isDisplayed());

    }
}
