package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.ExchangeRate;
import utils.TestUtils;

public class Steps {

	private static final String BASE_URL = "https://api.ratesapi.io/api";

	private static RequestSpecification request;
	private static Response response;
	private TestUtils testUtils = new TestUtils();

	@Given("URL of Rates API")
	public void url_of_Rates_API() {
		RestAssured.baseURI = BASE_URL;
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	@When("The Latest Foreign Exchange rates are fetched")
	public void the_Latest_Foreign_Exchange_rates_are_fetched() {
		response = request.get("/latest");
	}

	@Then("The API response code is {int}")
	public void the_API_response_code_is(Integer int1) {
		response.then().assertThat().statusCode(int1);
	}

	@Then("The response body is correct")
	public void the_response_body_is_correct() {
		ExchangeRate actualExchangeRate = response.then().assertThat().statusCode(200).extract().as(ExchangeRate.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ZonedDateTime now = testUtils.getCurrentEuropianDate();
		assertEquals(dtf.format(now.minusDays(1)), actualExchangeRate.getDate());
	}

	@Then("The response body is of the date {int}-{int}-{int}")
	public void the_response_body_is_of_the_date(Integer int1, Integer int2, Integer int3) throws ParseException {
		ExchangeRate actualExchangeRate = response.then().assertThat().statusCode(200).extract().as(ExchangeRate.class);
		String strDate = int1 + "/" + int2 + "/" + int3;
		Date date = new SimpleDateFormat("yyyy/MM/dd").parse(strDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals(sdf.format(date), actualExchangeRate.getDate());
	}

	@When("An incorrect url is fetched")
	public void an_incorrect_url_is_fetched() {
		response = request.get("/malformed");
	}

	@Then("The current Foreign Exchange rates are fetched")
	public void the_current_Foreign_Exchange_rates_are_fetched() {
		ExchangeRate actualExchangeRate = response.then().assertThat().statusCode(200).extract().as(ExchangeRate.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ZonedDateTime now = testUtils.getCurrentEuropianDate();
		assertEquals(dtf.format(now.minusDays(1)), actualExchangeRate.getDate());
	}

	@When("Foreign Exchange rates for the date {int}-{int}-{int} is fetched")
	public void foreign_Exchange_rates_for_the_date_is_fetched(Integer int1, Integer int2, Integer int3) {
		response = request.get("/" + int1 + "-" + int2 + "-" + int3);
	}

	@When("Foreign Exchange rates for future date is fetched")
	public void foreign_Exchange_rates_for_future_date_is_fetched() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ZonedDateTime now = testUtils.getCurrentEuropianDate();
		response = request.get("/" + dtf.format(now.plusDays(1)));
	}
}