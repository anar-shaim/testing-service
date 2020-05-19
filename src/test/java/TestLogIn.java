import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogIn {
    public String baseUrl = "http://www.ozon.ru";

    @Attachment
            (value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void authorize() {
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wdw = new WebDriverWait(driver, 60);
        Actions actions = new Actions(driver);
        driver.get(baseUrl);
        String dataW = null;

        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]")));
        WebElement profile = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]")));
        dataW = profile.getAttribute("data-widget");
        Assert.assertEquals(dataW, "profileMenuAnonymous");
        saveAllureScreenshot(driver);
        actions.moveToElement(wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]")))).click().perform();
        //ввод телефона и кода вручную
        wdw.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[2]/label/div")));
        wdw.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/div/div")));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement profile2 = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]")));
        Assert.assertEquals(profile2.getAttribute("data-widget"), "profile");
        saveAllureScreenshot(driver);

        driver.close();
    }
}
