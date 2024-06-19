package Utility;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	static WebDriver driver;
	static String url = "https://www.amazon.in/";
	
	public static WebDriver createDriver(String browser) {
		
		
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		driver.get(url);
		
		return driver;
	}
	
	
	public static void ScreenShot(String img) throws IOException {
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File target = new File(img);
			FileHandler.copy(source,target);
	}
	 
	
	public static void endDriver() {
		System.out.println("Browser is closed");
		driver.quit();
	}
}
