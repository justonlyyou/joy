/**
 * 
 */
package com.kvc.joy.commons.lang;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-29 上午11:43:56
 */
public class DateToolTest {

	private Date date;
	private Calendar cal;

	@Before
	public void setUp() throws Exception {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2010);
		cal.set(Calendar.MONTH, 7);
		cal.set(Calendar.DATE, 23);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 21);
		cal.set(Calendar.SECOND, 21);
		cal.set(Calendar.MILLISECOND, 111);
		date = cal.getTime();
	}

	@Test
	public void testFormatDate() {
		Assert.assertEquals("2010年08月23日 21时21分21秒", DateTool.formatDate(date, DateTool.FMT_CN_DAY_CN_SECOND));
		Assert.assertEquals("08/23/2010 21:21:21", DateTool.formatDate(date, "MM/dd/yyyy HH:mm:ss"));
	}

	@Test
	public void testSwapDates() {
		Date date1 = ((Calendar) cal.clone()).getTime();
		Calendar cal2 = (Calendar) cal.clone();
		cal2.add(Calendar.MILLISECOND, 100);
		Date date2 = cal2.getTime();
		DateTool.swapDates(date1, date2);
		Assert.assertEquals(date1.getTime(), date2.getTime() + 100);
	}
	
	@Test
	public void testGetNominalAge() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, 1);
		
		Assert.assertEquals(2, DateTool.getNominalAge(date, cal.getTime()));
		Assert.assertTrue(DateTool.getNominalAge(date, null) >= 4);
	}
	
	@Test
	public void testGetActualAge() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, 2);
		
		Assert.assertEquals(2, DateTool.getActualAge(date, cal.getTime()));
		Assert.assertTrue(DateTool.getActualAge(date, null) >= 2);
	}
	
	@Test
	public void testDaysBetween() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		cal.add(Calendar.MILLISECOND, 1);
		Assert.assertEquals(1, DateTool.daysBetween(cal.getTime(), date));
		
		cal.add(Calendar.MILLISECOND, -2);
		Assert.assertEquals(1, DateTool.daysBetween(cal.getTime(), date));
	}

}
