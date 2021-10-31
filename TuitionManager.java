package tuitionmanager;

import java.util.Scanner;

/**
 * Class that defines the 'Tuition Manager' abstract data type which can manage the tuition of various types of students.
 * @author Aatif Sayed, Pranav Tailor
 */
public class TuitionManager {
    
    private Scanner userInput;  // will be used to scan, store, and refer to user input
    private Roster roster;
    
    private static final int MINIMUM_CREDITS = 3;
    private static final int MAXIMUM_CREDITS = 24;
    private static final int CREDIT_HOURS_MISSING_ARGUMENTS = 3;
    private static final int F_COMMAND_NUMBER_OF_ARGUMENTS = 4;
    private static final int MISSING_DATA_ARGUMENTS = 5;
    public static final int MINIMUM_INTERNATIONAL_CREDITS = 12;
    
    /**
     * Default constructor to instantiate a tuition manager which accepts user input via a 'Scanner' instance and instantiates a Roster object.
     */
    public TuitionManager() {
        userInput = new Scanner(System.in);
        roster = new Roster();
    }
    
    /**
     * This method runs the tuition manager which continually takes user commands until the command "Q" is received.
     * Takes a line of user input and creates an array of Strings, containing a command and relevant data, by parsing comma-delimited lines.
     */
    public void run() {
        System.out.println("Tuition Manager starts running.\n\n");
        while (userInput.hasNext()) {
            String[] commandTokens = userInput.nextLine().split(",");
            executeCommand(commandTokens);
            if (commandTokens[0].equals("Q")) {
                userInput.close();
                break;
            }
        }
        System.out.println("Tuition Manager terminated.");
    }
    
    /**
     * Private helper method to check if user command is valid, execute command if possible, and print to the console.
     * If command is invalid, print a message to the console letting the user know.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommand(String[] commandTokens) {
        switch (commandTokens[0]) {
            case "AR" : executeCommandAR(commandTokens);
                break;
            case "AN" : executeCommandAN(commandTokens);
                break;
            case "AT" : executeCommandAT(commandTokens);
                break;
            case "AI" : executeCommandAI(commandTokens);
                break;
            case "R" : executeCommandR(commandTokens);
                break;
            case "C" : executeCommandC(commandTokens);
                break;
            case "T" : executeCommandT(commandTokens);
                break;
            case "S" : executeCommandS(commandTokens);
                break;
            case "F" : executeCommandF(commandTokens);
                break;
            case "P" : roster.print();
                break;
            case "PT" : roster.printByPaymentDate();
                break;
            case "PN" : roster.printByStudentName();
                break;
            case "" :
                System.out.println();
                break;
            case "Q" : 
                break;
            default : System.out.println("Command '" + commandTokens[0] + "' not supported!");
        }
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'AR' or add resident student command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandAR(String[] commandTokens) {
        if (!checkArgumentsForAdding(commandTokens))
            return;
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        Resident newResident = new Resident(newProfile, Integer.parseInt(commandTokens[3]));
        if (roster.add(newResident))
            System.out.println("Student added.");
        else
            System.out.println("Student is already in the roster.");
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'AN' or add non-resident student command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandAN(String[] commandTokens) {
        if (!checkArgumentsForAdding(commandTokens))
            return;
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        NonResident newNonResident = new NonResident(newProfile, Integer.parseInt(commandTokens[3]));
        if (roster.add(newNonResident))
            System.out.println("Student added.");
        else
            System.out.println("Student is already in the roster.");
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'AT' or add tri-state student command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandAT(String[] commandTokens) {
        if (!checkArgumentsForAdding(commandTokens))
            return;
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        TriState newTriStateStudent = new TriState(newProfile, Integer.parseInt(commandTokens[3]), 
                State.convertStringToState(commandTokens[4]));
        if (roster.add(newTriStateStudent))
            System.out.println("Student added.");
        else
            System.out.println("Student is already in the roster.");
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'AI' or add international student command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandAI(String[] commandTokens) {
        if (!checkArgumentsForAdding(commandTokens))
            return;
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        International newInternationalStudent = new International(newProfile, Integer.parseInt(commandTokens[3]), 
                Boolean.parseBoolean(commandTokens[4]));
        if (roster.add(newInternationalStudent))
            System.out.println("Student added.");
        else
            System.out.println("Student is already in the roster.");
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'R' or remove command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandR(String[] commandTokens) {
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        Student newStudent = new Student(newProfile);
        if (roster.remove(newStudent))
            System.out.println("Student removed from the roster.");
        else
            System.out.println("Student is not in the roster.");
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'C' or calculate tuition command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandC(String[] commandTokens) {
        roster.calculateTuition();
        System.out.println("Calculation completed.");
        return;
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'T' or pay tuition command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandT(String[] commandTokens) {
        if (!checkArgumentsForPaying(commandTokens))
            return;
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        Student newStudent = new Student(newProfile);
        double payment = Double.parseDouble(commandTokens[3]);
        Date paymentDate = new Date(commandTokens[4]);
        if (roster.processPayment(newStudent, payment, paymentDate))
            System.out.println("Payment applied.");
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'S' or set study abroad status to true command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandS(String[] commandTokens) {
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        International newInternationalStudent = new International(newProfile);
        if (roster.setStudyAbroadToTrue(newInternationalStudent, Boolean.parseBoolean(commandTokens[3])))
            System.out.println("Tuition updated.");
    }
    
    /**
     * Private helper method to execute and print to the console the results of the 'F' or set financial aid amount command.
     * @param commandTokens an array of Strings, containing a command and relevant data, found by parsing comma-delimited lines.
     */
    private void executeCommandF(String[] commandTokens) {
        if (commandTokens.length < F_COMMAND_NUMBER_OF_ARGUMENTS) {
            System.out.println("Missing the amount."); return;
        }
        Profile newProfile = new Profile(commandTokens[1], Major.convertStringToMajor(commandTokens[2]));
        Resident newResidentStudent = new Resident(newProfile);
        double financialAidAmount = Double.parseDouble(commandTokens[3]);
        if (roster.setFinancialAid(newResidentStudent, financialAidAmount))
            System.out.println("Tuition updated.");
    }
    
