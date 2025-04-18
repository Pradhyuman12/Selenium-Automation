package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumDemoClass {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Starting from here in an attempt to automate facebook register page.\n");

		String projectPath = System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.get("https://www.google.com");
		
		driver.switchTo().newWindow(WindowType.TAB);

		driver.get("https://www.facebook.com/");
		
		driver.navigate().refresh();
		
		String actualTitle = driver.getTitle();

		String expectedTitle = "Facebook – log in or sign up";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Test Case 1 - Passed");
		}

		boolean welcomeText = driver.getPageSource()
				.contains("Facebook helps you connect and share with the people in your life.");

		if (welcomeText) {
			System.out.println("Test Case 2 - Passed");
		}

		WebElement newAccountBtn = driver.findElement(By.linkText("Create new account"));

		if (newAccountBtn.isDisplayed()) {
			System.out.println("Test Case 3 - Passed");

			newAccountBtn.click();
		}

		boolean createNewAccText = driver.getPageSource().contains("Create a new account");

		if (createNewAccText) {
			System.out.println("Test Case 4 - Passed");
		}

		WebElement firstnameInput = driver.findElement(By.name("firstname"));

		firstnameInput.sendKeys("Test");

		WebElement lastnameInput = driver.findElement(By.name("lastname"));

		lastnameInput.sendKeys("User");

		WebElement day = driver.findElement(By.id("day"));

		Select selectDay = new Select(day);

		selectDay.selectByValue("31");

		WebElement month = driver.findElement(By.id("month"));

		Select selectMonth = new Select(month);

		selectMonth.selectByValue("12");

		WebElement year = driver.findElement(By.id("year"));

		Select selectYear = new Select(year);

		selectYear.selectByValue("1905");
		
		WebElement genderMale = driver.findElement(By.xpath("//label/input[@value='2']"));
		
		if(genderMale.isDisplayed()) {
			genderMale.click();
		}
		
		WebElement emailInput = driver.findElement(By.name("reg_email__"));
		
		emailInput.sendKeys("testuser123@facebook.com");
		
		WebElement passwordInput = driver.findElement(By.id("password_step_input"));
		
		passwordInput.sendKeys("Testuser123!");
		
		WebElement signupBtn = driver.findElement(By.name("websubmit"));
		
		if(signupBtn.isDisplayed()) {
			System.out.println("\nYou have successfully reached here. Bye!\n");
		}
		
		driver.close();
		
		System.out.println("closed current tab\n");
		
		driver.quit();
		
		System.out.println("closed all tabs\n");

		
	}

}
