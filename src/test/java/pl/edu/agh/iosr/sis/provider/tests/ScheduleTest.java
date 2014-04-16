package pl.edu.agh.iosr.sis.provider.tests;

import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.quartz-test-cfg.xml" })
public class ScheduleTest {

	private static final String CRON_TRIGGER_NAME = "cronTrigger";

	@Autowired
	private SchedulerFactoryBean quartzScheduler;

	@Autowired
	private TimeZone timeZone;

	@Test
	public void scheduleTest() throws SchedulerException {
		DateTimeZone dtz = DateTimeZone.forTimeZone(timeZone);
		Trigger firstTrigger = quartzScheduler.getScheduler().getTrigger(
				new TriggerKey(CRON_TRIGGER_NAME));
		DateTime tomorrow = new DateTime().withTimeAtStartOfDay().plusDays(1);
		Date next = firstTrigger.getFireTimeAfter(tomorrow.toDate());
		DateTime expected = tomorrow.plusMinutes(5).toDateTime(dtz);
		Assert.assertEquals(expected.toDate(), next);

		next = firstTrigger.getFireTimeAfter(next);
		expected = expected.plusMinutes(5);
		Assert.assertEquals(expected.toDate(), next);
	}

}
