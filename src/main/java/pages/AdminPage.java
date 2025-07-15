package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestHelpers;

public class AdminPage {

    private WebDriver driver;
    private TestHelpers testHelpers;

    // Menu locators
    private By sectorsMenu = By.xpath("//a[contains(text(),'Sectores')]");
    private By temasMenu = By.xpath("//a[contains(text(),'Temas')]");
    private By subtemasMenu = By.xpath("//a[contains(text(),'Subtemas')]");
    private By preguntasMenu = By.xpath("//a[contains(text(),'Preguntas')]");

    // Form locators
    private By createButton = By.xpath("//button[contains(text(),'Crear')]");
    private By nameField = By.name("name");
    private By descriptionField = By.name("description");
    private By sectorDropdown = By.name("sector_id");
    private By temaDropdown = By.name("tema_id");
    private By subtemaDropdown = By.name("subtema_id");
    private By questionField = By.name("question");
    private By saveButton = By.xpath("//button[contains(text(),'Guardar')]");
    private By successMessage = By.className("success-message");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.testHelpers = new TestHelpers(driver);
    }

    public void goToSectorsSection() {
        testHelpers.clickElement(sectorsMenu);
    }

    public void goToTemasSection() {
        testHelpers.clickElement(temasMenu);
    }

    public void goToSubtemasSection() {
        testHelpers.clickElement(subtemasMenu);
    }

    public void goToPreguntasSection() {
        testHelpers.clickElement(preguntasMenu);
    }

    public void clickCreateButton() {
        testHelpers.clickElement(createButton);
    }

    public void enterName(String name) {
        testHelpers.sendKeys(nameField, name);
    }

    public void enterDescription(String description) {
        testHelpers.sendKeys(descriptionField, description);
    }

    public void selectSector(String sector) {
        testHelpers.selectDropdownByText(sectorDropdown, sector);
    }

    public void selectTema(String tema) {
        testHelpers.selectDropdownByText(temaDropdown, tema);
    }

    public void selectSubtema(String subtema) {
        testHelpers.selectDropdownByText(subtemaDropdown, subtema);
    }

    public void enterQuestion(String question) {
        testHelpers.sendKeys(questionField, question);
    }

    public void clickSaveButton() {
        testHelpers.clickElement(saveButton);
    }

    public void createSector(String name, String description) {
        goToSectorsSection();
        clickCreateButton();
        enterName(name);
        enterDescription(description);
        clickSaveButton();
    }

    public void createTema(String name, String description, String sector) {
        goToTemasSection();
        clickCreateButton();
        enterName(name);
        enterDescription(description);
        selectSector(sector);
        clickSaveButton();
    }

    public void createSubtema(String name, String description, String tema) {
        goToSubtemasSection();
        clickCreateButton();
        enterName(name);
        enterDescription(description);
        selectTema(tema);
        clickSaveButton();
    }

    public void createPregunta(String question, String subtema) {
        goToPreguntasSection();
        clickCreateButton();
        enterQuestion(question);
        selectSubtema(subtema);
        clickSaveButton();
    }

    public boolean isSuccessMessageDisplayed() {
        return testHelpers.isElementPresent(successMessage);
    }
}