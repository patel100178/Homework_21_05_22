package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class TestSuit {

    protected static WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        // Type URL
        driver.get("https://demo.nopcommerce.com/");

    }

    @Test
    public void userShouldBeAbleToRegisterSuccessfuly() {
        // click on register button
        clickElement(By.className("ico-register"));

        //select male and female
        driver.findElement(By.id("gender-female")).click();

        // enter firstname
        typeText(By.xpath("//*[@name='FirstName']"), "Payal");

        // enter lastname
        typeText(By.id("LastName"), "Patel");

        //Select date of birth day
        Select birthday = new Select(driver.findElement(By.name("DateOfBirthDay")));
        birthday.selectByValue("5");

        //select month
        Select birthmonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        birthmonth.selectByValue("5");

        //select year
        Select birthyear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        birthyear.selectByVisibleText("2020");

        // enter Email
        String email = "Payal" + randomDate() + "payal_jp22@yahoo.com";
        System.out.println(email);
        typeText(By.id("Email"), email);

        // enter password
        typeText(By.id("Password"), "12345@");

        // enter confirm password
        typeText(By.id("ConfirmPassword"), "12345@");

        // click register button
        clickElement(By.id("register-button"));
        String expectedMassage = "Your registration completed";
        String actualMassage = getTextFromElement(By.className("result"));
        String actualRegistrationurl = driver.getCurrentUrl();
        Assert.assertEquals(actualMassage, expectedMassage, "Registration not successful");
    }

    @Test
    public void userShouldAbleAddToCartProduct() {
        //click on a computer
        clickElement(By.partialLinkText("Computers"));

        //click on Desktop
        clickElement(By.partialLinkText("Desktops"));

        //click on Add to cart button
        clickElement(By.xpath("//button[contains(@onclick,'/addproducttocart/catalog/1/1/1')]"));

        // select from processor dropdown
        selectDropdownValue(By.xpath("//select[@name=\"product_attribute_1\"]"), "1");

        // select RAM
        selectDropdownValue(By.xpath("//select[@name=\"product_attribute_2\"]"), "3");

        // click on HDD radioButton
        clickElement(By.xpath("//input[@name=\"product_attribute_3\"and@id=\"product_attribute_3_6\"]"));

        //click on OS radiobutton
        clickElement(By.xpath("//input[@id=\"product_attribute_4_9\"and@name =\"product_attribute_4\"]"));

        // select  software checkbox all
        clickElement(By.xpath("//*[@id=\"product_attribute_5_10\"]"));
        clickElement(By.xpath("//*[@id=\"product_attribute_5_11\"]"));
        clickElement(By.xpath("//*[@id=\"product_attribute_5_12\"]"));

        // click on  Add to cart button
        clickElement(By.xpath("//*[@id=\"add-to-cart-button-1\"]"));

        // click on shopping cart button
        clickElement(By.xpath("//*[@id=\"topcartlink\"]/a/span[1]"));

        //Assertion to verify the shopping cart
        assertVerification("Shopping cart",By.linkText("Shopping cart"),"Invalid page");

        // Assertion to verify the correct product in the cart
        assertVerification("Build your own computer",By.linkText("Build your own computer"),"This is not correct product");

    }

    @Test
    public void registeredUserShouldBeAbleToReferAFriend() {
        // click on register button
        clickElement(By.className("ico-register"));

        //male and female
        driver.findElement(By.id("gender-female")).click();

        // enter firstname
        typeText(By.xpath("//*[@name='FirstName']"), "Payal");

        // enter lastname
        typeText(By.id("LastName"), "Patel");

        //Select date of birth day
        Select birthday = new Select(driver.findElement(By.name("DateOfBirthDay")));
        birthday.selectByValue("5");

        //select month
        Select birthmonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        birthmonth.selectByValue("5");

        //select year
        Select birthyear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        birthyear.selectByVisibleText("2020");

        // enter Email
        String email = "Payal" + randomDate() + "payal_jp22@yahoo.com";
        System.out.println(email);
        typeText(By.id("Email"), email);

        // enter password
        typeText(By.id("Password"), "12345@");

        // enter confirm password
        typeText(By.id("ConfirmPassword"), "12345@");

        // click register button
        clickElement(By.id("register-button"));
        String expectedMassage = "Your registration completed";
        String actualMassage = getTextFromElement(By.className("result"));
        String actualRegistrationurl = driver.getCurrentUrl();

        //click on CONTINUE button
        clickElement(By.xpath("//div[2]/a[@class=\"button-1 register-continue-button\"]"));

        //click on computer
        clickElement(By.xpath("//div[@class=\"master-wrapper-page\"]/div[2]/ul[1]/li[1]/a[@href=\"/computers\"]"));

        //click on Desktops
        clickElement(By.xpath("//ul[@class=\"sublist\"]/li[1]/a[@href=\"/desktops\"]"));

        //click Lenovo IdeaCentre 600 All-in-one PC
        clickElement(By.xpath("//h2/a[@href=\"/lenovo-ideacentre-600-all-in-one-pc\"]"));

        //click on Email a Friend
        clickElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"]"));

        //Friend's Email
        typeText(By.xpath("//input[@name=\"FriendEmail\"]"), "payal_jp22+01@gmail.com");

        //Type a massage
        typeText(By.id("PersonalMessage"), "This Product is very nice");

        //Click on Send Email
        clickElement(By.xpath("//button[@name=\"send-email\"]"));
        Assert.assertEquals(actualMassage, expectedMassage, "Your message has been sent.");

    }

    @Test
    public void userShouldAbleToSeeProductCurrencyInEuro() {

        //click Currency bar
        clickElement(By.id("customerCurrency"));

        //select currency
        Select currency = new Select(driver.findElement(By.id("customerCurrency")));
        currency.selectByVisibleText("Euro");

        //currency check
        String price = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div/div[4]/div[2]/div[1]/div/div[2]/div[3]/div[1]/span")).getText();
        System.out.println(price);
        String p = price.substring(0, 1);
        System.out.println(p);
        Assert.assertEquals(p, "€");

    }
    @AfterMethod
        public void closeBrowser() {
        driver.quit();

    }

    //public static void main(String[] args) {
    // Open Chrome browser
    //System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
    // driver = new ChromeDriver();
    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    //driver.manage().window().maximize();
    // Type URL
    //driver.get("https://demo.nopcommerce.com/");
    // click on register button
    //driver.findElement(By.className("ico-register")).click();
    // clickElement(By.className("ico-register"));
    //driverClickable(10, By.className("ico-register"));
    //driverSelected(10, By.id("gender-female"));
    //male and female
    //driver.findElement(By.id("gender-female")).click();
    // enter firstname
    //driver.findElement(By.xpath("//*[@name='FirstName']")).sendKeys("Payal");
    //typeText(By.xpath("//*[@name='FirstName']"), "Payal");
    // enter lastname
    //driver.findElement(By.id("LastName")).sendKeys("Patel");
    //typeText(By.id("LastName"), "Patel");
    //Select date of birth day
    //Select birthday = new Select(driver.findElement(By.name("DateOfBirthDay")));
    // birthday.selectByValue("5");
    //select month
    //Select birthmonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
    //birthmonth.selectByValue("5");
    //select year
    //Select birthyear = new Select(driver.findElement(By.name("DateOfBirthYear")));
    //birthyear.selectByVisibleText("2020");
    // enter Email
    //driver.findElement(By.id("Email")).sendKeys("payal_jp22@yahoo.com");
    //String email = "Payal" + randomDate() + "payal_jp22@yahoo.com";
    //System.out.println(email);
    //typeText(By.id("Email"), email);

    // enter password
    //driver.findElement(By.id("Password")).sendKeys("12345@");
    //typeText(By.id("Password"), "12345@");
    // enter confirm password
    //driver.findElement(By.id("ConfirmPassword")).sendKeys("12345@");
    //typeText(By.id("ConfirmPassword"), "12345@");
    // click register button
    //driver.findElement(By.id("register-button")).click();
    //clickElement(By.id("register-button"));
    //String expectedMassage = "Your registration completed";
    //String actualMassage = getTextFromElement(By.className("result"));
    //System.out.println("Actual Massage: " + actualMassage);
    //if (expectedMassage.equals(actualMassage)) {
    // System.out.println("Your registration is successful");
    // } else {
    // System.out.println("Your registration is not successful");
    //}
    //String actualRegistrationurl= driver.getCurrentUrl();
    //Assert.assertEquals(actualMassage,expectedMassage,"Registration not successful");

    //driver.quit();
    //verify user is on correct registration page
    //driverWaitsUntilURLTobe( 20, "https://demo.nopcommerce.com/registerresult/1?returnUrl=/");

    // driverTitleContains(10, "nopCommerce demo store");
    //driverElementToBeSelected(10, By.partialLinkText("/fahrenheit-451-by-ray-bradbury"));
    //driverAlertIsPresent(10);
    //}
