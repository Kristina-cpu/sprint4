package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SamokatPage {
    WebDriver driver;

    public SamokatPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnCookie() {
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }
        public void clickOnQuestion(int number) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                    driver.findElement(By.id("accordion__heading-" + number)));
            driver.findElement(By.id("accordion__heading-" + number)).click();
    }
    public WebElement getAnswer(int number){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-"+number)));

        return driver.findElement(By.id("accordion__panel-"+number));

    }
    public void clickOnButtonOrder() {

        driver.findElement(By.className("Button_Button__ra12g")).click();
    }

    public void typeName(String name) {
        driver.findElement(By.xpath("//*[@placeholder='* Имя']")).sendKeys(name);
    }

    public void typeSurname(String surname) {
        driver.findElement(By.xpath("//*[@placeholder='* Фамилия']")).sendKeys(surname);
    }

    public void typeAdress(String adresse) {
        driver.findElement(By.xpath("//*[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(adresse);
    }

    public void clickOnMetro() {
        driver.findElement(By.xpath("//*[@placeholder='* Станция метро']")).click();
    }

    public void clickOnStreet() {
        driver.findElement(By.xpath("//div[text()='Бульвар Рокоссовского']")).click();
    }

    public void typePhoneNumber(String phoneNumber) {
        driver.findElement(By.xpath("//*[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(phoneNumber);
    }

    public void clickOnTheButttonNext() {
        driver.findElement(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM")).click();
    }

    public void clickOnDeliveryDateAndSelectionOfOrderDate() {
        driver.findElement(By.xpath("//*[@placeholder='* Когда привезти самокат']")).click();
        driver.findElement(By.xpath("//*[@aria-label='Choose четверг, 14-е сентября 2023 г.']")).click();
    }


    public void clickOnPeriodOfRental() {
        driver.findElement(By.xpath("//div[text()='* Срок аренды']")).click();
    }

    public void selectionOfTheRentalPeriod() {
        driver.findElement(By.xpath("//div[text()='сутки']")).click();
    }

    public void selectionDropdownMenu() {
        driver.findElement(By.id("black")).click();
    }

    public void typeComment(String comment) {
        driver.findElement(By.xpath("//*[@placeholder='Комментарий для курьера']")).sendKeys(comment);
    }

    public void clickOnButtonOrderTwo() {
        driver.findElement(By.xpath("(//*[text()='Заказать'])[2]")).click();
    }

    public void clickOnYes() {
        driver.findElement(By.xpath("(//*[@class='Order_Modal__YZ-d3']//button)[2]")).click();
    }
    public WebElement getLookStatusButton(){
        return driver.findElement(
                By.xpath("//button[text()='Посмотреть статус']"));
    }
}
