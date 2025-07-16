package blazedemo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class driver {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://blazedemo.com/");
		driver.manage().window().maximize();
		
		String actual_title = driver.getTitle();
		String expected_title = "BlazeDemo";
		if(actual_title.equals(expected_title)) {
			System.out.println("user landed on correct page");
		}
		
		WebElement from_port = driver.findElement(By.xpath("//select[@name='fromPort']"));
		Select select_from = new Select(from_port);
		select_from.selectByVisibleText("Boston");
		WebElement to_port = driver.findElement(By.xpath("//select[@name='toPort']"));
		Select select_to = new Select(to_port);
		select_to.selectByVisibleText("Berlin");
		WebElement find_flights = driver.findElement(By.xpath("//input[@value='Find Flights']"));
		find_flights.click();
		
		String page2_actual_title = driver.getTitle();
		String page2_expected_title = "BlazeDemo - reserve";
		if(page2_actual_title.equals(page2_expected_title)) {
			System.out.println("user landed on reserve flights page");
		}
		
		int total_rows = driver.findElements(By.xpath("//table[@class='table']//tr")).size();
		int total_columns = driver.findElements(By.xpath("//table[@class='table']//th")).size();
		List<Double> prices = new ArrayList<Double>();
		for(int i=1; i<=total_rows-1; i++) {
			String price = driver.findElement(By.xpath("//table[@class='table']//tr["+i+"]//td[6]")).getText();
			prices.add(Double.parseDouble(price.replace("$", " ").trim()));
		}
		double min_price = Double.MAX_VALUE;
		for(int i=0; i<prices.size(); i++) {
			if(prices.get(i) < min_price) {
				min_price = prices.get(i);
			}
		}
		for(int i=1; i<=total_rows-1; i++) {
			String price = driver.findElement(By.xpath("//table[@class='table']//tr["+i+"]//td[6]")).getText();
			if(min_price == Double.parseDouble(price.replace("$", " ").trim())) {
				driver.findElement(By.xpath("//table[@class='table']//tr["+i+"]//td[1]//input")).click();
				break;
			}
		}
		
		String pag3_actual_title = driver.getTitle();
		String page3_expected_title = "BlazeDemo Purchase";
		if(pag3_actual_title.equals(page3_expected_title)) {
			System.out.println("user landed on BlazeDemo Purchase page");
		}
		
		WebElement name_inputbox = driver.findElement(By.xpath("//input[@id='inputName']"));
		name_inputbox.sendKeys("Prad");
		WebElement address_inputbox = driver.findElement(By.xpath("//input[@id='address']"));
		address_inputbox.sendKeys("123 Building, ABC Street");
		WebElement city_inputbox = driver.findElement(By.xpath("//input[@id='city']"));
		city_inputbox.sendKeys("Pune");
		WebElement state_inputbox = driver.findElement(By.xpath("//input[@id='state']"));
		state_inputbox.sendKeys("Maharashtra");
		WebElement zipcode_inputbox = driver.findElement(By.xpath("//input[@id='zipCode']"));
		zipcode_inputbox.sendKeys("123123");
		WebElement cardNumber_inputbox = driver.findElement(By.xpath("//input[@id='creditCardNumber']"));
		cardNumber_inputbox.sendKeys("1111 2222 3333 4444");
		WebElement cardMonth_inputbox = driver.findElement(By.xpath("//input[@id='creditCardMonth']"));
		cardMonth_inputbox.clear();
		cardMonth_inputbox.sendKeys("08");
		WebElement cardYear_inputbox = driver.findElement(By.xpath("//input[@id='creditCardYear']"));
		cardYear_inputbox.clear();
		cardYear_inputbox.sendKeys("2032");
		WebElement cardName_inputbox = driver.findElement(By.xpath("//input[@id='nameOnCard']"));
		cardName_inputbox.sendKeys("Prad Human");
		
		driver.findElement(By.xpath("//input[@id='rememberMe']")).click();
		driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
		
		String confirmationPage_actualTitle = driver.getTitle();
		String confirmationPage_expectedTitle = "BlazeDemo Confirmation";
		if(confirmationPage_actualTitle.equals(confirmationPage_expectedTitle)) {
			System.out.println("user landed on confirmation page");
		}
		
		WebElement thankyou_heading = driver.findElement(By.tagName("h1"));
		String actualThankYouText = thankyou_heading.getText();
		String expectedThankYouText = "Thank you for your purchase today!";
		if(actualThankYouText.equals(expectedThankYouText)) {
			System.out.println("Test Completed Successfully.");
			System.out.println("Finally!!");
		}
		
		driver.quit();
	}

}
