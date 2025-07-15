package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;

public class AdminCreationTest extends BaseTest {
    private AdminPage adminPage;
    private LoginPage loginPage;
    
    @BeforeMethod
    public void setupAdmin() {
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        
        // Login as admin
        loginPage.loginAsAdmin();
        testHelpers.takeScreenshot("admin_logged_in");
    }
    
    @Test(priority = 1)
    public void testCreateSectors() {
        String[] sectors = {"Sector_Automatizado_1", "Sector_Automatizado_2", "Sector_Automatizado_3"};
        
        for (String sector : sectors) {
            adminPage.createSector(sector, "Descripción del " + sector);
            testHelpers.takeScreenshot("sector_created_" + sector);
            
            // Verify sector was created (adjust based on actual behavior)
            Assert.assertTrue(adminPage.isSuccessMessageDisplayed(), 
                             "Success message should appear after creating sector");
            
            System.out.println("✓ Sector created: " + sector);
        }
    }
    
    @Test(priority = 2, dependsOnMethods = {"testCreateSectors"})
    public void testCreateTemas() {
        String[] temas = {"Tema_Automatizado_1", "Tema_Automatizado_2", "Tema_Automatizado_3"};
        
        for (String tema : temas) {
            adminPage.createTema(tema, "Descripción del " + tema, "Sector_Automatizado_1");
            testHelpers.takeScreenshot("tema_created_" + tema);
            
            Assert.assertTrue(adminPage.isSuccessMessageDisplayed(), 
                             "Success message should appear after creating tema");
            
            System.out.println("✓ Tema created: " + tema);
        }
    }
    
    @Test(priority = 3, dependsOnMethods = {"testCreateTemas"})
    public void testCreateSubtemas() {
        String[] subtemas = {"Subtema_Automatizado_1", "Subtema_Automatizado_2", "Subtema_Automatizado_3"};
        
        for (String subtema : subtemas) {
            adminPage.createSubtema(subtema, "Descripción del " + subtema, "Tema_Automatizado_1");
            testHelpers.takeScreenshot("subtema_created_" + subtema);
            
            Assert.assertTrue(adminPage.isSuccessMessageDisplayed(), 
                             "Success message should appear after creating subtema");
            
            System.out.println("✓ Subtema created: " + subtema);
        }
    }
    
    @Test(priority = 4, dependsOnMethods = {"testCreateSubtemas"})
    public void testCreatePreguntas() {
        String[] preguntas = {
            "¿Pregunta de prueba automatizada 1?",
            "¿Pregunta de prueba automatizada 2?",
            "¿Pregunta de prueba automatizada 3?"
        };
        
        for (String pregunta : preguntas) {
            adminPage.createPregunta(pregunta, "Subtema_Automatizado_1");
            testHelpers.takeScreenshot("pregunta_created");
            
            Assert.assertTrue(adminPage.isSuccessMessageDisplayed(), 
                             "Success message should appear after creating pregunta");
            
            System.out.println("✓ Pregunta created: " + pregunta);
        }
    }
}