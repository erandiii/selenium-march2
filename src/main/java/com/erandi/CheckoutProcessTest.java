package com.erandi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutProcessTest {
    private WebDriver driver;

    /**
     * Set up the browser before each test.
     * Launches the Chrome browser, maximizes the window, and navigates to the login page.
     */
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    /**
     * Tear down the browser after each test.
     * Ensures that the browser is closed even if the test fails.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    /**
     * Test valid login with correct username and password.
     */
    @Test
    public void checkOut() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle, "Products", "Login failed: Unexpected page title");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Erandi");
        driver.findElement(By.id("last-name")).sendKeys("Wijerathne");
        driver.findElement(By.id("postal-code")).sendKeys("70400");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String finalOrder = driver.findElement(By.id("back-to-products")).getText();
        Assert.assertEquals(finalOrder, "Back Home");



    }

}