    /**
     * Private helper method that checks if there are any errors with the arguments in a given command.
     * @param commandTokens an array of Strings containing a command and relevant data found by parsing comma-delimited lines.
     * @return true if there are no errors with the arguments of a given command, false otherwise.
     */
    private boolean checkArgumentsForAdding(String[] commandTokens) {
        if (((commandTokens[0].equals("AR") || commandTokens[0].equals("AN")) && commandTokens.length < CREDIT_HOURS_MISSING_ARGUMENTS)
                 || (commandTokens[0].equals("AT") && commandTokens.length < MISSING_DATA_ARGUMENTS)) {
            System.out.println("Missing data in command line."); return false;
        }
        if (commandTokens.length == CREDIT_HOURS_MISSING_ARGUMENTS) {
            System.out.println("Credit hours missing."); return false;
        }
        if (Major.convertStringToMajor(commandTokens[2]) == null) {
            System.out.println("'" + commandTokens[2] + "' is not a valid major."); return false;
        }
        if (commandTokens[0].equals("AT") && State.convertStringToState(commandTokens[4]) == null) {
            System.out.println("Not part of the tri-state area."); return false;
        }
        try {
            int numberOfCredits = Integer.parseInt(commandTokens[3]);
            if (numberOfCredits < 0) {
                System.out.println("Credit hours cannot be negative."); return false;
            }
            if (numberOfCredits < MINIMUM_CREDITS) {
                System.out.println("Minimum credit hours is " + MINIMUM_CREDITS + "."); return false;
            }
            if (numberOfCredits > MAXIMUM_CREDITS) {
                System.out.println("Credit hours exceed the maximum " + MAXIMUM_CREDITS + "."); return false;
            }
            if (commandTokens[0].equals("AI") && numberOfCredits < MINIMUM_INTERNATIONAL_CREDITS) {
                System.out.println("International students must enroll at least " + MINIMUM_INTERNATIONAL_CREDITS + " credits.");
                return false;
            }
        }
        catch (Exception exception) {
            System.out.println("Invalid credit hours."); return false;
        }
        return true;
    }
    
    /**
     * Private helper method that checks if there are any errors with the arguments in a given command.
     * @param commandTokens an array of Strings containing a command and relevant data found by parsing comma-delimited lines.
     * @return true if there are no errors with the arguments of a given command, false otherwise.
     */
    private boolean checkArgumentsForPaying(String[] commandTokens) {
        if (commandTokens.length == CREDIT_HOURS_MISSING_ARGUMENTS) {
            System.out.println("Payment amount missing."); return false;
        }
        if (Double.parseDouble(commandTokens[3]) <= 0) {
            System.out.println("Invalid amount."); return false;
        }
        Date paymentDate = new Date(commandTokens[4]);
        if (!paymentDate.isValid()) {
            System.out.println("Payment date invalid."); return false;
        }
        return true;
    }
}