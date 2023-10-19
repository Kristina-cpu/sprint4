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
public class ParametrizedOrderTest {

    private WebDriver driver;

    private final String browserType;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String comment;

    public ParametrizedOrderTest(String browserType, String name, String surname, String address,
                                 String phoneNumber, String comment) {
        this.browserType = browserType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"firefox", "Леон", "Сатурн","Город Санкт-Петербург" ,"79657789632","Hello" },
                {"chrome", "Леон", "Сатурн","Город Санкт-Петербург" ,"79657789632","Hello" }

        });
    }



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
    @Test
    public void order() throws InterruptedException {
        SamokatPage samokatPage = new SamokatPage(driver);
        samokatPage.clickOnButtonOrder();
        //клик на кнопку заказать
        samokatPage.clickOnButtonOrder();
        //клик на всплывающее окно
        samokatPage.clickOnCookie();

        //ввод имени в поле
        samokatPage.typeName(name); // "Леон"
        //Thread.sleep(2000);
        //ввод фамилии в поле
        samokatPage.typeSurname(surname); // "Сатурн"

        //ввод адреса в поле
        samokatPage.typeAdress(address); // "Город Санкт-Петербург"
        //ввод метро в поле
        samokatPage.clickOnMetro();
        //ввод улицы
        samokatPage.clickOnStreet();
        //ввод номера телефона в поле
        samokatPage.typePhoneNumber(phoneNumber); //  "79657789632"
        //кликнуть по кнопке далее
        samokatPage.clickOnTheButttonNext();
        //дата доставки  //когда привезти самокат
        samokatPage.clickOnDeliveryDateAndSelectionOfOrderDate();

        //срок аренды
        samokatPage.clickOnPeriodOfRental();
        //выбор срока аренды
        samokatPage.selectionOfTheRentalPeriod();
        //dropdown menu
        samokatPage.selectionDropdownMenu();

        //comment
        samokatPage.typeComment(comment); // "Hello"
        //заказать
        samokatPage.clickOnButtonOrderTwo();
        // yes
        samokatPage.clickOnYes();
        Assert.assertTrue(samokatPage.getLookStatusButton().isDisplayed());
    }
}
