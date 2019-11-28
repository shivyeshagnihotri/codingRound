package main.java;

import com.sun.javafx.PlatformUtil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();
    
    
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(id = "CheckInDate")
    private WebElement checkInDate;

    @FindBy(id = "CheckOutDate")
    private WebElement checkOutDate;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();

        //This will create WebElements
        PageFactory.initElements(driver, this);

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();
        waitFor(2000);
        
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-id-1"))));
        
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"ui-id-1\"]//li"));
        list.get(1).click();
        
        //Enter dates
        checkInDate.click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr[3]/td[3]/a")).click();
        checkOutDate.click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[3]/td[5]/a")).click();
        
        Select travellers = new Select(travellerSelection);
        travellers.selectByVisibleText("1 room, 2 adults");
        
        searchButton.click();

        driver.quit();

    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
