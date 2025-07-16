package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginRegisterTest extends BaseTest {
    
    @Test(priority = 1)
    public void testLoginWithNonExistentUser() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Navigate to login page
        loginPage.goToLoginPage();
        testHelpers.takeScreenshot("initial_login_page");
        
        // Try to login with non-existent user
        loginPage.login("yosdoy@test.com", "password123");
        testHelpers.takeScreenshot("login_attempt_failed");
        
        // Verify login failed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should be displayed for invalid login");
        
        System.out.println("✓ Login with non-existent user failed as expected");
    }
    
    @Test(priority = 2)
    public void testUserRegistration() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Navigate to login page
        loginPage.goToLoginPage();
        
        // Generate random user data
        String randomName = "TestUser_" + testHelpers.generateRandomString(5);
        String randomEmail = "testuser_" + testHelpers.generateRandomString(5) + "@test.com";
        String password = "password123";
        String sector = ConfigReader.getProperty("test.sector");
        
        // Register new user
        loginPage.registerNewUser(randomName, randomEmail, password, sector);
        testHelpers.takeScreenshot("user_registered");
        
        // Verify registration was successful (adjust based on actual behavior)
        // This might redirect to dashboard or show success message
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard") || 
                         driver.getCurrentUrl().contains("home"),
                         "User should be redirected after successful registration");
        
        System.out.println("✓ User registration completed successfully");
        System.out.println("Registered user: " + randomEmail);
    }
}