package selenium;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class CircleFirefoxJupiterTest {

    @Test
    void testWithOneFirefox(FirefoxDriver driver) {
        driver.get("http://localhost:8080/demo/servlet/circle.html");
        WebElement radiusInput = driver.findElement(By.id("radius"));
        radiusInput.clear();
        radiusInput.sendKeys("125");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        //driver.wait(3000);
    }

    

}