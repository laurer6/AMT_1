package ch.heigvd.amt.uat.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage  extends AbstractAMTLab1Page {

    By bLoginLocator = By.id("bLogin");

    public HomePage(WebDriver driver) {
        super(driver);

        if (!"Home".equals(driver.getTitle())) {
            throw new IllegalStateException("Ce n'est pas la bonne page");
        }
    }

    public void loginPasProposee(){

        boolean eleSelected;

        if(!driver.findElements(bLoginLocator).isEmpty()){
            eleSelected = true;
        }else{
            eleSelected = false;
        }

        if (eleSelected)  {
            throw new IllegalStateException("login should not be propose");
        }
    }

}
