package utils;

import java.io.File;

import pojo.ExchangeRate;

/**
 * Class to test json reader
 */
public class DataLoader {

	public static ExchangeRate getExchangeRateTestData(int testDataSet) {

		ClassLoader classLoader = DataLoader.class.getClassLoader();

		File file = new File(classLoader.getResource("TestData/ExchangeRateTestData.json").getFile());

		ExchangeRate exchangeRate = TestDataReader.loadJsonFile(ExchangeRate.class, file);

		return exchangeRate;
	}
}