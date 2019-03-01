/**
 * TicketSystem
 * This class contains all the UI for the Prom/Semi project
 * @author Arjun Pillai and Joyce Huang
 * @since Feb 22 2019
 */

//Imports
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.OverlayLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;

public class TicketingSystem extends JFrame{

    // Private Variables //

    //UI Variables
    private JFrame frame = new JFrame("Ticketing System");
    private JLayeredPane overallPanel = new JLayeredPane();
    private JPanel mainPanel = new JPanel();
    private JPanel backgroundPanel = new JPanel();
    private JPanel loadingScreenPanel = new JPanel();
    private JPanel addStudentsPanel = new JPanel();
    private JPanel modifyStudentsPanel = new JPanel();
    private JPanel removeModifyClearPanel = new JPanel();
    private JPanel modifyStudents2Panel = new JPanel();
    private JPanel removeStudentsPanel = new JPanel();
    private JLabel background, loadingIcon;
    private JButton addStudentButton, modifyStudentsButton, displayFloorPlanButton, generateFloorPlanButton, exitProgramButton;
    private JButton modifyStudentsButton2, removeStudentButton, clearStudentsButton;
    private JButton submitInfoButton, submitInfoModifyButton, submitInfoRemoveButton, submitInfoEditButton;
    private JButton returnButton, returnFromModifyButton, returnFromModifyButton2, returnFromEditButton, returnFromRemoveButton;
    private JTextField nameField, studentNumberField, dietaryRestrictionsField, friendField, modifyStudentField, removeStudentField;
    private JTextField modifyNameField, modifyStudentNumberField, modifyDietaryRestrictionsField, modifyFriendField;
    private JLabel nameFieldLabel,studentNumberFieldLabel,dietaryRestrictionsFieldLabel,friendFieldLabel,removeStudentNumberFieldLabel;
    private JLabel modifyNameFieldLabel,modifyStudentNumberFieldLabel,modifyDietaryRestrictionsFieldLabel,modifyFriendFieldLabel;
    private JLabel modifyStudentFieldLabel;

    //Array Lists storing students and tables
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<String> studentNumbers = new ArrayList<String>();
    private ArrayList<Table> tables = new ArrayList<Table>();

    //Used for creating students
    private Student tempStudent;
    private String name, studentNumber, tempDiet, tempFriend, tempWord, removeStudentNumber, outputtedStudentNumbers, outputtedDietaryRestrictions;
    private String dietaryRestrictionsText, friendStudentNumbersText;
    private ArrayList<String> dietaryRestrictions = new ArrayList<>();
    private ArrayList<String> friendStudentNumbers = new ArrayList<>();
    private int studentToModify;

    //Input/Output
    private Scanner input;
    private PrintWriter output;

    //images
    private static ImageIcon addStudentPicture = new ImageIcon("addStudentButton.png");
    private static ImageIcon displayFloorPlanPicture = new ImageIcon("displayFloorPlanButton.png");
    private static ImageIcon exitProgramPicture = new ImageIcon("exitProgramButton.png");
    private static ImageIcon removeStudentPicture = new ImageIcon("removeStudentButton.png");
    private static ImageIcon returnPicture = new ImageIcon("returnButton.png");
    private static ImageIcon submitInfoPicture = new ImageIcon("submitInfoButton.png");
    private static ImageIcon backgroundPicture = new ImageIcon("backgroundPicture.png");
    private static ImageIcon generateFloorPlanPicture = new ImageIcon("generateFloorPlanButton.png");
    private static ImageIcon loadingIconPicture = new ImageIcon("loadingIcon.gif");
    private static ImageIcon modifyStudentsPicture = new ImageIcon("modifyStudentsButton.png");
    private static ImageIcon clearStudentsPicture = new ImageIcon("clearStudentsButton.png");

    //external implementation
    private SeatingAlg seatingAlgorithm;
    private FloorPlan plan;
    private static final int TABLE_LIMIT = 10;

