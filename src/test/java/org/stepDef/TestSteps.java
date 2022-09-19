package org.stepDef;



import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.Imdb;
import org.pages.Wiki;
import org.utils.ReadProp;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestSteps {
	public Imdb imPgOb;
	public Wiki wiPgOb;
	public WebDriver driver;
	
	@Given("User opens the browser")
	public void user_opens_the_browser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		
	}

	@When("Hit URL {string} and navigate to movie page on Wiki")
	public void hit_url_and_navigate_to_movie_page_on_Wiki(String string) {
		wiPgOb = new Wiki(driver);
		driver.get(string);
	}

	@Then("Extract Release Date from Imdb")
	public void extract_release_date_from_imdb() {
		imPgOb.getMovie(ReadProp.getProp("movieName"));
		Assert.assertEquals(ReadProp.getProp("Release_Date_on_IMDB"), imPgOb.extractReleseDate());
		ReadProp.setProp("Release_Date_on_IMDB", imPgOb.extractReleseDate(),ReadProp.getProp("movieName"));
	}

	@Then("Extract Country from Imdb")
	public void extract_country_from_imdb() {
		Assert.assertEquals(ReadProp.getProp("Country_on_IMDB"),imPgOb.extractCountry() );
		ReadProp.setProp("Country_on_IMDB", imPgOb.extractCountry(),ReadProp.getProp("movieName"));
	}

	@When("Hit URL {string} and navigate to movie page on IMDB")
	public void hit_url_and_navigate_to_movie_page_on_IMDB(String string) {
		imPgOb = new Imdb(driver);
		driver.get(string);
	}

	@Then("Extract Release Date from Wiki")
	public void extract_release_date_from_wiki() {
		wiPgOb.getMovie(ReadProp.getProp("movieName"));
		Assert.assertEquals(ReadProp.getProp("Release_Date_on_Wiki"), wiPgOb.getReleaseDate());
		ReadProp.setProp("Release_Date_on_Wiki", wiPgOb.getReleaseDate(),ReadProp.getProp("movieName"));
	}

	@Then("Extract Country from Wiki")
	public void extract_country_from_wiki() {
		Assert.assertEquals(ReadProp.getProp("Country_on_Wiki"), wiPgOb.getCountry());
		ReadProp.setProp("Country_on_Wiki", wiPgOb.getCountry(),ReadProp.getProp("movieName"));
		
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
