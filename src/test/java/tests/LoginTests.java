package tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
//Login tests are maintained here.
//without page object approach

public class LoginTests {

   WebDriver driver;

   Map<String,String> myMap= new HashMap<String, String>();

   @BeforeMethod
    public void SetUp(){
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   }

   @Test
    public void loginTest1(){
       driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2ftestcomplete12%2fweborders%2fDefault.aspx");
       driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
       driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);
       String title = driver.getTitle();
       Assert.assertEquals(driver.getTitle(),"Web Orders");


   }

   @Test
   public void negativeloginTest1(){
      driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2ftestcomplete12%2fweborders%2fDefault.aspx");
      driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester2");
      driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test2"+ Keys.ENTER);
      String errorMsg=driver.findElement(By.id("ctl00_MainContent_status")).getText();;
      Assert.assertEquals(errorMsg,"Invalid Login or Password.");


   }

   @Test
   public void logOutTest(){
      driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2ftestcomplete12%2fweborders%2fDefault.aspx");
      driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
      driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);


      driver.findElement(By.id("ctl00_logout")).click();
      String title = driver.getTitle();
      Assert.assertEquals(driver.getTitle(),"Web Orders Login");

   }

   @AfterMethod
   public  void cleapUp(){
      driver.close();

   }


}