    /**
     * Constructor for the ticketing system
     */
    TicketingSystem() {

        //Read save file
        readFile();

        //Create external classes
        seatingAlgorithm = new SeatingAlg();
        plan = new FloorPlan();

        //Create UI
        makeUIComponents();

    }



    /** 
     * This method creates the main menu
     */
    private void makeUIComponents() {

        //Create JFrame
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Create JLayeredPane to contain all other panels
        overallPanel.setLayout(new OverlayLayout(overallPanel));

        //Create background and panel to place background on
        background = new JLabel(backgroundPicture);
        backgroundPanel.add(background);

        //Set all panel sizes
        addStudentsPanel.setSize(500, 500);
        removeStudentsPanel.setSize(500, 500);
        loadingScreenPanel.setSize(500, 500);
        modifyStudentsPanel.setSize(500, 500);
        removeModifyClearPanel.setSize(500, 500);
        modifyStudents2Panel.setSize(500, 500);

        //Set all panel layouts
        mainPanel.setLayout(null);
        addStudentsPanel.setLayout(null);
        removeStudentsPanel.setLayout(null);
        loadingScreenPanel.setLayout(null);
        modifyStudentsPanel.setLayout(null);
        removeModifyClearPanel.setLayout(null);
        modifyStudents2Panel.setLayout(null);

        //Set all panel opacity
        mainPanel.setOpaque(false);
        addStudentsPanel.setOpaque(false);
        removeStudentsPanel.setOpaque(false);
        loadingScreenPanel.setOpaque(false);
        modifyStudentsPanel.setOpaque(false);
        removeModifyClearPanel.setOpaque(false);
        modifyStudents2Panel.setOpaque(false);

        //Create loading screen
        loadingIcon = new JLabel(loadingIconPicture);
        loadingIcon.setBounds(150, 150, 200, 200);
        loadingScreenPanel.add(loadingIcon);

        //Create buttons
        addStudentButton = new JButton(addStudentPicture);
        modifyStudentsButton = new JButton(modifyStudentsPicture);
        generateFloorPlanButton = new JButton(generateFloorPlanPicture);
        displayFloorPlanButton = new JButton(displayFloorPlanPicture);
        exitProgramButton = new JButton(exitProgramPicture);
        modifyStudentsButton2 = new JButton(modifyStudentsPicture);
        removeStudentButton = new JButton(removeStudentPicture);
        clearStudentsButton = new JButton(clearStudentsPicture);
        submitInfoButton = new JButton(submitInfoPicture);
        submitInfoModifyButton = new JButton(submitInfoPicture);
        submitInfoRemoveButton = new JButton(submitInfoPicture);
        submitInfoEditButton = new JButton(submitInfoPicture);
        returnButton = new JButton(returnPicture);
        returnFromModifyButton = new JButton(returnPicture);
        returnFromModifyButton2 = new JButton(returnPicture);
        returnFromEditButton = new JButton(returnPicture);
        returnFromRemoveButton = new JButton(returnPicture);

        //Delete borders of buttons
        addStudentButton.setBorder(null);
        modifyStudentsButton.setBorder(null);
        displayFloorPlanButton.setBorder(null);
        generateFloorPlanButton.setBorder(null);
        exitProgramButton.setBorder(null);
        modifyStudentsButton2.setBorder(null);
        removeStudentButton.setBorder(null);
        clearStudentsButton.setBorder(null);
        submitInfoButton.setBorder(null);
        submitInfoModifyButton.setBorder(null);
        submitInfoRemoveButton.setBorder(null);
        submitInfoEditButton.setBorder(null);
        returnButton.setBorder(null);
        returnFromModifyButton.setBorder(null);
        returnFromModifyButton2.setBorder(null);
        returnFromEditButton.setBorder(null);
        returnFromRemoveButton.setBorder(null);

        //Set button locations
        addStudentButton.setBounds(135, 10, 230, 50);
        modifyStudentsButton.setBounds(135, 110, 231, 50);
        displayFloorPlanButton.setBounds(135, 310, 230, 50);
        generateFloorPlanButton.setBounds(135, 210, 231, 50);
        exitProgramButton.setBounds(135, 410, 231, 50);
        modifyStudentsButton2.setBounds(135, 10, 230, 50);
        removeStudentButton.setBounds(135, 110, 231, 50);
        clearStudentsButton.setBounds(135, 210, 231, 50);
        submitInfoButton.setBounds(135, 300, 231, 50);
        submitInfoModifyButton.setBounds(135, 300, 231, 50);
        submitInfoRemoveButton.setBounds(135, 300, 231, 50);
        submitInfoEditButton.setBounds(135, 300, 231, 50);
        returnButton.setBounds(135, 375, 231, 50);
        returnFromModifyButton.setBounds(135, 375, 231, 50);
        returnFromModifyButton2.setBounds(135, 375, 231, 50);
        returnFromEditButton.setBounds(135, 375, 231, 50);
        returnFromRemoveButton.setBounds(135, 375, 231, 50);

        //Add action listeners to buttons
        ActionListener listener = new ButtonListener();
        addStudentButton.addActionListener(listener);
        modifyStudentsButton.addActionListener(listener);
        displayFloorPlanButton.addActionListener(listener);
        generateFloorPlanButton.addActionListener(listener);
        exitProgramButton.addActionListener(listener);
        modifyStudentsButton2.addActionListener(listener);
        removeStudentButton.addActionListener(listener);
        clearStudentsButton.addActionListener(listener);
        submitInfoButton.addActionListener(listener);
        submitInfoModifyButton.addActionListener(listener);
        submitInfoRemoveButton.addActionListener(listener);
        submitInfoEditButton.addActionListener(listener);
        returnButton.addActionListener(listener);
        returnFromModifyButton.addActionListener(listener);
        returnFromModifyButton2.addActionListener(listener);
        returnFromEditButton.addActionListener(listener);
        returnFromRemoveButton.addActionListener(listener);

        //Create JTextFields
        nameField = new JTextField(20);
        studentNumberField = new JTextField(20);
        dietaryRestrictionsField = new JTextField(20);
        friendField = new JTextField(20);
        removeStudentField = new JTextField(20);
        modifyStudentField = new JTextField(20);
        modifyNameField = new JTextField(20);
        modifyStudentNumberField = new JTextField(20);
        modifyDietaryRestrictionsField = new JTextField(20);
        modifyFriendField = new JTextField(20);

        //Create labels for text fields
        nameFieldLabel = new JLabel("Enter student name.");
        studentNumberFieldLabel = new JLabel("Enter student number.");
        dietaryRestrictionsFieldLabel = new JLabel("Enter student's dietary restrictions.");
        friendFieldLabel = new JLabel("Enter student number of friends.");
        modifyNameFieldLabel = new JLabel("Enter student name.");
        modifyStudentNumberFieldLabel = new JLabel("Enter student number.");
        modifyDietaryRestrictionsFieldLabel = new JLabel("Enter student's dietary restrictions.");
        modifyFriendFieldLabel = new JLabel("Enter student number of friends.");
        removeStudentNumberFieldLabel = new JLabel("Enter the student number to remove.");
        modifyStudentFieldLabel = new JLabel("Enter the student number to modify.");

        //Set label colour to gray
        nameFieldLabel.setForeground(Color.gray);
        studentNumberFieldLabel.setForeground(Color.gray);
        dietaryRestrictionsFieldLabel.setForeground(Color.gray);
        friendFieldLabel.setForeground(Color.gray);
        removeStudentNumberFieldLabel.setForeground(Color.gray);
        modifyStudentFieldLabel.setForeground(Color.gray);
        modifyNameFieldLabel.setForeground(Color.gray);
        modifyStudentNumberFieldLabel.setForeground(Color.gray);
        modifyDietaryRestrictionsFieldLabel.setForeground(Color.gray);
        modifyFriendFieldLabel.setForeground(Color.gray);

        //Set text field locations
        nameField.setBounds(135, 40, 230, 20);
        studentNumberField.setBounds(135, 100, 230, 20);
        dietaryRestrictionsField.setBounds(135, 160, 230, 20);
        friendField.setBounds(135, 220, 230, 20);
        removeStudentField.setBounds(135, 80, 230, 20);
        modifyStudentField.setBounds(135, 80, 230, 20);
        modifyNameField.setBounds(135, 40, 230, 20);
        modifyStudentNumberField.setBounds(135, 100, 230, 20);
        modifyDietaryRestrictionsField.setBounds(135, 160, 230, 20);
        modifyFriendField.setBounds(135, 220, 230, 20);

        //Set label locations
        nameFieldLabel.setBounds(135, 20, 230, 20);
        studentNumberFieldLabel.setBounds(135, 80, 230, 20);
        dietaryRestrictionsFieldLabel.setBounds(135, 140, 230, 20);
        friendFieldLabel.setBounds(135, 200, 230, 20);
        modifyStudentFieldLabel.setBounds(135,60,230,20);
        modifyNameFieldLabel.setBounds(135, 20, 230, 20);
        modifyStudentNumberFieldLabel.setBounds(135, 80, 230, 20);
        modifyDietaryRestrictionsFieldLabel.setBounds(135, 140, 230, 20);
        modifyFriendFieldLabel.setBounds(135, 200, 230, 20);
        removeStudentNumberFieldLabel.setBounds(135, 60, 230, 20);

        //Add buttons to panels
        mainPanel.add(addStudentButton);
        mainPanel.add(modifyStudentsButton);
        mainPanel.add(generateFloorPlanButton);
        mainPanel.add(displayFloorPlanButton);
        mainPanel.add(exitProgramButton);
        addStudentsPanel.add(submitInfoButton);
        addStudentsPanel.add(returnButton);
        removeModifyClearPanel.add(returnFromModifyButton);
        removeModifyClearPanel.add(removeStudentButton);
        removeModifyClearPanel.add(modifyStudentsButton2);
        removeModifyClearPanel.add(clearStudentsButton);
        removeStudentsPanel.add(returnFromRemoveButton);
        removeStudentsPanel.add(submitInfoRemoveButton);
        modifyStudentsPanel.add(submitInfoModifyButton);
        modifyStudentsPanel.add(returnFromModifyButton2);
        modifyStudents2Panel.add(submitInfoEditButton);
        modifyStudents2Panel.add(returnFromEditButton);

        //Add text fields to panels
        addStudentsPanel.add(nameField);
        addStudentsPanel.add(studentNumberField);
        addStudentsPanel.add(dietaryRestrictionsField);
        addStudentsPanel.add(friendField);
        removeStudentsPanel.add(removeStudentField);
        modifyStudentsPanel.add(modifyStudentField);
        modifyStudents2Panel.add(modifyNameField);
        modifyStudents2Panel.add(modifyStudentNumberField);
        modifyStudents2Panel.add(modifyDietaryRestrictionsField);
        modifyStudents2Panel.add(modifyFriendField);

        //Add labels to panel
        addStudentsPanel.add(nameFieldLabel);
        addStudentsPanel.add(studentNumberFieldLabel);
        addStudentsPanel.add(dietaryRestrictionsFieldLabel);
        addStudentsPanel.add(friendFieldLabel);
        modifyStudentsPanel.add(modifyStudentFieldLabel);
        modifyStudents2Panel.add(modifyNameFieldLabel);
        modifyStudents2Panel.add(modifyStudentNumberFieldLabel);
        modifyStudents2Panel.add(modifyDietaryRestrictionsFieldLabel);
        modifyStudents2Panel.add(modifyFriendFieldLabel);
        removeStudentsPanel.add(removeStudentNumberFieldLabel);

        //Add initial panels to JLayeredPane
        overallPanel.add(mainPanel, new Integer(100));
        overallPanel.add(backgroundPanel, new Integer(0));

        //Add panel to frame
        frame.getContentPane().add(overallPanel);
        frame.setVisible(true);

    }

