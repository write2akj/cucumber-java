package utils;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestUtils {

	public ZonedDateTime getCurrentEuropianDate() {
		Clock clock = Clock.system(ZoneId.of("Europe/Berlin"));
		return ZonedDateTime.now(clock);
	}
}
