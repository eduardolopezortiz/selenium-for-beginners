package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest {// class to test a negative test
	@Test 
	public void incorrectusernameTest() {
		
		System.out.println("Starting incorrect user namame test");

		// create driver
		System.setProperty("webdriver.chrome.driver", "C:/Users/BI/eclipse-workspace/selenium-for-beginners/src/main/resources/chromedriver.exe");
		// new instance of driver
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(1000);

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("page is open");

		// sleep for 3 second
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
		
		
		//enter the wrong username
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
		sleep(2500);

		
		
	
		
		//success login message
		WebElement invalidUserNameMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String expectedMessage = "Your username is invalid!";
		String actualMessage = invalidUserNameMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message doesnt contain expected message.\nActual message: " + actualMessage 
				+ "\nExpected message: " + expectedMessage);
		
		sleep(1500);
		
		
	
		//close browser
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