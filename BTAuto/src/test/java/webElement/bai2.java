package webElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class bai2 {
    static WebDriver driver = null;

    public static <WebElements> void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        WebElement email = driver.findElement(By.id("Business-Email"));
        email.sendKeys("tt@gmail.com");

        WebElement firstName = driver.findElement(By.id("First Name"));
        firstName.sendKeys("tt");

        WebElement lastName = driver.findElement(By.id("Last Name"));
        lastName.sendKeys("q");




        WebElement phone = driver.findElement(By.id("Phone"));
        phone.sendKeys("09677");

        WebElement country = driver.findElement(By.id("Country"));
        Select optionC = new Select(country);
        optionC.selectByIndex(3);

        WebElement useCase = driver.findElement(By.id("Use-Case"));
        Select option = new Select(useCase);
        option.selectByIndex(3);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement comment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Comments")));
        comment.sendKeys("hello hello");

//        WebElement checkbox = driver.findElement(By.id("Checkbox"));
//        checkbox.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", comment);

        Thread.sleep(1000);

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("form-button"))
        );

        button.click();

        WebElement company = driver.findElement(By.id("Company"));
        String message = company.getAttribute("validationMessage");
        String firstName2 = company.getAttribute("name");
        System.out.println(firstName2 + " : " + message);
//        driver.quit();
    }
}
