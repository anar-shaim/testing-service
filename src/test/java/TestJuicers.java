import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestJuicers {
    public String baseUrl = "http://www.ozon.ru";

    @Attachment
            (value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void juiciers() {
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String town1 = null, town2 = null;
        WebDriverWait wdw = new WebDriverWait(driver, 20);
        Actions actions = new Actions(driver);

        driver.get(baseUrl);

        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[1]/button"))).click();
        WebElement menu1 = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[1]/div/a[12]")));
        actions.moveToElement(menu1).perform();
        WebElement menu2 = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a[6]")));
        actions.moveToElement(menu2).perform();
        saveAllureScreenshot(driver);
        menu2.click();
        WebElement priceFrom = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[1]/input")));
        actions.moveToElement(priceFrom).perform();
        priceFrom.click();
        priceFrom.sendKeys(Keys.CONTROL + "a");
        priceFrom.sendKeys(Keys.DELETE);
        priceFrom.sendKeys("3000");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 250)");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement priceTo = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[2]/input")));
        actions.moveToElement(priceTo).perform();
        priceTo.click();
        priceTo.sendKeys(Keys.CONTROL + "a");
        priceTo.sendKeys(Keys.DELETE);
        priceTo.sendKeys("4000");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jse.executeScript("scroll(0, 250)");
        priceTo.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement priceRange = wdw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div/div/button/div/span")));
        Assert.assertEquals("Цена: от 3 000 до 4 000", priceRange.getText());
        saveAllureScreenshot(driver);
        WebElement filter = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div")));
        actions.moveToElement(filter).perform();
        filter.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div"))).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div"))).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        saveAllureScreenshot(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
        jse.executeScript("scroll(0, 250)");
        WebElement addToCart = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div/div/div[3]/div[2]/div/div/button")));
        actions.moveToElement(addToCart).perform();
        saveAllureScreenshot(driver);
        addToCart.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[4]/a[2]"))).click();

        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div"))).click();
        WebElement priceToCh = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div/div/span")));
        Integer price = Integer.parseInt(priceToCh.getText().replaceAll(" ", "").replace("\u20BD", ""));
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        saveAllureScreenshot(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
        WebElement priceAftAdd = wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div[1]/div")));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals((price * 5), Integer.parseInt(priceAftAdd.getText().replaceAll(" ", "").replace("\u20BD", "")));
        saveAllureScreenshot(driver);
        driver.close();
    }
}