    /**
     * This method converts a string into an ArrayList of strings
     * @param input the string that can be converted
     * @return The converted ArrayList of strings
     */
    private ArrayList<String> convertStringToArray(String input) {

        //Create ArrayList to store the converted string
        ArrayList<String> items = new ArrayList<>();

        do {
            if (input.contains(",")) { //Check if multiple terms are in the string to convert
                tempWord = input.substring(0, input.indexOf(","));
                if (input.lastIndexOf(",") != input.length()) { //Check to make sure the last character isn’t a comma
                    input = input.substring(input.indexOf(",") + 1);
                } else {
                    input = "";
                }
            } else if (!input.isEmpty()) { //Check to make sure the string contains characters to convert
                tempWord = input;
                input = "";
            }
            items.add(tempWord); //Add term to ArrayList
        } while (!input.isEmpty());

        //Return converted ArrayList
        return items;

    }

    /**
     *This method reads the list of students from the text file named Students.txt
     */
    private void readFile() {

        //Create Scanner to read the text file where students are stored
        try {
            input = new Scanner(new File("Students.txt"));
        } catch(Exception e) {
            students.clear();
            studentNumbers.clear();
            System.exit(1);
        }

        while (input.hasNextLine()) { //Check that the save file contains information

            //Retrieve information from save file
            name = input.nextLine();
            studentNumber = input.nextLine();
            outputtedDietaryRestrictions = input.nextLine();
            outputtedStudentNumbers = input.nextLine();

            //Convert read dietary restrictions and student numbers to strings
            dietaryRestrictions = convertStringToArray(outputtedDietaryRestrictions);
            friendStudentNumbers = convertStringToArray(outputtedStudentNumbers);

            //Create new student
            studentNumbers.add(studentNumber);
            tempStudent = new Student(name, studentNumber, new ArrayList<>(dietaryRestrictions), new ArrayList<>(friendStudentNumbers));
            students.add(tempStudent);

            //Empty all used strings
            name = "";
            studentNumber = "";
            outputtedStudentNumbers = "";
            outputtedDietaryRestrictions = "";
            dietaryRestrictions.clear();
            friendStudentNumbers.clear();

        }

        //Close Scanner
        input.close();

    }

