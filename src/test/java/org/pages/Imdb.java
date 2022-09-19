package org.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Imdb {
	private WebDriver driver;
	private WebDriverWait wt;
	@FindBy(xpath="//*[@placeholder='Search IMDb']")
	private WebElement searchBox;
	
	@FindBy(css=".result_text a")
	private List<WebElement> movieSelect;
	
	@FindBy(xpath="//a[text()='Release date']/../div//a")
	private WebElement releaseDate;
	
	@FindBy(xpath="//span[text()='Country of origin']/../div//a")
	private WebElement country;
	
	public Imdb(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getMovie(String movieName) {
		
		searchBox.sendKeys(movieName,Keys.ENTER);
		
		wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".result_text a")));
		
		for(WebElement mov: movieSelect) {
			if(mov.getText().contains(movieName)) {
				mov.click();
				break;
			}
		}
	}
	public String extractReleseDate() {
		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//span[text()='Country of origin']/../div//a")));
		return releaseDate.getText();
	}
	
	public String extractCountry() {
		return country.getText();
	}
}
