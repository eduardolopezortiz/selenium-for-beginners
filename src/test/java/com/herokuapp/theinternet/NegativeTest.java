package com.herokuapp.theinternet;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest {// class to test a negative test
	@Test(priority = 1)
	public void incorrectusernameTest() {

		System.out.println("Starting incorrect user namame test");

		// create driver
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/chromedriver.exe");
		// new instance of driver
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(1000);

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("<<<page is open>>>");

		// sleep for 3 second
		sleep(2000);

		// maximize browser window
		driver.manage().window().maximize();

		// enter the wrong username
		WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
		username.sendKeys("tomsmith5");
		sleep(1500);

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(4500);

		// click login button
		WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
		loginButton.click();
		sleep(2500);

		// success login message
		WebElement invalidUserNameMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String expectedMessage = "Your username is invalid!";
		String actualMessage = invalidUserNameMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message doesnt contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);

		sleep(1500);
		
		driver.quit();

		

	}
	
	@Test(priority = 2)
	public void incorrectupasswordTest() {

		System.out.println("<<<<Starting incorrect password test>>>>");

		// create driver
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/chromedriver.exe");
		// new instance of driver
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(1000);

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("<<<page is open>>>");

		// sleep for 3 second
		sleep(2000);

		// maximize browser window
		driver.manage().window().maximize();

		// enter the wrong username
		WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
		username.sendKeys("tomsmith");
		sleep(1500);

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("incorrectPassword!");
		sleep(4500);

		// click login button
		WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
		loginButton.click();
		sleep(2500);

		// success login message
		WebElement errorMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String expectedMessage = "Your password is invalid!";
		String actualMessage = errorMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message doesnt contain expected message.\nActual message: " + actualMessage
						+ "\nExpected message: " + expectedMessage);

		sleep(1500);
		
		driver.quit();

		

	}
	
	
	
//method sleep
	private void sleep(long m) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}