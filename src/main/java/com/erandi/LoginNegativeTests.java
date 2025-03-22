package com.erandi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginNegativeTests {
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
    public void testValidLogin() {
        enterCredentials("standard_user", "secret_sauce");
        clickLoginButton();

        String actualTitle = getPageTitle();
        Assert.assertEquals(actualTitle, "Products", "Login failed: Unexpected page title.");
    }

    /**
     *Invalid username, correct password
     */
    @Test
    public void testLoginWithInvalidUsername() {
        enterCredentials("xxx", "secret_sauce");
        clickLoginButton();

        String errorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    /**
     *Correct username, invalid password
     */
    @Test
    public void testLoginWithInvalidPassword() {
        enterCredentials("standard_user", "vvv");
        clickLoginButton();

        String errorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    /**
     *Invalid username and password
     */
    @Test
    public void testLoginWithInvalidUsernameAndPassword() {
        enterCredentials("standard", "vvv");
        clickLoginButton();

        String errorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    /**
     * Test login with a blank username.
     */
    @Test
    public void testLoginWithBlankUsername() {
        enterCredentials("", "secret_sauce");
        clickLoginButton();

        String errorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @DataProvider(name = "user-credentials")
    public Object[][] userCredentials() {
        return new Object[][] {
                {"", "", "Epic sadface: Username is required"},
                {"", "password", "Epic sadface: Username is required"},
                {"standard_user", "","Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "user-credentials")
    public void testInvalidLoginScenarioWithDDT(String username, String password, String expectedError) {
//        driver.findElement(By.id("user-name")).sendKeys();
//        driver.findElement(By.id("password")).sendKeys();
//        driver.findElement(By.id("login-button")).sendKeys();

        enterCredentials(username, password);
        clickLoginButton();
        Assert.assertEquals(getErrorMessage(), expectedError);
    }
    /**
     * Test login with a blank password.
     */
    @Test
    public void testLoginWithBlankPassword() {
        enterCredentials("standard_user", "");
        clickLoginButton();

        String errorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    /**
     *Special characters in username/password
     */
    @Test
    public void testLoginWithSpecialCharacterOfUsername() {
        enterCredentials("standard!", "secret_sauce");
        clickLoginButton();

        String errorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    /**
     *Very long username/password
     */
    @Test
    public void testLoginWithLongUsername() {
        enterCredentials("standarddddddddddddddddddddddddddddddddddddd", "secret_sauce");
        clickLoginButton();

        String errorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    /**
     * Enters the given username and password into the login form.
     *
     * @param username The username to enter.
     * @param password The password to enter.
     */
    private void enterCredentials(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    private void clickLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    /**
     * Gets the title of the current page after login.
     *
     * @return The page title as a string.
     */
    private String getPageTitle() {
        return driver.findElement(By.cssSelector(".title")).getText();
    }

    /**
     * Retrieves the displayed error message.
     *
     * @return The error message as a string.
     */
    private String getErrorMessage() {
        return driver.findElement(By.cssSelector("[data-test='error']")).getText();
    }

}
