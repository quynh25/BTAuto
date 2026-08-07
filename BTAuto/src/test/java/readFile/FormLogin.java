package readFile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static Utils.ExcelUtils.readExcelData;

public class FormLogin {
    private static final String EXCEl_PATH = "FormLogin.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    private static final String LOGIN_URL = "https://saucelabs.com/request-demo";

    public static void main(String[] args) {
        List<Map<String,String>> testData = readExcelData(EXCEl_PATH,SHEET_NAME);
        WebDriver driver;
        int i = 1;
        for (Map<String,String> row:testData){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            String email = row.get("Business Email");
            String firstName = row.get("First name");
            String lastName = row.get("Last name");
            String company = row.get("Company");
            String phoneNumber = row.get("Phone number");
            String country = row.get("Country");
            String useCase = row.get("Use Case");
            String comment = row.get("Comments");

            System.out.println("Lần " + i + " nhập form");
            i++;
            driver.get(LOGIN_URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



            WebElement emailField = driver.findElement(By.id("Business-Email"));
            emailField.sendKeys(email);

            WebElement firstNameField = driver.findElement(By.id("First Name"));
            firstNameField.sendKeys(firstName);

            WebElement lastNameField = driver.findElement(By.id("Last Name"));
            lastNameField.sendKeys(lastName);


            WebElement companyField = driver.findElement(By.id("Company"));
            companyField.sendKeys(company);

            WebElement phoneField = driver.findElement(By.id("Phone"));
            phoneField.sendKeys(phoneNumber);

            WebElement countryField = driver.findElement(By.id("Country"));
            Select countrySelect = new Select(countryField);
            countrySelect.selectByVisibleText(country);

            WebElement useCaseField = driver.findElement(By.id("Use-Case"));
            Select useCaseSelect = new Select(countryField);
            useCaseSelect.selectByVisibleText(country);

            WebElement commentField = driver.findElement(By.id("Comments"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", commentField);
            commentField.sendKeys(comment);



            WebElement button = driver.findElement(By.className("form-button"));
            button.click();
            driver.quit();

        }
    }
}
