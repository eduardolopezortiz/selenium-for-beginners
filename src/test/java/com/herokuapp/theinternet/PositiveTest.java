package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTest {
	
	@Test 
	public void loginTest() {

		// create driver
		System.setProperty("webdriver.chrome.driver", "C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/chromedriver.exe");
		// new instance of driver
		WebDriver driver = new ChromeDriver();

		// sleep for 3 second
		sleep(1000);

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("page is open");

		// sleep for 3 second
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
		
		//enter username
		WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
		username.sendKeys("tomsmith");
		sleep(1500);
		
		//enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(4500);
		
		//click login button
		WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
		loginButton.click();
		sleep(4500);
		
		//verification of url
		//new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
		
		
		//logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//div[@id='content']//a[@href='/logout']"));
		//logOutButton.click();	
		Assert.assertTrue(logOutButton.isDisplayed(), "Log out button is not visible");
		sleep(5000);
		
		
		//success login message
		WebElement succesMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = succesMessage.getText();
		//Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message doesnt contain expected message.\nActual message: " + actualMessage 
				+ "\nExpected message: " + expectedMessage);
		
		
	
		//close browser
		driver.quit();
		
		
		
	}

	private void sleep(long m) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
