package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
	
	private WebDriver driver;
	
	
	
	@Parameters({ "browser"})	
	@BeforeMethod(alwaysRun = true)
	//si no hay un parametro que venga del LoginTests.xml e su lugar asiganra chrome a la variable browser
	private void setUp(@Optional("chrome") String browser) {
		// TODO Auto-generated method stub
		// create driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/BI/cursos/selenium-for-beginners/src/main/resources/chromedriver.exe");
			// new instance of driver
			driver = new ChromeDriver();
			break;

			
		case "firefox": 
			System.setProperty("webdriver.geckodriver.driver",
					"C:/Users/BI/cursos/selenium-for-beginners/src/main/resources/geckodriver.exe");
			// new instance of driver
			driver = new FirefoxDriver();
			break;
			
			
		default:
			
		System.out.println("Do not know how to start "+ browser + ", starting chrome instead");
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/BI/cursos/selenium-for-beginners/src/main/resources/chromedriver.exe");
			// new instance of driver
			driver = new ChromeDriver();
			
			break;
		}
		
		
		
				

				// sleep for 3 second
				sleep(1000);

				// maximize browser window
				driver.manage().window().maximize();
				sleep(1000);

	}

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void positiveloginTest() {

		

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("<<<page is open>>>");
		System.out.println("<<<Starting succesfully login test>>>");

		// sleep for 3 second
		sleep(1000);

		// enter username
		WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
		username.sendKeys("tomsmith");
		sleep(1000);

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(1000);

		// click login button
		WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
		loginButton.click();
		sleep(1000);

		// verification of url
		// new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

		// logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//div[@id='content']//a[@href='/logout']"));
		// logOutButton.click();
		Assert.assertTrue(logOutButton.isDisplayed(), "Log out button is not visible");
		sleep(3000);

		// success login message
		WebElement succesMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = succesMessage.getText();
		// Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
		// the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message doesnt contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);

	

	}
	

	//Negative Login Test

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })

	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

		System.out.println("Starting negativeLoginTest with " + username + " and " + password);
		/*
		 * // create driver System.setProperty("webdriver.chrome.driver",
		 * "C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/chromedriver.exe"
		 * ); // new instance of driver WebDriver driver = new ChromeDriver();
		 */
		// sleep for 3 seconds
		sleep(1000);

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("<<<page is open>>>");

		// sleep for 3 second
		sleep(1000);

		// maximize browser window
		driver.manage().window().maximize();

		// enter the wrong username
		WebElement usernameElemnet = driver.findElement(By.xpath("/html//input[@id='username']"));
		usernameElemnet.sendKeys(username);
		sleep(1000);

		// enter password
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);
		sleep(1000);

		// click login button
		WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
		loginButton.click();
		sleep(1000);

		// success login message
		WebElement invalidUserNameMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String actualMessage = invalidUserNameMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedErrorMessage),
				"Actual message doesnt contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedErrorMessage);

		sleep(1000);

		

	}

	private void sleep(long m) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// close browser
		driver.quit();
	}

}
