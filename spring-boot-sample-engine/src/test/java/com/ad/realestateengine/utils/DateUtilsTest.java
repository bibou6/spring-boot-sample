package com.ad.realestateengine.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DateUtilsTest {
	
	private Logger logger = LoggerFactory.getLogger(DateUtilsTest.class);
	
	
	@Test
	public void convertDateToDateWithTimeZoneTest() {
		logger.info("[TEST] - convertDateToDateWithTimeZoneTest");
		Date now = new Date();
		Date date = DateUtils.withTimezone(now);
		assertEquals(now.toInstant(), date.toInstant());
	}
	
	
	@Test
	public void convertLocaleDateToDateWithTimeZoneTest() {
		logger.info("[TEST] - convertLocaleDateToDateWithTimeZoneTest");
		LocalDate now = LocalDate.now();
		Date date = DateUtils.withTimezone(now);
		assertEquals(now.atStartOfDay().atZone(ZoneId.of("Europe/Paris")).toInstant(), date.toInstant());
	}
	

}
