package com.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class crossBrowserTest {
    private WebDriver driver;

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
     * Set up the browser before each test.
     * Launches the Chrome browser, maximizes the window, and navigates to the login page.
     */
    @Test
    public void loginWithChrome() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle, "Products", "Login failed: Unexpected page title");
    }
    @Test
    public void loginWithChromeHeadless() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle, "Products", "Login failed: Unexpected page title");
    }

    @Test
    public void loginWithSafari() {
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle, "Products","Login failed: Unexpected page title");
    }


}
