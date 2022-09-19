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

public class Wiki {
	private WebDriver driver;
	private WebDriverWait wt;
	@FindBy(css="input[placeholder='Search Wikipedia']")
	private WebElement searchBox;
	
	@FindBy(css=".mw-search-result-heading>a")
	private List<WebElement> searchResult;
	
	@FindBy(css=".infobox.vevent>tbody :nth-child(12) li")
	private WebElement releaseDate;
	
	@FindBy(css=".infobox.vevent>tbody :nth-child(14)>td")
	private WebElement country;
	
	public Wiki(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getMovie(String movieName) {
		searchBox.sendKeys(movieName,Keys.ENTER);
		
		wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector(".mw-search-result-heading>a")));
		for(WebElement movie: searchResult) {
			if(movie.getText().equals(movieName)) {
				movie.click();
				break;
			}
		}
	}
	
	public String getReleaseDate() {
		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector(".infobox.vevent>tbody :nth-child(12) li")));
		return releaseDate.getText();
	}
	
	public String getCountry() {
		return country.getText();
	}
}
