package ch.heigvd.amt.uat.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the "User info" page
 *
 * @author Laurent Tailhades
 */
public class UserPage extends AbstractAMTLab1Page {

  public UserPage(WebDriver driver) {
    super(driver);

    if (!"User Info".equals(driver.getTitle())) {
      throw new IllegalStateException("Ce n'est pas la bonne page");
    }
  }
}
