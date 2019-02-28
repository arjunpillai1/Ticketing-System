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
  private JPanel addStudentsPanel = new JPanel();
  private JPanel removeStudentsPanel = new JPanel();
  private JPanel loadingScreenPanel = new JPanel();
  private JPanel modifyStudentsPanel = new JPanel();
  private JPanel removeModifyClearPanel = new JPanel();
  private JPanel modifyStudents2Panel = new JPanel();
  private JLabel background, loadingIcon;
  private JButton addStudentButton, removeStudentButton, displayFloorPlanButton, exitProgramButton, submitInfoButton, returnButton, returnFromRemoveButton;
  private JButton submitInfoRemoveButton, generateFloorPlanButton, modifyStudentsButton, modifyStudentsButton2, clearStudentsButton;
  private JButton returnFromModifyButton, returnFromModifyButton2, submitInfoModifyButton, submitInfoEditButton, returnFromEditButton;
  private JTextField nameField, studentNumberField, dietaryRestrictionsField, friendField, modifyStudentField, removeStudentField;
  private JTextField modifyNameField, modifyStudentNumberField, modifyDietaryRestrictionsField, modifyFriendField;
  private JLabel nameFieldLabel,studentNumberFieldLabel,dietaryRestrictionsFieldLabel,friendFieldLabel,removeStudentNumberFieldLabel;
  private JLabel modifyNameFieldLabel,modifyStudentNumberFieldLabel,modifyDietaryRestrictionsFieldLabel,modifyFriendFieldLabel;
  private JLabel modifyStudentFieldLabel;
  
  //Array Lists
  ArrayList<Student> students = new ArrayList<Student>();
  ArrayList<String> studentNumbers = new ArrayList<String>();
  private Student tempStudent;
  private ArrayList<Table> tables = new ArrayList<Table>();
  
  //Used for creating students
  private String dietaryRestrictionsText, friendStudentNumbersText;
  private String name, studentNumber, tempDiet, tempFriend, tempWord, removeStudentNumber, outputtedStudentNumbers, outputtedDietaryRestrictions;
  private ArrayList<String> dietaryRestrictions = new ArrayList<String>();
  private ArrayList<String> friendStudentNumbers = new ArrayList<String>();
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
  SeatingAlg seatingAlgorithm;
  FloorPlan plan;
  private static int tableLimit=10;
  
  /**
   * Constructor for the ticketing system
   */
  TicketingSystem() {
    readFile();
    seatingAlgorithm = new SeatingAlg();
    plan = new FloorPlan();
    makeUIComponents();
  }
  
  
  
  /* makeUIComponents
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
    removeStudentButton = new JButton(removeStudentPicture);
    displayFloorPlanButton = new JButton(displayFloorPlanPicture);
    exitProgramButton = new JButton(exitProgramPicture);
    submitInfoButton = new JButton(submitInfoPicture);
    returnButton = new JButton(returnPicture);
    submitInfoRemoveButton = new JButton(submitInfoPicture);
    returnFromRemoveButton = new JButton(returnPicture);
    generateFloorPlanButton = new JButton(generateFloorPlanPicture);
    modifyStudentsButton = new JButton(modifyStudentsPicture);
    modifyStudentsButton2 = new JButton(modifyStudentsPicture);
    clearStudentsButton = new JButton(clearStudentsPicture);
    returnFromModifyButton = new JButton(returnPicture);
    returnFromModifyButton2 = new JButton(returnPicture);
    submitInfoModifyButton = new JButton(submitInfoPicture);
    submitInfoEditButton = new JButton(submitInfoPicture);
    returnFromEditButton = new JButton(returnPicture);
    
    //Delete borders of buttons
    addStudentButton.setBorder(null);
    removeStudentButton.setBorder(null);
    displayFloorPlanButton.setBorder(null);
    exitProgramButton.setBorder(null);
    submitInfoButton.setBorder(null);
    returnButton.setBorder(null);
    submitInfoRemoveButton.setBorder(null);
    returnFromRemoveButton.setBorder(null);
    generateFloorPlanButton.setBorder(null);
    modifyStudentsButton.setBorder(null);
    modifyStudentsButton2.setBorder(null);
    clearStudentsButton.setBorder(null);
    returnFromModifyButton.setBorder(null);
    returnFromModifyButton2.setBorder(null);
    submitInfoModifyButton.setBorder(null);
    submitInfoEditButton.setBorder(null);
    returnFromEditButton.setBorder(null);
    
    //Set button locations
    addStudentButton.setBounds(135, 15, 230, 50);
    modifyStudentsButton.setBounds(135, 115, 231, 50);
    modifyStudentsButton2.setBounds(135, 15, 230, 50);
    removeStudentButton.setBounds(135, 115, 231, 50);
    clearStudentsButton.setBounds(135, 215, 231, 50);
    generateFloorPlanButton.setBounds(135, 215, 231, 50);
    displayFloorPlanButton.setBounds(135, 315, 230, 50);
    exitProgramButton.setBounds(135, 415, 231, 50);
    submitInfoButton.setBounds(135, 300, 231, 50);
    returnButton.setBounds(135, 375, 231, 50);
    submitInfoRemoveButton.setBounds(135, 300, 231, 50);
    returnFromRemoveButton.setBounds(135, 375, 231, 50);
    returnFromModifyButton.setBounds(135, 375, 231, 50);
    returnFromModifyButton2.setBounds(135, 375, 231, 50);
    submitInfoModifyButton.setBounds(135, 300, 231, 50);
    submitInfoEditButton.setBounds(135, 300, 231, 50);
    returnFromEditButton.setBounds(135, 375, 231, 50);
    
    //Add action listeners to buttons
    ActionListener listener = new ButtonListener();
    addStudentButton.addActionListener(listener);
    removeStudentButton.addActionListener(listener);
    generateFloorPlanButton.addActionListener(listener);
    displayFloorPlanButton.addActionListener(listener);
    exitProgramButton.addActionListener(listener);
    submitInfoButton.addActionListener(listener);
    returnButton.addActionListener(listener);
    submitInfoRemoveButton.addActionListener(listener);
    returnFromRemoveButton.addActionListener(listener);
    modifyStudentsButton.addActionListener(listener);
    modifyStudentsButton2.addActionListener(listener);
    clearStudentsButton.addActionListener(listener);
    returnFromModifyButton.addActionListener(listener);
    returnFromModifyButton2.addActionListener(listener);
    submitInfoModifyButton.addActionListener(listener);
    submitInfoEditButton.addActionListener(listener);
    returnFromEditButton.addActionListener(listener);
    
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
    //Connect labels to text
//    nameFieldLabel.setLabelFor(nameField);
//    studentNumberFieldLabel.setLabelFor(studentNumberField);
//    dietaryRestrictionsFieldLabel.setLabelFor(dietaryRestrictionsField);
//    friendFieldLabel.setLabelFor(friendField);
    
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
  
  private void makeStudent() {
    
    //Retrieve information from text field
    name = nameField.getText();
    studentNumber = studentNumberField.getText();
    tempDiet = dietaryRestrictionsField.getText();
    tempFriend = friendField.getText();
    
    if (!name.isEmpty() && !studentNumber.isEmpty()) {
      
      if (!studentNumbers.contains(studentNumber)) {
        
        //Convert String of dietary restrictions to ArrayList
        dietaryRestrictions = convertStringToArray(tempDiet);
        
        //Convert String of student numbers to ArrayList
        friendStudentNumbers = convertStringToArray(tempFriend);
        
        //Create a new student
        students.add(new Student(name, studentNumber, new ArrayList<String>(dietaryRestrictions), new ArrayList<String>(friendStudentNumbers)));
        studentNumbers.add(studentNumber);
      } else {
        
      }
      
    } else {
      
    }
    
    //Clear all text fields and ArrayLists
    nameField.setText("");
    studentNumberField.setText("");
    dietaryRestrictionsField.setText("");
    friendField.setText("");
    dietaryRestrictions.clear();
    friendStudentNumbers.clear();
  }
  
  private void removeStudent() {
    removeStudentNumber = removeStudentField.getText();
    
    if (studentNumbers.contains(removeStudentNumber)) {
      for (int i = 0; i < students.size(); i++) {
        if (students.get(i).getStudentNumber().equals(removeStudentNumber)) {
          students.remove(students.indexOf(students.get(i)));
          studentNumbers.remove(removeStudentNumber);
        }
      }
    } else {
      
    }
    
    modifyStudentField.setText("");
    removeStudentField.setText("");
    writeToFile();
    
  }
  
  private void generateFloorPlan() {
    tables = seatingAlgorithm.generateTables(students,tableLimit);
    overallPanel.removeAll();
    overallPanel.add(backgroundPanel, new Integer(0));
    overallPanel.add(mainPanel, new Integer(100));
    frame.repaint();
  }
  
  private void displayFloorPlan() {
    plan.generateFloorPlan(tables);
    plan.displayFloorPlan();
    
    //return null;
  }
  
  private void writeToFile() {
    
    //Create PrintWriter to write to a file
    try {
      output = new PrintWriter(new File("Students.txt"));
    } catch(Exception e) {
      System.out.println("oops");
    }
    
    //Output each student's information
    for (int i = 0; i < students.size(); i++) {
      output.println(students.get(i).getName());
      output.println(students.get(i).getStudentNumber());
      for (int k = 0; k < students.get(i).getDietaryRestrictions().size(); k++) {
        output.print(students.get(i).getDietaryRestrictions().get(k));
        if (k != students.get(i).getDietaryRestrictions().size() - 1) {
          output.print(",");
        }
      }
      
      if (students.get(i).getDietaryRestrictions().size() == 0) {
        output.println("");
      }
      output.println("");
      for (int l = 0; l < students.get(i).getFriendStudentNumbers().size(); l++) {
        output.print(students.get(i).getFriendStudentNumbers().get(l));
        if (l != students.get(i).getFriendStudentNumbers().size() - 1) {
          output.print(",");
        }
      }
      if (students.get(i).getFriendStudentNumbers().size() == 0) {
        output.println("");
      }
      output.println("");
    }
    
    output.close();
  }
  
  private void clearFile() {
    students.clear();
    
    try {
      output = new PrintWriter(new File("Students.txt"));
    } catch(Exception e) {
      System.out.println("oops");
    }
    
    output.print("");
    output.close();
    
  }
  
  private void readFile() {
    
    try {
      input = new Scanner(new File("Students.txt"));
    } catch(Exception e) {
      System.out.println("oops");
    }
    
    while (input.hasNextLine()) {
      name = input.nextLine();
      studentNumber = input.nextLine();
      outputtedDietaryRestrictions = input.nextLine();
      outputtedStudentNumbers = input.nextLine();
      String tempDiet, tempStudentNum;
      
      do {
        if (outputtedDietaryRestrictions.contains(",")) {
          tempDiet = outputtedDietaryRestrictions.substring(0, outputtedDietaryRestrictions.indexOf(","));
          outputtedDietaryRestrictions = outputtedDietaryRestrictions.substring(outputtedDietaryRestrictions.indexOf(",") + 1);
          dietaryRestrictions.add(tempDiet);
        }
      } while (outputtedDietaryRestrictions.contains(","));
      dietaryRestrictions.add(outputtedDietaryRestrictions);
      
      do {
        if (outputtedStudentNumbers.contains(",")) {
          tempStudentNum = outputtedStudentNumbers.substring(0, outputtedStudentNumbers.indexOf(","));
          outputtedStudentNumbers = outputtedStudentNumbers.substring(outputtedStudentNumbers.indexOf(",") + 1);
          friendStudentNumbers.add(tempStudentNum);
        }
      } while (outputtedStudentNumbers.contains(","));
      friendStudentNumbers.add(outputtedStudentNumbers);
      
      studentNumbers.add(studentNumber);
      tempStudent = new Student(name, studentNumber, new ArrayList<String>(dietaryRestrictions), new ArrayList<String>(friendStudentNumbers));
      students.add(tempStudent);
      
      name = "";
      studentNumber = "";
      outputtedStudentNumbers = "";
      outputtedDietaryRestrictions = "";
      dietaryRestrictions.clear();
      friendStudentNumbers.clear();
    }
    
    
    input.close();
  }
  
  private int modifyStudent() {
    String studentNumToModify;
    dietaryRestrictionsText = "";
    friendStudentNumbersText = "";
    studentNumToModify = modifyStudentField.getText();
    if (studentNumbers.contains(studentNumToModify)) {
      for (int i = 0; i < students.size(); i++) {
        if (students.get(i).getStudentNumber().equals(studentNumToModify)) {
          System.out.println(students.get(i).getName());
          modifyNameField.setText(students.get(i).getName());
          modifyStudentNumberField.setText(students.get(i).getStudentNumber());
          for (int k = 0; k < students.get(i).getDietaryRestrictions().size(); k++) {
            if (k != students.get(i).getDietaryRestrictions().size()-1) {
              dietaryRestrictionsText = dietaryRestrictionsText + students.get(i).getDietaryRestrictions().get(k) + ",";
            } else {
              dietaryRestrictionsText = dietaryRestrictionsText + students.get(i).getDietaryRestrictions().get(k);
            }
          }
          modifyDietaryRestrictionsField.setText(dietaryRestrictionsText);
          for (int j = 0; j < students.get(i).getFriendStudentNumbers().size(); j++) {
            if (j != students.get(i).getDietaryRestrictions().size()-1) {
              friendStudentNumbersText = friendStudentNumbersText + students.get(i).getFriendStudentNumbers().get(j) + ",";
            } else {
              friendStudentNumbersText = friendStudentNumbersText + students.get(i).getFriendStudentNumbers().get(j);
            }
          }
          modifyFriendField.setText(friendStudentNumbersText);
          
          return i;
          
        }
      }
    } else {
    }
    return -1;
  }
  
  private void editStudent(int studentToModify) {
    frame.repaint();
    System.out.println(modifyDietaryRestrictionsField.getText());
    name = modifyNameField.getText();
    studentNumber = modifyStudentNumberField.getText();
    tempDiet = modifyDietaryRestrictionsField.getText();
    tempFriend = modifyFriendField.getText();
    dietaryRestrictions = convertStringToArray(tempDiet);
    System.out.println(dietaryRestrictions);
    friendStudentNumbers = convertStringToArray(tempFriend);
    frame.repaint();
    
    if (!name.isEmpty() && !studentNumber.isEmpty()) {
      students.get(studentToModify).setName(name);
      students.get(studentToModify).setStudentNumber(studentNumber);
      students.get(studentToModify).setDietaryRestrictions(new ArrayList<String>(dietaryRestrictions));
      students.get(studentToModify).setFriendStudentNumbers(new ArrayList<String>(friendStudentNumbers));
    } else {
      
    }
    
    modifyNameField.setText("");
    modifyStudentNumberField.setText("");
    modifyDietaryRestrictionsField.setText("");
    modifyFriendField.setText("");
    
    writeToFile();
  }
  
  private ArrayList<String> convertStringToArray(String input) {
    ArrayList<String> items = new ArrayList<String>();
    
    do {
      if (input.contains(",")) { //Check if multiple dietary restrictions are in place
        tempWord = input.substring(0, input.indexOf(","));
        if (input.lastIndexOf(",") != input.length()) {
          input = input.substring(input.indexOf(",") + 1);
        } else {
          input = "";
        }
        System.out.println(input);
      } else if (!input.isEmpty()) {
        tempWord = input;
        input = "";
      }
      items.add(tempWord);
    } while (!input.isEmpty());
    
    return items;
  }
  
  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent press) {
      if (press.getSource() == addStudentButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(addStudentsPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == removeStudentButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(removeStudentsPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == displayFloorPlanButton) {
        displayFloorPlan();
      } else if (press.getSource() == exitProgramButton) {
        students.clear();
        System.exit(0);
      } else if (press.getSource() == submitInfoButton) {
        makeStudent();
        writeToFile();
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(mainPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == returnButton || press.getSource() == returnFromRemoveButton || press.getSource() == returnFromModifyButton || press.getSource() == returnFromModifyButton2 || press.getSource() == returnFromEditButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(mainPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == submitInfoRemoveButton) {
        removeStudent();
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(mainPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == generateFloorPlanButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(loadingScreenPanel, new Integer(100));
        frame.repaint();
        generateFloorPlan();
      } else if (press.getSource() == clearStudentsButton) {
        clearFile();
      } else if (press.getSource() == modifyStudentsButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(removeModifyClearPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == modifyStudentsButton2) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(modifyStudentsPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == submitInfoModifyButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(modifyStudents2Panel, new Integer(100));
        frame.repaint();
        studentToModify = modifyStudent();
      } else if (press.getSource() == submitInfoEditButton) {
        if (studentToModify != -1) {
          editStudent(studentToModify);
        } else {
          
        }
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(mainPanel, new Integer(100));
        frame.repaint();
      }
    }
  }
  
}