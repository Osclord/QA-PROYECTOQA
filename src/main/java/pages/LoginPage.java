package pages;

//import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.TestHelpers;

public class LoginPage {
    private WebDriver driver;
    private TestHelpers testHelpers;
    
    // Locators
    private By usernameField = By.name("TxtUsuario");
    private By passwordField = By.name("TxtClave");
    private By loginButton = By.xpath("//*[@id=\"BtnIngresar\"]");
    private By registerLink = By.xpath("//*[@id=\"sidebar\"]/div/p/a");
    private By errorMessage = By.className("dxeBase error");

    
    // Registration form locators
    private By regEmailField = By.name("TxtCorreo");
    private By regPasswordField = By.name("TxtClave");
    //private By regConfirmPasswordField = By.name("confirm_password");
    private By regNameField = By.name("TxtNit");
    private By regSectorDropdown = By.name("LstSector");
    private By regSubmitButton = By.xpath("//*[@id=\"BtnRegistrar\"]");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.testHelpers = new TestHelpers(driver);
    }
    
    public void goToLoginPage() {
        driver.get(ConfigReader.getProperty("base.url"));
    }
    
    public void enterUsername(String username) {
        testHelpers.sendKeys(usernameField, username);
    }
    
    public void enterPassword(String password) {
        testHelpers.sendKeys(passwordField, password);
    }
    
    public void clickLoginButton() {
        testHelpers.clickElement(loginButton);
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    public boolean isErrorMessageDisplayed() {
        return testHelpers.isElementPresent(errorMessage);
    }
    
    public void clickRegisterLink() {
        testHelpers.clickElement(registerLink);
    }
    
    public void registerNewUser(String name, String email, String password, String sector) {
        clickRegisterLink();
        testHelpers.sendKeys(regNameField, name);
        testHelpers.sendKeys(regEmailField, email);
        testHelpers.sendKeys(regPasswordField, password);
        //testHelpers.sendKeys(regConfirmPasswordField, password);
        testHelpers.selectDropdownByText(regSectorDropdown, sector);
        testHelpers.clickElement(regSubmitButton);
    }
    
    public void loginAsAdmin() {
        driver.get(ConfigReader.getProperty("admin.url"));
        login(ConfigReader.getProperty("admin.username"), 
              ConfigReader.getProperty("admin.password"));
    }
    
    public void loginAsTestUser() {
        goToLoginPage();
        login(ConfigReader.getProperty("test.username"), 
              ConfigReader.getProperty("test.password"));
    }
}