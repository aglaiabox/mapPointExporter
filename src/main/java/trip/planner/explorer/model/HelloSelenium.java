package trip.planner.explorer.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import trip.planner.explorer.webPages.LoginPage;

import java.time.Duration;
import java.util.Collections;


public class HelloSelenium {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/maps/d/u/0/?hl=ru");
//        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
//        loginField.sendKeys("justmashroom@gmail.com");

        new WebDriverWait(driver, Duration.ofMillis(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='identifierId']"))).sendKeys("justmashroom@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        new WebDriverWait(driver, Duration.ofMillis(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']"))).sendKeys("sp1sp27080");
        driver.findElement(By.id("passwordNext")).click();
        System.out.println(driver.getTitle());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputLogin("justmashroom@gmail.com");
        loginPage.clickLoginBtn();


//        driver.quit();
    }



}
