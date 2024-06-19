package Main;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.DriverSetup;
import Utility.ElementsUtil;
import Utility.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	static WebDriver driver;
	
	public static void titleValidation() throws IOException {
		//to get the title of the page
		String jk= driver.getTitle();

		//validating the page title
		if(jk.equals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")) 
		{
			System.out.println("Home page loaded");
		}
		else
		{
			System.out.println("Home page not loaded");
			
		}
		//Title validation screenshot
		DriverSetup.ScreenShot(System.getProperty("user.dir") + "/Screenshots/1.Title Validation.png");
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose Browser (Chrome/Edge)");
		//user chooses the browser
		String browser = sc.nextLine();
		sc.close();
		//gets the browser from DriverSetup
		driver=DriverSetup.createDriver(browser);
		ElementsUtil.setDriver(driver);

		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		
		titleValidation();
		
		
		

		
		String input = "Home appliances";
		
		//Home appliances input in the search box
		ElementsUtil.getElement("twotabsearchtextbox", "id").sendKeys(input);
		//Home appliances screenshot
		DriverSetup.ScreenShot(System.getProperty("user.dir") + "/Screenshots/2.Search Home sappliances.png");
		ElementsUtil.getElement("nav-search-submit-button", "id").click();
		//First item gets clicked in the home appliances page
		ElementsUtil.getElement("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a/span", "xpath").click();
		
		String firsturl=driver.getCurrentUrl();
	 
	    //Switching between windows
		 ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
		 driver.switchTo().window(tabs.get(1));
	   //add to cart button  
		 ElementsUtil.getElement("//*[@id='add-to-cart-button']", "xpath").click();
	  
	  
	  
      //Skip button
      wait.until(ExpectedConditions.visibilityOfElementLocated(ElementsUtil.withXpath("//span[@id='attachSiNoCoverage-announce']/preceding-sibling::input"))).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(ElementsUtil.withXpath("//a[@data-csa-c-slot-id='sw-gtc']"))).click();
      //First product goes to cart and screenshot taken
 	  DriverSetup.ScreenShot(System.getProperty("user.dir") + "/Screenshots/3.First product added to cart.png");
 	  //displaying the price of first product in console
      List<WebElement> priceArr = ElementsUtil.getElements("//span[@id=\"sc-subtotal-amount-buybox\"]/descendant::span", "xpath");
      String p1j = "";
      
      for(WebElement we : priceArr) {
    	  p1j += we.getText();
      }
      
      System.out.println("p1:"+p1j);
      

      //closes the driver 
        driver.close();
   	 	
    	
   		
    	
      //Back to Home appliances page
        driver.switchTo().window(tabs.get(0));
   		
        driver.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[4]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a/span")).click();
        //Back to Home appliances page screenshot
   		DriverSetup.ScreenShot(System.getProperty("user.dir") + "/Screenshots/4.Back to home appliances page.png");
 
        ArrayList<String> tabs1=new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs1.get(1));
        //add to cart button for second item
   		driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
   		//skip button
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='attachSiNoCoverage-announce']/preceding-sibling::input"))).click();
   		//go to cart button
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-csa-c-slot-id='sw-gtc']"))).click();
   		//second item added to cart and screenshot taken
   		DriverSetup.ScreenShot(System.getProperty("user.dir") + "/Screenshots/5.Second product added to cart.png");

   		//gets the prices of both the items to console
   		List<WebElement> st = driver.findElements(By.xpath("//div[@class='a-section a-spacing-mini'][1]/descendant::span"));
   		
   		String prices = "";
   		for(WebElement el : st) prices+=el.getText();
   		

   		prices= prices.strip();
   		System.out.println("prices:  " +prices);
   		
   		List<WebElement> tot = driver.findElements(By.xpath("//span[@id='sc-subtotal-amount-activecart']/descendant::span"));
   		
   		String totPrice = "";
   		for(WebElement el : tot) totPrice+=el.getText();
   		
        //gets total price to the console
   		totPrice=totPrice.strip();
   		System.out.println("total price:  " +totPrice);
   		
   		String[] p1 = prices.split(" ");
        
   		System.out.println("Price 1 -> " + p1[p1.length-1]);
   		System.out.println("Price 2  -> " + p1[0]);
   		
   		
   		String p2 = p1[0];
   		
   		//validation of prices
   		if(Integer.parseInt(p1j.strip().replaceAll("[,.]", "")) + Integer.parseInt(p2.strip().replaceAll("[,.]", "")) == Integer.parseInt(totPrice.strip().replaceAll("[,.]", "")))
   		{
   			System.out.println("Validation Successful");
   		} 
   		else 
   		{
   			System.out.println("Validation Failed");
   		}
    		
        //closing the driver
  		DriverSetup.endDriver();
   		
   		
   		
    	

  	  
			
		
		

	}

}
