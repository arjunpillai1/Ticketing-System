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
  private JLabel background, loadingIcon;
  private JButton addStudentButton, removeStudentButton, displayFloorPlanButton, exitProgramButton, submitInfoButton, returnButton, returnFromRemoveButton;
  private JButton submitInfoRemoveButton, generateFloorPlanButton;
  private JTextField nameField, studentNumberField, dietaryRestrictionsField, friendField, removeStudentNumberField;
  private JLabel nameFieldLabel,studentNumberFieldLabel,dietaryRestrictionsFieldLabel,friendFieldLabel,removeStudentNumberFieldLabel;
  
  //Array Lists
  ArrayList<Student> students = new ArrayList<Student>();
  ArrayList<String> studentNumbers = new ArrayList<String>();
  private Student tempStudent;
  private ArrayList<Table> tables = new ArrayList<Table>();
  
  //Used for creating students
  private String name, studentNumber, tempDiet, tempFriend, tempWord, removeStudentNumber, outputtedStudentNumbers, outputtedDietaryRestrictions;
  private ArrayList<String> dietaryRestrictions = new ArrayList<String>();
  private ArrayList<String> friendStudentNumbers = new ArrayList<String>();
  
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
    
    //Set all panel layouts
    mainPanel.setLayout(null);
    addStudentsPanel.setLayout(null);
    removeStudentsPanel.setLayout(null);
    loadingScreenPanel.setLayout(null);
    
    //Set all panel opacity
    mainPanel.setOpaque(false);
    addStudentsPanel.setOpaque(false);
    removeStudentsPanel.setOpaque(false);
    loadingScreenPanel.setOpaque(false);
    
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
    
    //Set button locations
    addStudentButton.setBounds(135, 15, 230, 50);
    removeStudentButton.setBounds(135, 115, 231, 50);
    generateFloorPlanButton.setBounds(135, 215, 231, 50);
    displayFloorPlanButton.setBounds(135, 315, 230, 50);
    exitProgramButton.setBounds(135, 415, 231, 50);
    submitInfoButton.setBounds(135, 300, 231, 50);
    returnButton.setBounds(135, 375, 231, 50);
    submitInfoRemoveButton.setBounds(135, 300, 231, 50);
    returnFromRemoveButton.setBounds(135, 375, 231, 50);
    
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
    
    //Create JTextFields
    nameField = new JTextField(20);
    studentNumberField = new JTextField(20);
    dietaryRestrictionsField = new JTextField(20);
    friendField = new JTextField(20);
    removeStudentNumberField = new JTextField(20);
    
    //Create labels for text fields
    nameFieldLabel = new JLabel("Enter student name.");
    studentNumberFieldLabel = new JLabel("Enter student number.");
    dietaryRestrictionsFieldLabel = new JLabel("Enter student's dietary restrictions.");
    friendFieldLabel = new JLabel("Enter student number of friends.");
    removeStudentNumberFieldLabel = new JLabel("Enter the student number to remove.");
    
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
    removeStudentNumberField.setBounds(135, 80, 230, 20);
    
    //Set label locations
    nameFieldLabel.setBounds(135, 20, 230, 20);
    studentNumberFieldLabel.setBounds(135, 80, 230, 20);
    dietaryRestrictionsFieldLabel.setBounds(135, 140, 230, 20);
    friendFieldLabel.setBounds(135, 200, 230, 20);
    removeStudentNumberFieldLabel.setBounds(135, 60, 230, 20);
    
    
    //Add buttons to panels
    mainPanel.add(addStudentButton);
    mainPanel.add(removeStudentButton);
    mainPanel.add(generateFloorPlanButton);
    mainPanel.add(displayFloorPlanButton);
    mainPanel.add(exitProgramButton);
    addStudentsPanel.add(submitInfoButton);
    addStudentsPanel.add(returnButton);
    removeStudentsPanel.add(returnFromRemoveButton);
    removeStudentsPanel.add(submitInfoRemoveButton);
    
    //Add text fields to panels
    addStudentsPanel.add(nameField);
    addStudentsPanel.add(studentNumberField);
    addStudentsPanel.add(dietaryRestrictionsField);
    addStudentsPanel.add(friendField);
    removeStudentsPanel.add(removeStudentNumberField);
   
    //Add labels to panel
    addStudentsPanel.add(nameFieldLabel);
    addStudentsPanel.add(studentNumberFieldLabel);
    addStudentsPanel.add(dietaryRestrictionsFieldLabel);
    addStudentsPanel.add(friendFieldLabel);
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
    
    //Convert String of dietary restrictions to ArrayList
    do {
      if (tempDiet.contains(",")) { //Check if multiple dietary restrictions are in place
        tempWord = tempDiet.substring(0, tempDiet.indexOf(","));
        if (tempDiet.lastIndexOf(",") != tempDiet.length()) {
          tempDiet = tempDiet.substring(tempDiet.indexOf(",") + 1);
        } else {
          tempDiet = "";
        }
      } else if (!tempDiet.isEmpty()) {
        tempWord = tempDiet;
        tempDiet = "";
      }
      dietaryRestrictions.add(tempWord);
    } while (!tempDiet.isEmpty());
    
    //Convert String of student numbers to ArrayList
    do {
      if (tempFriend.contains(",")) {
        tempWord = tempFriend.substring(0, tempFriend.indexOf(","));
        if (tempFriend.lastIndexOf(",") != tempFriend.length()) {
          tempFriend = tempFriend.substring(tempFriend.indexOf(",") + 1);
        } else {
          tempFriend = "";
        }
      } else if (!tempFriend.isEmpty()) {
        tempWord = tempFriend;
        tempFriend = "";
      }
      friendStudentNumbers.add(tempWord);
    } while (!tempFriend.isEmpty());
    
    //Create a new student
    students.add(new Student(name, studentNumber, new ArrayList<String> (dietaryRestrictions), new ArrayList<String> (friendStudentNumbers)));
    studentNumbers.add(studentNumber);
    
    //Clear all text fields and ArrayLists
    nameField.setText("");
    studentNumberField.setText("");
    dietaryRestrictionsField.setText("");
    friendField.setText("");
    dietaryRestrictions.clear();
    friendStudentNumbers.clear();
  }
  
  private void removeStudent() {
    removeStudentNumber = removeStudentNumberField.getText();
    
    if (studentNumbers.contains(removeStudentNumber)) {
      for (int i = 0; i < students.size(); i++) {
        if (students.get(i).getStudentNumber().equals(removeStudentNumber)) {
          students.remove(students.indexOf(students.get(i)));
          studentNumbers.remove(removeStudentNumber);
        }
      }
    }
    
    removeStudentNumberField.setText("");
    writeToFile();
    
  }
  
  private void generateFloorPlan() {
    tables = seatingAlgorithm.generateTables(students,tableLimit);
    overallPanel.removeAll();
    overallPanel.add(backgroundPanel, new Integer(0));
    overallPanel.add(mainPanel, new Integer(100));
    frame.repaint();
  }
  
  private JPanel displayFloorPlan() {
    plan.generateFloorPlan(tables);
    while (true) {
      plan.displayFloorPlan();
    }
    
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
          output.print("|");
        }
      }
      output.println("");
      for (int l = 0; l < students.get(i).getFriendStudentNumbers().size(); l++) {
        output.print(students.get(i).getFriendStudentNumbers().get(l));
        if (l != students.get(i).getFriendStudentNumbers().size() - 1) {
          output.print("|");
        }
      }
      output.println("");
    }
    
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
        if (outputtedDietaryRestrictions.contains("|")) {
          tempDiet = outputtedDietaryRestrictions.substring(0, outputtedDietaryRestrictions.indexOf("|"));
          outputtedDietaryRestrictions = outputtedDietaryRestrictions.substring(outputtedDietaryRestrictions.indexOf("|") + 1);
          dietaryRestrictions.add(tempDiet);
        }
      } while (outputtedDietaryRestrictions.contains("|"));
      dietaryRestrictions.add(outputtedDietaryRestrictions);
      
      do {
        if (outputtedStudentNumbers.contains("|")) {
          tempStudentNum = outputtedStudentNumbers.substring(0, outputtedStudentNumbers.indexOf("|"));
          outputtedStudentNumbers = outputtedStudentNumbers.substring(outputtedStudentNumbers.indexOf("|") + 1);
          friendStudentNumbers.add(tempStudentNum);
        }
      } while (outputtedStudentNumbers.contains("|"));
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
      } else if (press.getSource() == returnButton || press.getSource() == returnFromRemoveButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(mainPanel, new Integer(100));
        frame.repaint();
      } else if (press.getSource() == submitInfoRemoveButton) {
        removeStudent();
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(mainPanel, new Integer(100));
      } else if (press.getSource() == generateFloorPlanButton) {
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(loadingScreenPanel, new Integer(100));
        frame.repaint();
        generateFloorPlan();
      }
    }
  }
  
}