//---------------// Utility/User defined method--------------------------------//
    public static void driverWaitsUntilURLTobe(int time, String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public static void clickElement(By by) {
        driver.findElement(by).click();
    }

    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static void selectDropdownText(By by, String textValue) {
        Select dropdown1 = new Select(driver.findElement(by));
        dropdown1.selectByVisibleText(textValue);
    }

    public static void selectDropdownIndex(By by, int index) {
        Select dropdown2 = new Select(driver.findElement(by));
        dropdown2.selectByIndex(index);
    }

    public static void selectDropdownValue(By by, String value) {
        Select dropdown3 = new Select(driver.findElement(by));
        dropdown3.selectByValue(value);
    }

    public static String randomDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyymmss");
        return formatter.format(date);
    }

    public static void driverClickable(int time, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public static void driverSelected(int time, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void driverTitleContains(int time, String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void driverInvisibility(int time, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void driverAlertIsPresent(int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void assertVerification(String expected, By by,String message) {
        String expectedResult = driver.findElement(by).getText();
        String actualResult = expected;
        Assert.assertEquals(expectedResult, actualResult, message);

    }

    public static void driverTextToBe(int time, By by, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBe(by, value));
    }

    public static void driverInvisibilityOf(int time, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void driverAttributeToBe(int time, By by, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
    }

    public static void driverAttributeToBeNotEmpty(int time, WebElement element, String attribute) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    public static void driverElementToBeSelected(int time, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }

}