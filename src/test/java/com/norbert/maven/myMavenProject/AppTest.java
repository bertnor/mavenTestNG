package com.norbert.maven.myMavenProject;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.norbert.maven.app.Website;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



/**
 * Unit test for simple App.
 */
public class AppTest {
	
	final static String webUrl = "http://timvroom.com/selenium/playground/";
	WebDriver driver;
	@Test
	@Parameters("browser")
	public void setBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			System.out.println(browserName);
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("opera")) {
			driver = new OperaDriver();
		}
	}
	
	@Test
    public void testApp() throws InterruptedException
    {
		Website webiste = new Website(driver);
		webiste.openUrl(webUrl);
		Assert.assertEquals(driver.getCurrentUrl(), "http://timvroom.com/selenium/playground/", "lala" );
		System.out.println("uwaga tytyul");
		System.out.println(webiste.getTitle());
		String title = driver.getTitle();
		driver.findElement(By.id("answer1")).sendKeys(title);
		driver.findElement(By.id("name")).sendKeys("Kilgore Trout");
		Select dropdown = new Select(driver.findElement(By.id("occupation")));
		dropdown.selectByVisibleText("Science Fiction Author");
		Integer numberOfBlueBoxes = new Integer(driver.findElements(By.className("bluebox")).size());
		driver.findElement(By.id("answer4")).sendKeys(numberOfBlueBoxes.toString());
		driver.findElement(By.linkText("click me")).click();
		//6
		String redBoxClass = driver.findElement(By.id("redbox")).getAttribute("class").toString();
		driver.findElement(By.id("answer6")).sendKeys(redBoxClass);
		//7 ZLE
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("return ran_this_js_function();");
		Object cos = js.executeScript("return got_return_from_js_function();");
		driver.findElement(By.id("answer8")).sendKeys(cos.toString());
		//8 ZLe
		//9
		List<WebElement> radios = driver.findElements(By.name("wrotebook"));
		radios.get(0).click();
		//10
		String textFromRedBox =  driver.findElement(By.id("redbox")).getText();
		driver.findElement(By.id("answer10")).sendKeys(textFromRedBox);
		//11
		WebElement orangeBox = driver.findElement(By.id("orangebox"));
		WebElement greenBox = driver.findElement(By.id("greenbox"));
		System.out.println(orangeBox.getLocation());
		System.out.println(greenBox.getLocation());
		Point pointOrange = orangeBox.getLocation();
		Point pointGreen = greenBox.getLocation();
		if(pointOrange.getY() < pointGreen.getY()){
			driver.findElement(By.id("answer11")).sendKeys("Orange");
			((JavascriptExecutor) driver).executeScript("document.getElementById('answer11').style.color = 'orange';");
		}
		else{
			driver.findElement(By.id("answer11")).sendKeys("Green");
			((JavascriptExecutor) driver).executeScript("document.getElementById('answer11').style.color = 'green';");
		}
		
		//12
		Dimension dim = new Dimension(848, 648);
		driver.manage().window().setSize(dim);
		System.out.println(driver.manage().window().getSize());
		//13
		try{
			WebElement element = driver.findElement(By.id("ishere"));
			driver.findElement(By.id("answer13")).sendKeys("yes");
		}
		catch( NoSuchElementException e){
			driver.findElement(By.id("answer13")).sendKeys("no");
		}
		//14
		WebElement purpleBox = driver.findElement(By.id("purplebox"));
		if(purpleBox.isDisplayed()){
			driver.findElement(By.id("answer14")).sendKeys("yes");
		}
		else driver.findElement(By.id("answer14")).sendKeys("no");
		//15
		driver.findElement(By.linkText("click then wait")).click();
		WebElement element = (new WebDriverWait(driver, 10))
				   .until(ExpectedConditions.elementToBeClickable(By.linkText("click after wait")));
		element.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(1000);
		alert.accept();
		driver.findElement(By.id("submitbutton")).click();
    }
    
    @AfterClass
    public void tearDown() {
    	driver.close();
    }
    
}
