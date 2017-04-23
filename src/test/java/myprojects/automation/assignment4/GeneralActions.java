package myprojects.automation.assignment4;


import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import myprojects.automation.assignment4.utils.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        CustomReporter.log("Login as user: " + login);
        driver.get(Properties.getBaseAdminUrl());
        driver.navigate().to(Properties.getBaseAdminUrl());
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("main")));
        throw new UnsupportedOperationException();
    }
    public void createProduct(ProductData newProduct) {

        driver.findElement(By.id("subtab-AdminParentOrders")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("products-catalog")));

        driver.findElement(By.xpath("//span[@class='title']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("form")));

        driver.findElement(By.id("form_step1_name_1")).sendKeys(newProduct.getName());
        driver.findElement(By.id("form_step1_qty_0_shortcut")).sendKeys(newProduct.getQty().toString());
        driver.findElement(By.id("form_step1_price_shortcut")).sendKeys(newProduct.getPrice());
        driver.findElement(By.xpath("//div[@class='switch-input']")).click();

        waitForAlert();

        driver.findElement(By.xpath("//button[@class='btn btn-primary js-btn-save']")).click();

        waitForAlert();

        throw new UnsupportedOperationException();
    }

    /**
     * Waits until page loader disappears from the page
     */
        public void waitForContentLoad() {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajax_running")));
        }

    public void waitForAlert() {
        WebElement closeAlert = driver.findElement(By.className("growl-close"));

        wait.until(ExpectedConditions.elementToBeClickable(closeAlert));

        closeAlert.click();
    }
}