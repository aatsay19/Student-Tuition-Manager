package JUnitTesting;

import org.junit.Assert;
import org.junit.Test;

import tuitionmanager.International;
import tuitionmanager.Major;
import tuitionmanager.Profile;

/**
 * JUnit Testing class for International.tuitionDue() method.
 * @author Aatif Sayed, Pranav Tailor
 */
public class InternationalJUnitTesting {
    
    private static final double LIMIT = 1e-15;
    
    @Test
    public void tuitionDueNotCalledTest() {
        Profile profile = new Profile("Aatif Sayed", Major.IT);
        International student = new International(profile, 12, false);
        Assert.assertEquals(0, student.getTuitionOwed(), LIMIT);
    }

    @Test
    public void nonStudyAbroadFullTimeTest() {
        Profile profile = new Profile("Aatif Sayed", Major.IT);
        International student = new International(profile, 12, false);
        Assert.assertEquals(35655, student.getTuitionOwed(), LIMIT);
    }

    @Test
    public void nonStudyAbroadPartTimeTest() {
        Profile profile = new Profile("Aatif Sayed", Major.IT);
        International student = new International(profile, 6, false);
        Assert.assertEquals(11060.4, student.getTuitionOwed(), LIMIT);
    }

    @Test
    public void studyAbroadFullTimeTest() {
        Profile profile = new Profile("Aatif Sayed", Major.IT);
        International student = new International(profile, 12, false);
        Assert.assertEquals(5918, student.getTuitionOwed(), LIMIT);
    }
}