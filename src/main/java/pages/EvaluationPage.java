package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestHelpers;

public class EvaluationPage {

    private WebDriver driver;
    private TestHelpers testHelpers;

    // Locators
    private By evaluationMenu = By.xpath("//a[contains(text(),'Evaluaciones')]");
    private By createEvaluationButton = By.xpath("//button[contains(text(),'Nueva Evaluación')]");
    private By startEvaluationButton = By.xpath("//button[contains(text(),'Iniciar Evaluación')]");
    private By questionContainer = By.className("question-container");
    private By answerOption1 = By.xpath("//input[@type='radio' and @value='1']");
    private By answerOption2 = By.xpath("//input[@type='radio' and @value='2']");
    private By answerOption3 = By.xpath("//input[@type='radio' and @value='3']");
    private By nextButton = By.xpath("//button[contains(text(),'Siguiente')]");
    private By submitButton = By.xpath("//button[contains(text(),'Finalizar')]");
    private By downloadPdfButton = By.xpath("//button[contains(text(),'Descargar PDF')]");
    private By evaluationTitle = By.name("title");

    public EvaluationPage(WebDriver driver) {
        this.driver = driver;
        this.testHelpers = new TestHelpers(driver);
    }

    public void goToEvaluationsSection() {
        testHelpers.clickElement(evaluationMenu);
    }

    public void clickCreateEvaluation() {
        testHelpers.clickElement(createEvaluationButton);
    }

    public void enterEvaluationTitle(String title) {
        testHelpers.sendKeys(evaluationTitle, title);
    }

    public void startEvaluation() {
        testHelpers.clickElement(startEvaluationButton);
    }

    public void selectAnswer(int answerNumber) {
        switch (answerNumber) {
            case 1:
                testHelpers.clickElement(answerOption1);
                break;
            case 2:
                testHelpers.clickElement(answerOption2);
                break;
            case 3:
                testHelpers.clickElement(answerOption3);
                break;
            default:
                testHelpers.clickElement(answerOption1);
        }
    }

    public void clickNext() {
        testHelpers.clickElement(nextButton);
    }

    public void submitEvaluation() {
        testHelpers.clickElement(submitButton);
    }

    public void downloadPDF() {
        testHelpers.clickElement(downloadPdfButton);
    }

    public void completeEvaluation(String title, int[] answers) {
        goToEvaluationsSection();
        clickCreateEvaluation();
        enterEvaluationTitle(title);
        startEvaluation();

        for (int i = 0; i < answers.length; i++) {
            selectAnswer(answers[i]);
            if (i < answers.length - 1) {
                clickNext();
            }
        }

        submitEvaluation();
    }

    public boolean isQuestionDisplayed() {
        return testHelpers.isElementPresent(questionContainer);
    }
}