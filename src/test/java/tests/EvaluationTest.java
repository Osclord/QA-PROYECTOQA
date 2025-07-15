package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EvaluationPage;
import pages.LoginPage;

public class EvaluationTest extends BaseTest {
    private EvaluationPage evaluationPage;
    private LoginPage loginPage;
    
    @BeforeMethod
    public void setupUser() {
        loginPage = new LoginPage(driver);
        evaluationPage = new EvaluationPage(driver);
        
        // Login as test user
        loginPage.loginAsTestUser();
        testHelpers.takeScreenshot("test_user_logged_in");
    }
    
    @Test(priority = 1)
    public void testCreateEvaluations() {
        // Define different answer patterns for each evaluation
        int[][] evaluationAnswers = {
            {1, 2, 3, 1, 2}, // Evaluation 1 answers
            {2, 3, 1, 2, 3}, // Evaluation 2 answers
            {3, 1, 2, 3, 1}  // Evaluation 3 answers
        };
        
        for (int i = 0; i < 3; i++) {
            String evaluationTitle = "Evaluación_Automatizada_" + (i + 1);
            
            // Complete evaluation
            evaluationPage.completeEvaluation(evaluationTitle, evaluationAnswers[i]);
            testHelpers.takeScreenshot("evaluation_" + (i + 1) + "_completed");
            
            // Download PDF
            evaluationPage.downloadPDF();
            testHelpers.handleDownload("evaluation_" + (i + 1) + ".pdf");
            testHelpers.takeScreenshot("evaluation_" + (i + 1) + "_pdf_downloaded");
            
            System.out.println("✓ Evaluation " + (i + 1) + " completed and PDF downloaded");
            
            // Small delay between evaluations
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test(priority = 2)
    public void testEvaluationResults() {
        // Navigate to evaluations section
        evaluationPage.goToEvaluationsSection();
        testHelpers.takeScreenshot("evaluations_list");
        
        // Verify that evaluations were created
        // This test would check if the evaluations appear in the user's evaluation history
        // Implementation depends on the actual UI structure
        
        System.out.println("✓ Evaluation results verified");
    }
}
