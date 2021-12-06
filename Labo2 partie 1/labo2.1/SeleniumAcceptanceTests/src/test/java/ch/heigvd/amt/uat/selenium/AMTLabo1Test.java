package ch.heigvd.amt.uat.selenium;

import ch.heigvd.amt.uat.selenium.pages.HomePage;
import ch.heigvd.amt.uat.selenium.pages.LoginPage;
import ch.heigvd.amt.uat.selenium.pages.Station;
import ch.heigvd.amt.uat.selenium.pages.UserPage;
import io.probedock.client.annotations.ProbeTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Laurent Tailhades
 */
public class AMTLabo1Test {

  private String baseUrl = "http://localhost:8080/loginTest-1.0-SNAPSHOT";
  private WebDriver driver;

  @Before
  public void openBrowser() {

    System.setProperty("webdriver.chrome.driver", "Applications/chromedriver.exe");
    driver = new ChromeDriver();
  }


  @Test
  @ProbeTest(tags = "WebUI")
  public void onNePeutPasSeconnecterAvecUnMauvaisID() {
      driver.get(baseUrl + "/login");
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("admin");
    loginPage.typePassword("admin");
    loginPage.submitFormExpectingFailure();
  }
  
 @Test
  @ProbeTest(tags = "WebUI")
  public void connectionReussiRedirigeVersUserInfo() {
     driver.get(baseUrl + "/login");
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("Rowan");
    loginPage.typePassword("c3436c707220a8cd9ba4cfd80ea884bf");
    UserPage homePage = (UserPage)loginPage.submitForm(UserPage.class);
  }


    @Test
    @ProbeTest(tags = "WebUI")
    public void laPaginationDevraitAfficherToutLesEmplacement() {
        driver.get(baseUrl + "/stationEtEmplacment");
        Station stationPage = new Station(driver);

        stationPage.testPagination();
    }


   @Test
   @ProbeTest(tags = "WebUI")
   public void laPageDeLoginNeDevraitPasEtreProposee() {
       driver.get(baseUrl + "/login");
       LoginPage loginPage = new LoginPage(driver);
       loginPage.typeEmailAddress("Rowan");
       loginPage.typePassword("c3436c707220a8cd9ba4cfd80ea884bf");
       loginPage.loginPasProposee();
   }

    @Test
    @ProbeTest(tags = "WebUI")
    public void laPageDeLoginNeDevraitPasEtreProposeeDansLeHome() {
        driver.get(baseUrl + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeEmailAddress("Rowan");
        loginPage.typePassword("c3436c707220a8cd9ba4cfd80ea884bf");
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        homepage.loginPasProposee();
    }


  @After
  public void closeBrowser() {
    driver.close();
  }



}
