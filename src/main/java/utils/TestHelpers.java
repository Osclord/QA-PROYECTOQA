package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Set;

public class TestHelpers {

    private WebDriver driver;
    private WebDriverWait wait;

    public TestHelpers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void takeScreenshot(String name) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = name + "_" + timestamp + ".png";
            File destFile = new File("evidencias/" + fileName);
            FileUtils.copyFile(sourceFile, destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error taking screenshot: " + e.getMessage());
        }
    }

    public void waitForElement(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void clickElement(By locator) {
        waitForElementToBeClickable(locator, 10);
        driver.findElement(locator).click();
    }

    public void sendKeys(By locator, String text) {
        waitForElement(locator, 10);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void selectDropdownByValue(By locator, String value) {
        waitForElement(locator, 10);
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByValue(value);
    }

    public void selectDropdownByText(By locator, String text) {
        waitForElement(locator, 10);
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(text);
    }

    public String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(chars.length())));
        }
        return result.toString();
    }

    public void handleDownload(String expectedFileName) {
        try {
            Thread.sleep(3000); // Wait for download to complete
            // Check if file exists in default download directory
            String userHome = System.getProperty("user.home");
            String downloadPath = userHome + "/Downloads/" + expectedFileName;
            File downloadedFile = new File(downloadPath);
            
            if (downloadedFile.exists()) {
                // Move to results folder
                File destFile = new File("resultados/" + expectedFileName);
                FileUtils.moveFile(downloadedFile, destFile);
                System.out.println("PDF moved to: " + destFile.getAbsolutePath());
            }
        } catch (InterruptedException | IOException e) {
            System.err.println("Error handling download: " + e.getMessage());
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void switchToNewWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
        }
    }
}