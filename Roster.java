package tuitionmanager;

/**
 * Class that defines the 'Roster' abstract data type; an instance of Roster can hold a list of Student objects.
 * @author Aatif Sayed, Pranav Tailor
 */
public class Roster {
    
    private Student[] roster;
    private int size;  // keep track of the number of students in the roster
    
    private static final int INITIAL_CAPACITY = 4;
    private static final int CAPACITY_INCREASE_INCREMENT = 4;
    private static final int NOT_FOUND = -1;
    private static final int MAXIMUM_FINANCIAL_AID = 10000;
    
    /**
     * Default constructor to instantiate an empty roster with a capacity of INITIAL_CAPACITY (or 4).
     */
    public Roster() {
        roster = new Student[INITIAL_CAPACITY];
        size = 0;  // Roster is initially empty 
    }
    
    /**
     * A private helper method to find a student in the roster.
     * @param student the student that the application wants to search for.
     * @return the index at which the student was found in the roster; if not found, return NOT_FOUND (or -1).
     */
    private int find(Student student) {
        if (size == 0)
            return NOT_FOUND;
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null && roster[i].equals(student))
                return i;
        }
        return NOT_FOUND;
    }
    
    /**
     * A private helper method to automatically increase the capacity of the roster by CAPACITY_INCREASE_INCREMENT (or 4).
     */
    private void grow() {
        Student[] newRoster = new Student[size + CAPACITY_INCREASE_INCREMENT];
        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }
    
    /**
     * This method adds a student to the roster if the student is not already in it.
     * Checks if there is space to add the student; if not, invokes the 'grow()' method.
     * @param student the student to be added to the roster.
     * @return true if the student was added successfully, false otherwise.
     */
    public boolean add(Student student) {
        if (find(student) != NOT_FOUND)
            return false;
        if (size == roster.length)
           grow(); 
        roster[size] = student;
        size++;
        return true;
    }
    
    /**
     * This method removes a student from the roster if the roster is not empty and if the student is found within it.
     * @param student the student to be removed from the roster.
     * @return true if the student was removed successfully, false otherwise.
     */
    public boolean remove(Student student) {
        if (size == 0)
            return false;
        int indexOfStudent = find(student);
        if (indexOfStudent != NOT_FOUND) {
            roster[indexOfStudent] = null;
            for (int i = indexOfStudent + 1; i < roster.length; i++)
                roster[i - 1] = roster[i];
            roster[roster.length - 1] = null;
            return true;
        }
        return false;
    }
    
    /**
     * This method calculates the tuition for every student in the roster by invoking the 'tuitionDue()' method which is implemented differently in different classes.
     */
    public void calculateTuition() {
        for (int i = 0; i < size; i++) {
            if (roster[i] != null)
                roster[i].tuitionDue();
        }
    }
    
    /**
     * This method processes student tuition payments and recalculates the tuition that the student owes.
     * @param studentToUpdate the student that the application wants to update the information for.
     * @param paymentAmount the amount of money that the student is paying towards tuition.
     * @param dateOfPayment the date that the student is making a payment on.
     * @return true if the payment was successfully processed, false otherwise.
     */
    public boolean processPayment(Student studentToUpdate, double paymentAmount, Date dateOfPayment) {
        for (Student student : roster) {
            if (student != null && student.equals(studentToUpdate)) {
                if (paymentAmount > student.getTuitionOwed()) {
                    System.out.println("Amount is greater than amount due.");
                    return false;
                }
                student.setTotalTuitionPaid(student.getTotalTuitionPaid() + paymentAmount);
                student.setTuitionOwed(student.getTuitionOwed() - paymentAmount);
                student.setLastPaymentDate(dateOfPayment);
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method sets the study abroad status of an international student to true.
     * @param internationalStudent the international student whose study abroad status we want to set to true.
     * @param studyAbroadStatus boolean value (true/false) that dictates whether or not an international student is studying abroad.
     * @return true if international student's study abroad status was changed successfully, false otherwise.
     */
    public boolean setStudyAbroadToTrue(International internationalStudent, boolean studyAbroadStatus) {
        int internationalStudentIndex = find(internationalStudent);
        if (internationalStudentIndex == NOT_FOUND) {
            System.out.println("Couldn't find the international student."); return false;
        }
        International studentConvertedToInternational = (International)roster[internationalStudentIndex];
        if (studentConvertedToInternational.getCredits() > TuitionManager.MINIMUM_INTERNATIONAL_CREDITS) {
            studentConvertedToInternational.setCredits(TuitionManager.MINIMUM_INTERNATIONAL_CREDITS);
        }
        studentConvertedToInternational.setTotalTuitionPaid(0);
        studentConvertedToInternational.setLastPaymentDate(null);
        studentConvertedToInternational.setStudyAbroadStatus(studyAbroadStatus);
        studentConvertedToInternational.tuitionDue();
        return true;
    }
    
    /**
     * This method sets the financial aid amount for a resident student.
     * @param residentStudent an instance of a 'Resident' student whose financial aid amount we are updating.
     * @param financialAidAmount the amount of financial aid to be set.
     * @return true if resident student's financial aid amount was successfully changed, false otherwise.
     */
    public boolean setFinancialAid(Resident residentStudent, double financialAidAmount) {
        int residentStudentIndex = find(residentStudent);
        if (residentStudentIndex == NOT_FOUND) {
            System.out.println("Student not in the roster."); return false;
        }
        if (!(roster[residentStudentIndex] instanceof Resident)) {
            System.out.println("Not a resident student."); return false;
        }
        Resident studentConvertedToResident = (Resident)roster[residentStudentIndex];
        if (financialAidAmount < 0 || financialAidAmount > MAXIMUM_FINANCIAL_AID) {
            System.out.println("Invalid amount."); return false;
        }
        if (studentConvertedToResident.getFinancialAid() != 0) {
            System.out.println("Awarded once already."); return false;
        }
        if (studentConvertedToResident.getCredits() < Student.MINIMUM_FULL_TIME_CREDITS) {
            System.out.println("Parttime student doesn't qualify for the award."); return false;
        }
        studentConvertedToResident.setFinancialAid(financialAidAmount);
        studentConvertedToResident.tuitionDue();
        return true;
    }
    
    /**
     * Print out the list of students in the roster.
     * If the collection is empty, print a message to the console letting the user know.
     */
    public void print() {
        if (size == 0) {
            System.out.println("Student roster is empty!"); return;
        }
        System.out.println("* list of students in the roster **");
        for (Student student : roster) {
            if (student != null)
                System.out.println(student.toString());
        }
        System.out.println("* end of roster **");
    }
    
    /**
     * Print out the list of students in the roster sorted by payment date from oldest to newest.
     * Students with the same payment date may be printed in any order and may not match expected output order.
     */
    public void printByPaymentDate() {
        if (size == 0) {
            System.out.println("Student roster is empty!"); return;
        }
        System.out.println("* list of students made payments ordered by payment date **");
        sortByPaymentDate(roster);
        for (int i = 0; i < size; i++) {
            if (roster[i] != null && roster[i].getLastPaymentDate() != null)
                System.out.println(roster[i].toString());
        }
        System.out.println("* end of roster **");
    }
    
    /**
     * Print out the list of students in the roster sorted by student name in alphabetical order.
     * Students with the same name may be printed in any order and may not match expected output order.
     */
    public void printByStudentName() {
        if (size == 0) {
            System.out.println("Student roster is empty!"); return;
        }
        System.out.println("* list of students ordered by name **");
        sortByStudentName(roster);
        for (Student student : roster) {
            if (student != null)
                System.out.println(student.toString());
        }
        System.out.println("* end of roster **");
    }
    
    /**
     * Private helper method to sort the list of students according to payment date (from oldest to newest).
     * @param listOfStudents the list of students to be sorted represented as an array of Student objects.
     */
    private void sortByPaymentDate(Student[] listOfStudents) {
        for (int i = 0; i < listOfStudents.length - 1; i++) {
            for (int j = i + 1; j < listOfStudents.length; j++) {
                if (listOfStudents[i] != null && listOfStudents[j] != null && listOfStudents[i].getLastPaymentDate() != null && 
                        listOfStudents[j].getLastPaymentDate() != null && 
                        listOfStudents[i].getLastPaymentDate().compareTo(listOfStudents[j].getLastPaymentDate()) > 0) {
                    Student temp = listOfStudents[i];
                    listOfStudents[i] = listOfStudents[j];
                    listOfStudents[j] = temp;
                }
            }
        }
    }
    
    /**
     * Private helper method to sort the list of students alphabetically according to name.
     * @param listOfStudents the list of students to be sorted represented as an array of Student objects.
     */
    private void sortByStudentName(Student[] listOfStudents) {
        for (int i = 0; i < listOfStudents.length - 1; i++) {
            for (int j = i + 1; j < listOfStudents.length; j++) {
                if (listOfStudents[i] != null && listOfStudents[j] != null && 
                        listOfStudents[i].getProfile().getName().compareTo(listOfStudents[j].getProfile().getName()) > 0) {
                    Student temp = listOfStudents[i];
                    listOfStudents[i] = listOfStudents[j];
                    listOfStudents[j] = temp;
                }
            }
        }
    }
}