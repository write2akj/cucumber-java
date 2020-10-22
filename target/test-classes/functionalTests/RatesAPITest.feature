Feature: Foreign currency exchange rates api with currency conversion 
	Description: Rates API is a free service for current and historical foreign exchange rates built on top of data published by European Central Bank.

Scenario: Assert the response code is 200 and response body is not null for the latest API URL. 
	Given URL of Rates API 
	When The Latest Foreign Exchange rates are fetched 
	Then The API response code is 200 
	And The response body is correct 
	
	
Scenario: Assert the response code is 400 for the malformed API URL. 
	Given URL of Rates API 
	When An incorrect url is fetched 
	Then The API response code is 400 
	
Scenario: Assert the response code is 200 and response body is not null for the past date API URL. 
	Given URL of Rates API 
	When  Foreign Exchange rates for the date 2010-01-12 is fetched 
	Then The API response code is 200 
	And The response body is of the date 2010-1-12
	
Scenario: Assert the API response for future date will fetch the latest Foreign Exchange rates. 
	Given URL of Rates API 
	When  Foreign Exchange rates for future date is fetched 
	Then The current Foreign Exchange rates are fetched 