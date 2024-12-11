import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Assessment {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://pos.com.my/send/ratecalculator");

        WebElement iframe = driver.findElement(By.tagName("iframe"));
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);
        new Actions(driver)
                .scrollFromOrigin(scrollOrigin, 0, 300)
                .perform();

        driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-static-layout/app-rate-calculator-v2/div/div[3]/div[1]/div[3]/div/div[2]/div[1]/input")).sendKeys("1");
        Thread.sleep(2);
        driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-static-layout/app-rate-calculator-v2/div/div[3]/div[1]/div[1]/div[3]/div/input")).sendKeys("35000");
        Thread.sleep(2);
        driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-static-layout/app-rate-calculator-v2/div/div[3]/div[1]/div[2]/div[3]/div/input")).sendKeys("70594");
        Thread.sleep(2);
        driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-static-layout/app-rate-calculator-v2/div/div[3]/div[2]/a")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Your Quote']")));

        WebElement Quote = driver.findElement(By.xpath("//h1[text()='Your Quote']"));
        WheelInput.ScrollOrigin scroll = WheelInput.ScrollOrigin.fromElement(Quote);
        new Actions(driver)
                .scrollFromOrigin(scroll, 0, 200)
                .perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("border-b")));

        List<WebElement> productElements = driver.findElements(By.className("border-b"));

        if (productElements.size() >= 1 && productElements.size() <= 3) {
            for (WebElement product : productElements) {
                String quote = product.getText();
                if (quote.contains("POS LAJU")) {
                    System.out.println("POS LAJU is present in quote");
                    break;
                } else {
                    System.out.println("validation failed");
                }
            }
        } else {
            System.out.println("Unexpected number of products in the cart.");
        }


    }
}
