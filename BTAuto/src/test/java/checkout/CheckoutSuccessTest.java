package checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutSuccessTest {
    static WebDriver driver = null;

    public static void printProducts(String screenName){
        System.out.println("===== " + screenName + " =====");
        List<WebElement> items = driver.findElements(By.className("cart_item"));
        for (WebElement item: items){
            String name = item.findElement(By.className("inventory_item_name")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();
            System.out.println("Ten: " + name);
            System.out.println("Gia: " + price);
        }
    }

    public static void main(String[] args) {
        // để Tắt Password
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebElement user = driver.findElement(By.id("user-name"));
        user.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();


        List<WebElement> addCarts = new ArrayList<>();
        addCarts.add(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")));
        addCarts.add(driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")));

        for (WebElement add: addCarts){
            add.click();
        }
        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        System.out.println("Số lượng giỏ hàng: "+ shoppingCart.getText());
        shoppingCart.click();

//        List<WebElement> items = driver.findElements(By.className("cart_item"));
//        for (WebElement item: items){
//            String name = item.findElement(By.className("inventory_item_name")).getText();
//            String price = item.findElement(By.className("inventory_item_price")).getText();
//            System.out.println("Ten: " + name);
//            System.out.println("Gia: " + price);
//        }
        printProducts("Your Cart");
        List<WebElement> buttonRemove = driver.findElements(By.xpath("//button[text()='Remove']"));
        System.out.println("Button remove: "+buttonRemove.size());


        WebElement buttonCheckout = driver.findElement(By.id("checkout"));
        buttonCheckout.click();

        WebElement firstName= driver.findElement(By.id("first-name"));
        firstName.sendKeys("Tran");
        WebElement lastName= driver.findElement(By.id("last-name"));
        lastName.sendKeys("Quynh");
        WebElement postalCode= driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("123");

        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();

        printProducts("Checkout Overview");

        WebElement shipping = driver.findElement(By.cssSelector("[data-test='shipping-info-value']"));
        System.out.println("Shipping Information: "+ shipping.getText());



        WebElement itemTotal = driver.findElement(By.cssSelector("[data-test='subtotal-label']"));
        System.out.println(itemTotal.getText());

        WebElement tax = driver.findElement(By.cssSelector("[data-test='tax-label']"));
        System.out.println(tax.getText());

        WebElement total = driver.findElement(By.cssSelector("[data-test='total-label']"));
        System.out.println(total.getText());

        WebElement buttonFinish = driver.findElement(By.id("finish"));
        System.out.println(buttonFinish.getText());
        buttonFinish.click();

        WebElement textCheckout = driver.findElement(By.xpath("//span[text()='Checkout: Complete!']"));
        textCheckout.getText();

        WebElement completeHeader = driver.findElement(By.cssSelector("[data-test='complete-header']"));
        System.out.println(completeHeader.getText());

        WebElement completeText = driver.findElement(By.cssSelector("[data-test='complete-text']"));
        System.out.println(completeText.getText());

        WebElement buttonBack = driver.findElement(By.id("back-to-products"));
        System.out.println(buttonBack.getText());

        driver.quit();


    }
}
