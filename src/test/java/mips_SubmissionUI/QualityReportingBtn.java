package mips_SubmissionUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pack_PageObject.B_LoginPage;
import pack_PageObject.C_AccountDashboard;
import pack_PageObject.D_GroupDashboard;
import pack_PageObject.E_QualityPage;
import resources.Base;

import java.io.IOException;

public class QualityReportingBtn extends Base{

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
    public void qualityReportingBtn() throws InterruptedException {

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

        // Quality Reporting Button
        g.getQualityButton().click();
        Log.info("Click action performed on Quality Reporting button");

        // Quality Page displayed
        E_QualityPage q = new E_QualityPage(driver);
        Log.info("Quality Page displayed");

        // Verify the text "Quality" presented in the page
        try {
            Assert.assertEquals(q.assertQualityTxt().getText(), "Quality");
            Log.info("The page contains the text Quality");

        } catch (Exception e) {
            Log.error("The page doesn't contains the text Quality");
        }

    }

    @AfterClass
    public void logoutMIPS() {

        // Quality Page displayed
        E_QualityPage q = new E_QualityPage(driver);
        Log.info("Quality Page displayed");

        // Logout
        q.getMyAccount().click();
        Log.info("Click action performed on MyAccount header link");

        q.getLogout().click();
        Log.info("Click action performed on Sign out link");

        q.getConfirmLogout().click();
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