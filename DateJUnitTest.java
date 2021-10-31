package JUnitTesting;

import org.junit.Assert;
import org.junit.Test;

import tuitionmanager.Date;

import java.util.Calendar;

/**
 * JUnit Testing class for Date.isValid() method.
 * @author Aatif Sayed, Pranav Tailor
 */
public class DateJUnitTest {

    @Test
    public void smallOrInvalidYearTest() {
        Assert.assertFalse("smallOrInvalidYearTest failed", new Date("05/16/799").isValid());
        Assert.assertFalse("smallOrInvalidYearTest failed", new Date("06/07/2020").isValid());
        Assert.assertFalse("smallOrInvalidYearTest failed", new Date("05/09/-2020").isValid());
        System.out.println("smallOrInvalidYearTest passed.");
    }

    @Test
    public void largeOrInvalidYearTest() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        Assert.assertFalse("largeOrInvalidYearTest failed", new Date("10/15/" + String.valueOf(year + 1)).isValid());
        System.out.println("largeOrInvalidYearTest passed.");
    }

    @Test
    public void validYearTest() {
        Assert.assertTrue("validYearTest failed", new Date("07/12/2021").isValid());
        Assert.assertTrue("validYearTest failed", new Date("03/16/2021").isValid());
        System.out.println("validYearsTest passed.");
    }

    @Test
    public void invalidMonthTest() {
        Assert.assertFalse("invalidMonthTest failed", new Date("13/06/2021").isValid());
        Assert.assertFalse("invalidMonthTest failed", new Date("00/27/2021").isValid());
        System.out.println("invalidMonthTest passed.");
    }

    @Test
    public void februaryLeapYearTest() {
        Assert.assertFalse("februaryLeapYearTest failed", new Date("02/30/2020").isValid());
        Assert.assertFalse("februaryLeapYearTest failed", new Date("02/29/2021").isValid());
        System.out.println("februaryLeapYearTest passed.");
    }

    @Test
    public void smallOrInvalidDayTest() {
        Assert.assertFalse("smallOrInvalidDayTest failed", new Date("05/-1/2021").isValid());
        Assert.assertFalse("smallOrInvalidDayTest failed", new Date("05/00/2021").isValid());
        Assert.assertFalse("smallOrInvalidDayTest failed", new Date("05/32/2021").isValid());
        System.out.println("smallOrInvalidDayTest passed.");
    }

    @Test
    public void thirtyOneDayMonthFalseTest() {
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("1/32/2021").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("3/32/2021").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("5/32/2021").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("7/32/2021").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("8/32/2021").isValid());
        System.out.println("thirtyOneDayMonthFalseTest passed.");
    }

    @Test
    public void thirtyOneDayMonthTrueTest() {
        Assert.assertTrue("thirtyOneDayMonthTrueTest failed", new Date("1/31/2021").isValid());
        Assert.assertTrue("thirtyOneDayMonthTrueTest failed", new Date("3/31/2021").isValid());
        Assert.assertTrue("thirtyOneDayMonthTrueTest failed", new Date("5/31/2021").isValid());
        Assert.assertTrue("thirtyOneDayMonthTrueTest failed", new Date("7/31/2021").isValid());
        Assert.assertTrue("thirtyOneDayMonthTrueTest failed", new Date("8/31/2021").isValid());
        System.out.println("thirtyOneDayMonthTrueTest passed.");
    }

    @Test
    public void thirtyDayMonthFalseTest() {
        Assert.assertFalse("thirtyDayMonthFalseTest failed", new Date("4/31/2021").isValid());
        Assert.assertFalse("thirtyDayMonthFalseTest failed", new Date("6/31/2021").isValid());
        Assert.assertFalse("thirtyDayMonthFalseTest failed", new Date("9/31/2021").isValid());
        System.out.println("thirtyDayMonthFalseTest passed.");
    }

    @Test
    public void thirtyDayMonthTrueTest() {
        Assert.assertTrue("thirtyDayMonthTrueTest failed", new Date("4/30/2021").isValid());
        Assert.assertTrue("thirtyDayMonthTrueTest failed", new Date("6/30/2021").isValid());
        System.out.println("thirtyDayMonthTrueTest passed.");
    }
}