    /**
     * This method outputs the data of students to a text file named Students.txt
     */
    private void writeToFile() {

        //Create PrintWriter to write to a file
        try {
            output = new PrintWriter(new File("Students.txt"));
        } catch(Exception e) {
            students.clear();
            studentNumbers.clear();
            System.exit(1);
        }

        //Output each student's information
        for (int i = 0; i < students.size(); i++) {

            //Output name and student number
            output.println(students.get(i).getName());
            output.println(students.get(i).getStudentNumber());

            if (students.get(i).getDietaryRestrictions().size() == 0) { //Check if there are no dietary restrictions
                output.println("");
            } else {
                //Format dietary restrictions to print out
                for (int k = 0; k < students.get(i).getDietaryRestrictions().size(); k++) {
                    output.print(students.get(i).getDietaryRestrictions().get(k));
                    if (k != students.get(i).getDietaryRestrictions().size() - 1) {
                        output.print(","); //Print commas between terms
                    }
                }
            }

            output.println(""); //Seperate dietary restrictions and student numbers

            if (students.get(i).getFriendStudentNumbers().size() == 0) { //Check if there are no friends
                output.println("");
            } else {
                //Format friends to print out
                for (int l = 0; l < students.get(i).getFriendStudentNumbers().size(); l++) {
                    output.print(students.get(i).getFriendStudentNumbers().get(l));
                    if (l != students.get(i).getFriendStudentNumbers().size() - 1) {
                        output.print(","); //Print commas between terms
                    }
                }
            }

            //Seperate students
            output.println("");

        }

        //Close PrintWriter
        output.close();

    }

