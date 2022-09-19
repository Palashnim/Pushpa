Feature: TestVagrant Pushpa test

	Background: 
		Given User opens the browser
		
	@Imdb
  Scenario: Extract data from IMDB
    When Hit URL "https://www.imdb.com/" and navigate to movie page on IMDB
    Then Extract Release Date from Imdb
    And Extract Country from Imdb
  
  @Wiki
  Scenario: Extract data from Wiki
    When Hit URL "https://en.wikipedia.org/" and navigate to movie page on Wiki
    Then Extract Release Date from Wiki
    And Extract Country from Wiki

