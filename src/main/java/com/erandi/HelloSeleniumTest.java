package com.erandi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloSeleniumTest {

    @Test
    public void testHelloSelenium(){

        //open/launching the web browser
        WebDriver driver = new ChromeDriver();
        //Navigate to the source login page
        driver.get("https://www.saucedemo.com/");
        //type username
        WebElement txtUserName = driver.findElement(By.id("user-name"));
        txtUserName.sendKeys("standard_user");
        txtUserName.clear();
        //type pwd
        WebElement txtPassword = driver.findElement(By.id("password"));
        txtPassword.sendKeys(("secret_sauce"));
        //click login button
        WebElement btnSubmit = driver.findElement((By.id("login-button")));
        btnSubmit.click();
        //verification
        //Assert.assertEquals(driver.findElement(By.cssSelector("title")).getText(), "Products");
        Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(), "Products");
        //close the browser
        driver.quit();
    }

    @Test
    public void testHelloSeleniumNegative(){

        //open/launching the web browser
        WebDriver driver = new ChromeDriver();
        //Navigate to the source login page
        driver.get("https://www.saucedemo.com/");
        //type username
        WebElement txtUserName = driver.findElement(By.id("user-name"));
        txtUserName.sendKeys("standard_user");
        txtUserName.clear();
        //type pwd
        WebElement txtPassword = driver.findElement(By.id("password"));
        txtPassword.sendKeys(("secret_sauce"));
        //click login button
        WebElement btnSubmit = driver.findElement((By.id("login-button")));
        btnSubmit.click();
        //verify the error message
        //Assert.assertEquals(driver.findElement(By.cssSelector("title")).getText(), "Products");
        Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(), "Products");
        //close the browser
        driver.quit();
    }






}