    /**
     * This method clears the student list from the text file
     */
    private void clearFile() {

        //Empties all students currently stored by the program
        students.clear();
        studentNumbers.clear();

        //Create PrintWriter to write over the previous save file
        try {
            output = new PrintWriter(new File("Students.txt"));
        } catch(Exception e) {
            students.clear();
            studentNumbers.clear();
            System.exit(1);
        }

        //Clear file
        output.print("");

        //Close PrintWriter
        output.close();

    }

    /**
     * This method creates the student object based on information put into the text fields
     */
    private void makeStudent() {

        //Retrieve information from text field
        name = nameField.getText();
        studentNumber = studentNumberField.getText();
        tempDiet = dietaryRestrictionsField.getText();
        tempFriend = friendField.getText();

        if (!name.isEmpty() && !studentNumber.isEmpty()) { //Check that a student can be made

            if (!studentNumbers.contains(studentNumber)) {

                //Convert String of dietary restrictions to ArrayList
                dietaryRestrictions = convertStringToArray(tempDiet);

                //Convert String of student numbers to ArrayList
                friendStudentNumbers = convertStringToArray(tempFriend);

                //Create a new student
                students.add(new Student(name, studentNumber, new ArrayList<>(dietaryRestrictions), new ArrayList<>(friendStudentNumbers)));
                studentNumbers.add(studentNumber);

            }

        }

        //Clear all text fields and ArrayLists
        nameField.setText("");
        studentNumberField.setText("");
        dietaryRestrictionsField.setText("");
        friendField.setText("");
        dietaryRestrictions.clear();
        friendStudentNumbers.clear();

    }

