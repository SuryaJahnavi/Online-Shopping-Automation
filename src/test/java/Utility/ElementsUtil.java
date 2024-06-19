package Utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementsUtil {
	static WebElement element;
	private static WebDriver driver;
	static List<WebElement> elements;
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		ElementsUtil.driver = driver;
	}
	
	public static WebElement getElement(String inp, String method) {
		if(method.equals("id")) {
			element = getDriver().findElement(withId(inp));
		} else if (method.equals("xpath")) {
			element = getDriver().findElement(withXpath(inp));
		}
		return element;
	}
	
	public static List<WebElement> getElements(String inp, String method) {
		if(method.equals("id")) {
			elements = getDriver().findElements(withId(inp));
		} else if (method.equals("xpath")) {
			elements = getDriver().findElements(withXpath(inp));
		}
		return elements;
	}
	
	public static By withId(String inp) {
		return By.id(inp);
	}
	
	public static By withXpath(String inp) {
		return By.xpath(inp);
	}

	
	
}