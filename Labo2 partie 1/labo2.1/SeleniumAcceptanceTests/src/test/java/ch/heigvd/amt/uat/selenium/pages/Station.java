package ch.heigvd.amt.uat.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Station extends AbstractAMTLab1Page {

    By bNextLocator = By.id("bNext");
    By bPreviousLocator = By.id("bPrevious");
    By bPagePagination = By.id("bPage");

    public Station(WebDriver driver) {

        super(driver);

        // Check that we're on the right page.
        if (!"Station".equals(driver.getTitle())) {
            throw new IllegalStateException("Ce n'est pas la bonne page");
        }
    }

    public void testPagination(){

        //on vérifie qu'il y ait au moint un numéro de page
        if (driver.findElements(bNextLocator).isEmpty())  {
            throw new IllegalStateException("Pagination pas en marche");
        }

        int pagePaginationNumber = 1;

        //on defile les pagination vers la fin
        while(!driver.findElements(bNextLocator).isEmpty()){
            driver.findElement(bNextLocator).click();
            pagePaginationNumber++;

            String URL = driver.getCurrentUrl();
            // on recupere le numero de page de la pagination de l'url pour le comparer
            String page = URL.substring(URL.lastIndexOf("=") + 1);

            if(Integer.parseInt(page) != pagePaginationNumber) {
                throw new IllegalStateException("Pas la bonne adresse de pagination");
            }
        }

        //on defile la pagination vers le début
        while(!driver.findElements(bPreviousLocator).isEmpty()){
            driver.findElement(bPreviousLocator).click();
            pagePaginationNumber--;

            String URL = driver.getCurrentUrl();
            // on recupere le numero de page de la pagination de l'url pour le comparer
            String page = URL.substring(URL.lastIndexOf("=") + 1);

            if(Integer.parseInt(page) != pagePaginationNumber) {
                throw new IllegalStateException("Pas la bonne adresse de pagination");
            }
        }
    }


}