    /**
     * This method takes the number of the student to modify from the text field
     *@return index of the person to modify
     */
    private int modifyStudent() {

        //Create variables to gather information to display in text fields when editing
        String studentNumToModify;
        studentNumToModify = modifyStudentField.getText();
        dietaryRestrictionsText = "";
        friendStudentNumbersText = "";

        //Clear text field
        modifyStudentField.setText("");

        if (studentNumbers.contains(studentNumToModify)) { //Checks if student exists
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentNumber().equals(studentNumToModify)) {

                    //Sets the text field to the student's current name and student number
                    modifyNameField.setText(students.get(i).getName());
                    modifyStudentNumberField.setText(students.get(i).getStudentNumber());

                    //Sets text field to students current dietary restrictions
                    for (int k = 0; k < students.get(i).getDietaryRestrictions().size(); k++) {
                        if (k != students.get(i).getDietaryRestrictions().size()-1) {
                            dietaryRestrictionsText = dietaryRestrictionsText + students.get(i).getDietaryRestrictions().get(k) + ",";
                        } else {
                            dietaryRestrictionsText = dietaryRestrictionsText + students.get(i).getDietaryRestrictions().get(k);
                        }
                    }
                    modifyDietaryRestrictionsField.setText(dietaryRestrictionsText);

                    //Sets text field to students current friend numbers
                    for (int j = 0; j < students.get(i).getFriendStudentNumbers().size(); j++) {
                        if (j != students.get(i).getDietaryRestrictions().size()-1) {
                            friendStudentNumbersText = friendStudentNumbersText + students.get(i).getFriendStudentNumbers().get(j) + ",";
                        } else {
                            friendStudentNumbersText = friendStudentNumbersText + students.get(i).getFriendStudentNumbers().get(j);
                        }
                    }
                    modifyFriendField.setText(friendStudentNumbersText);

                    //Return the index where the student to modify is stored
                    return i;

                }
            }
        }

        //Returns a negative number to show that there is no student to modify
        return -1;

    }

    /**
     * This method leads to the screen to edit the selected the student
     *@param studentToModify the index of the student to modify
     */
    private void editStudent(int studentToModify) {

        //Receive information from text fields
        name = modifyNameField.getText();
        studentNumber = modifyStudentNumberField.getText();
        tempDiet = modifyDietaryRestrictionsField.getText();
        tempFriend = modifyFriendField.getText();
        dietaryRestrictions = convertStringToArray(tempDiet);
        friendStudentNumbers = convertStringToArray(tempFriend);

        //Modifies student based off information received
        if (!name.isEmpty() && !studentNumber.isEmpty()) {
            students.get(studentToModify).setName(name);
            students.get(studentToModify).setStudentNumber(studentNumber);
            students.get(studentToModify).setDietaryRestrictions(new ArrayList<>(dietaryRestrictions));
            students.get(studentToModify).setFriendStudentNumbers(new ArrayList<>(friendStudentNumbers));
            studentNumbers.remove(studentToModify);
            studentNumbers.add(studentToModify, studentNumber);
        }

        //Clear text fields
        modifyNameField.setText("");
        modifyStudentNumberField.setText("");
        modifyDietaryRestrictionsField.setText("");
        modifyFriendField.setText("");
        dietaryRestrictions.clear();
        friendStudentNumbers.clear();

        //Saves students
        writeToFile();

    }

    /**
     * This method removes a student from the program
     */
    private void removeStudent() {

        //Retrieve student number of student to remove
        removeStudentNumber = removeStudentField.getText();

        if (studentNumbers.contains(removeStudentNumber)) { //Check if the student number entered exists
            for (int i = 0; i < students.size(); i++) { //Find which index the student to remove is at
                if (students.get(i).getStudentNumber().equals(removeStudentNumber)) {
                    //Remove student
                    students.remove(students.get(i));
                    studentNumbers.remove(removeStudentNumber);
                }
            }
        }

        //Clear text fields
        modifyStudentField.setText("");
        removeStudentField.setText("");

        //Save students
        writeToFile();

    }

    /**
     * This method generates a floor plan that can later be displayed
     */
    private void generateFloorPlan() {

        //Run SeatingAlgorithm class's method
        tables = seatingAlgorithm.generateTables(students, TABLE_LIMIT);

        //Return to main panel from loading screen
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(mainPanel, new Integer(100));
        frame.repaint();

    }

    /**
     * This method displays the generated floor plan
     */
    private void displayFloorPlan() {

        //Run FloorPlan class's methods
        plan.generateFloorPlan(tables);
        plan.displayFloorPlan();

    }

    /**
     * This class gives the buttons in the UI functionality
     */
    private class ButtonListener implements ActionListener{

        /**
         * This method determines which button was pressed and performs actions accordingly
         * @param press the button that was pressed
         */
        public void actionPerformed(ActionEvent press) {
            if (press.getSource() == addStudentButton) { //Moves to panel where you can add students
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(addStudentsPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == modifyStudentsButton) { //Moves to panel where you can choose how to edit students
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(removeModifyClearPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == modifyStudentsButton2) { //Moves to panel where you can select which student to modify
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(modifyStudentsPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == removeStudentButton) { //Moves to panel where you can remove a student
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(removeStudentsPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == clearStudentsButton) { //Moves to panel where you can erase the save
                clearFile();
            } else if (press.getSource() == submitInfoButton) { //Creates a student if possible and returns to main panel
                makeStudent();
                writeToFile();
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(mainPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == submitInfoRemoveButton) { //Removes student if possible and returns to main panel
                removeStudent();
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(mainPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == submitInfoModifyButton) { //Moves to panel where you can modify a student if possible
                studentToModify = modifyStudent();
                if (studentToModify != -1) {
                    overallPanel.removeAll();
                    overallPanel.add(backgroundPanel, new Integer(0));
                    overallPanel.add(modifyStudents2Panel, new Integer(100));
                    frame.repaint();
                }
            } else if (press.getSource() == submitInfoEditButton) { //Edits student if possible
                if (studentToModify != -1) {
                    editStudent(studentToModify);
                }
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(mainPanel, new Integer(100));
                frame.repaint();
            } else if ((press.getSource() == returnButton) || (press.getSource() == returnFromModifyButton)) { //Returns to main panel
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(mainPanel, new Integer(100));
                frame.repaint();
            } else if ((press.getSource() == returnFromRemoveButton) || (press.getSource() == returnFromModifyButton2)) { //Returns to main panel
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(mainPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == returnFromEditButton) { //Returns to main panel
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(mainPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == displayFloorPlanButton) { //Displays floor plan
                displayFloorPlan();
            } else if (press.getSource() == generateFloorPlanButton) { //Generates floor plan
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(loadingScreenPanel, new Integer(100));
                frame.repaint();
                generateFloorPlan();
            } else if (press.getSource() == exitProgramButton) { //Exits program
                students.clear(); //Clears array for the next time the program opens
                studentNumbers.clear();
                System.exit(0); //Exit program
            }
        }
    }

}