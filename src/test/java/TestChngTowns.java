import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestChngTowns {
    public String baseUrl = "http://www.ozon.ru";

    @Attachment
            (value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void checkTowns() {
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String town1 = null, town2 = null;
        WebDriverWait wdw = new WebDriverWait(driver, 60);
        Actions actions = new Actions(driver);
        driver.get(baseUrl);

        String town = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/button/span"))).getText();
        Assert.assertEquals(town, "Москва");
        saveAllureScreenshot(driver);
        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/button"))).click();
        WebElement we = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/label/div/input")));
        we.sendKeys("Вольск");
        wdw.until(ExpectedConditions.textToBe(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/ul/li[1]/a"), "Вольск, Саратовская область"));
        saveAllureScreenshot(driver);
        we.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        town = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/button/span"))).getText();
        Assert.assertEquals(town, "Вольск");
        saveAllureScreenshot(driver);


        //авторизация
        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]")));
        actions.moveToElement(wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]")))).click().perform();
        //ввод телефона и кода вручную
        wdw.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[2]/label/div")));
        wdw.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/div/div")));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement profile = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]")));
        actions.moveToElement(profile).perform();
        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/div[1]/div/ul/li[1]/a"))).click();
        WebElement btn = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[4]/div/div[2]/div[5]/div[2]/div/div[2]/div[1]/a")));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 250)");
        actions.moveToElement(btn).perform();
        btn.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        town1 = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/button/span"))).getText();
        town2 = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[3]/div/div[2]/div[2]/div/div[2]/div/span/span"))).getText();
        saveAllureScreenshot(driver);
        Assert.assertEquals(town1, "Вольск");
        Assert.assertEquals(town2, "Вольск");
        driver.close();
    }
}
