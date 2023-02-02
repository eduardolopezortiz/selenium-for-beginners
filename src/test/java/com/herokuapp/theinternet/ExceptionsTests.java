package com.herokuapp.theinternet;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionsTests {

	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	// si no hay un parametro que venga del LoginTests.xml en su lugar asignara 
	// chrome a la variable browser
	private void setUp(@Optional("chrome") String browser) {
		// TODO Auto-generated method stub
		// create driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/chromedriver.exe");
			// new instance of driver
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.geckodriver.driver",
					"C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/geckodriver.exe");
			// new instance of driver
			driver = new FirefoxDriver();
			break;

		default:

			System.out.println("Do not know how to start " + browser + ", starting chrome instead");
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/chromedriver.exe");
			// new instance of driver
			driver = new ChromeDriver();

			break;
		}

		// sleep for 3 second
		

		// maximize browser window
		driver.manage().window().maximize();
		
	}
	
	
	@Test
	public void noSuchElementExceptionTest() {
		
		
		//Test case 1: noSuchElementException
		
		//Open page
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		
		//Click Add button
		WebElement addButtonElement = driver.findElement(By.xpath("/html//button[@id='add_btn']"));
		addButtonElement.click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Verify Row 2 input field is displayed
		WebElement row2Input = driver.findElement(By.xpath("//div[@id='rows']/div[3]/div[@class='row']/input[@type='text']"));
		Assert.assertTrue(row2Input.isDisplayed(),"Row 2 is not diplayed");
		
			
		
		
		
		
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// close browser
		driver.quit();
	}

}
