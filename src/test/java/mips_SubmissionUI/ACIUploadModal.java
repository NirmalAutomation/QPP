package mips_SubmissionUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pack_PageObject.*;
import resources.Base;

import java.io.IOException;

public class ACIUploadModal extends Base {

    private static Logger Log = LogManager.getLogger(LoginLogout.class.getName());

    @BeforeTest
    public void initializingBrowser() throws IOException {

        // Browser Initialization from Base Class
        BrowserInitial();
        Log.info("MIPS Application Launched and Home page displayed");
    }

    @BeforeClass
    public void loginMIPS() {
        // QPP LoginPage
        B_LoginPage l = new B_LoginPage(driver);
        Log.info("Login page displayed");

        // QPP Account Login
        l.getSignin().click();
        Log.info("Click action performed on Signin button");

    }

    @Test
    public void aciFileUploadModal() throws InterruptedException {

        // Account Dashboard
        C_AccountDashboard a = new C_AccountDashboard(driver);
        Log.info("Account Dashboard displayed");

        // Report as Group User
        Thread.sleep(1000);
        a.getGroup().click();
        Log.info("Click action performed on Group Reporting button");

        // Group Dashboard
        D_GroupDashboard g = new D_GroupDashboard(driver);
        Log.info("Group Dashboard displayed");

        // ACI Reporting Button
        g.getACIButton().click();
        Log.info("Click action performed on ACI Reporting button");

        // ACI Page displayed
        F_ACIPage ac = new F_ACIPage(driver);
        Log.info("ACI Page displayed");

        // Open File Upload Model
        ac.openFileUpload().click();
        Log.info("Opening the ACI File Upload Modal");

        // Assert the warning text on File Upload modal
        Thread.sleep(1000);

        try {
            Assert.assertEquals(ac.getDragDropTxt().getText(), "Drag & Drop\n" +
                    "Your .xml or .json file here or\n" +
                    "browse");
            Log.info("The page contains the Drag and Drop information");

        } catch (Exception e) {
            Log.error("The page doesn't contains the Drag and Drop information");
        }

        // Close Full Upload window
        Thread.sleep(1000);
        ac.closeFileUpload().click();
        Log.info("Closing the ACI File Upload Modal");
    }

    @AfterClass
    public void logoutMIPS() throws InterruptedException {

        // ACI Page displayed
        Thread.sleep(1000);
        F_ACIPage ac = new F_ACIPage(driver);
        Log.info("ACI Page displayed");

        // Logout
        ac.getMyAccount().click();
        Log.info("Click action performed on MyAccount header link");

        ac.getLogout().click();
        Log.info("Click action performed on Sign out link");

        ac.getConfirmLogout().click();
        Log.info("Sign out confirmation");

    }

    @AfterTest
    public void closingBrowser() throws InterruptedException {

        Thread.sleep(1000);
        // Browser closing
        driver.close();
        Log.info("Browser closed");
        driver = null;

    }
}
