package standaloneItems;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindBrokenLinks {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total no. of links : "+links.size());
		
		List<String> urls = new ArrayList<String>();
		for(WebElement link : links) {
			String url = link.getDomAttribute("href");
			urls.add(url);
		}
		
		urls.parallelStream().forEach(e -> checkBrokenLink(e)); //using parallelStream() for faster execution
		
		driver.quit();
	}
	
	public static void checkBrokenLink(String linkUrl) {
		
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.connect();
			
			if(httpUrlConnection.getResponseCode() >= 400) {
				System.out.println("Broken Link - "+linkUrl+" - "+httpUrlConnection.getResponseMessage());
			} else {
				System.out.println("Proper Link - "+linkUrl+" - "+httpUrlConnection.getResponseMessage());
			}
			
		} catch (Exception e) {
			
		}
		
	}

}
