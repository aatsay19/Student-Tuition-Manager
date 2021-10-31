package JUnitTesting;

import org.junit.Assert;
import org.junit.Test;

import tuitionmanager.International;
import tuitionmanager.Major;
import tuitionmanager.NonResident;
import tuitionmanager.Profile;
import tuitionmanager.Resident;
import tuitionmanager.Roster;
import tuitionmanager.Student;
import tuitionmanager.TriState;

/**
 * JUnit Testing class for Roster.add() and Roster.remove() methods.
 * @author Aatif Sayed, Pranav Tailor
 */
public class RosterJUnitTesting {
    
    @Test
    public void removeStudentFromEmptyRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        Student student = new Student(profile);
        Assert.assertFalse(roster.remove(student));
    }

    @Test
    public void removeInternationalFromEmptyRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        International student = new International(profile);
        Assert.assertFalse(roster.remove(student));
    }

    @Test
    public void removeResidentFromEmptyRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        Resident student = new Resident(profile);
        Assert.assertFalse(roster.remove(student));
    }

    @Test
    public void removeNonResidentFromEmptyRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        NonResident student = new NonResident(profile);
        Assert.assertFalse(roster.remove(student));
    }

    @Test
    public void removeTriStateFromEmptyRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        TriState student = new TriState(profile);
        Assert.assertFalse(roster.remove(student));
    }
    
    @Test
    public void addDuplicateStudentToRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        Student student = new Student(profile);
        Assert.assertTrue(roster.add(student));
        Assert.assertFalse(roster.add(student));
    }

    @Test
    public void addDuplicateInternationalToRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        International student = new International(profile);
        Assert.assertTrue(roster.add(student));
        Assert.assertFalse(roster.add(student));
    }

    @Test
    public void addDuplicateResidentToRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        Resident student = new Resident(profile);
        Assert.assertTrue(roster.add(student));
        Assert.assertFalse(roster.add(student));
    }

    @Test
    public void addDuplicateNonResidentToRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        NonResident student = new NonResident(profile);
        Assert.assertTrue(roster.add(student));
        Assert.assertFalse(roster.add(student));
    }

    @Test
    public void addDuplicateTriStateToRosterTest() {
        Roster roster = new Roster();
        Profile profile = new Profile("Aatif Sayed", Major.CS);
        TriState student = new TriState(profile);
        Assert.assertTrue(roster.add(student));
        Assert.assertFalse(roster.add(student));
    }
}