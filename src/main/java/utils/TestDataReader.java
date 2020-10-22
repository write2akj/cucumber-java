package utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class which provides methods to read json test data files.
 */
public class TestDataReader {

	private static final Logger LOGGER = Logger.getLogger(TestDataReader.class);

	/**
	 * Maps json file to java class to access the json data
	 * 
	 * @param cls  Class to which json needs to be mapped
	 * @param path Path of json file
	 * @return Instance of java class where json is mapped
	 */
	public static <T> T loadJsonFile(Class<T> cls, File file) {

		LOGGER.info("Started loading test json file");

		ObjectMapper mapper = new ObjectMapper();

		try {

			T obj = mapper.readValue(file, cls);

			return obj;

		} catch (JsonMappingException e) {

			LOGGER.debug("Could not map the provided json at path : " + file.getPath());

			e.printStackTrace();

			return null;

		} catch (IOException e) {

			LOGGER.debug("Could not find the provided json at path : " + file.getPath());

			e.printStackTrace();

			return null;
		}
	}
}
