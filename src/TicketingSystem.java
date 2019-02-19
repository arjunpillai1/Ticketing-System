/* TicketSystem
 * This class contains all the UI for the Prom/Semi project
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicketingSystem extends JFrame{
    private JFrame frame = new JFrame("Ticketing System");
    private JLayeredPane overallPanel = new JLayeredPane();
    private JPanel mainPanel = new JPanel();
    private JPanel backgroundPanel = new JPanel();
    private JPanel addStudentsPanel = new JPanel();
    private JPanel removeStudentsPanel = new JPanel();
    private JLabel background;
    private JButton addStudentButton, removeStudentButton, displayFloorPlanButton, exitProgramButton, submitInfoButton, returnButton, returnFromRemoveButton;
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Student> savedStudentList = new ArrayList<Student>();
    private Student tempStudent;
    private String name, studentNumber, tempDiet, tempFriend, tempWord, friendStudentNumber;
    private ArrayList<String> dietaryRestrictions = new ArrayList<String>();
    private ArrayList<String> friendStudentNumbers = new ArrayList<String>();
    //private ArrayList<Table> tables = new ArrayList<Table>;
    private JTextField nameField, studentNumberField, dietaryRestrictionsField, friendField, removeStudentNumber;

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

    TicketingSystem() {
//        SeatingAlg seatingAlgorithm = new SeatingAlg();
//        FloorPlan floorPlan = new FloorPlan();
        makeFrame();
        addStudents();
    }


    /* makeFrame
     * This method creates the main menu
     */
    private void makeFrame() {

        try {
            input = new Scanner(new File("Students.txt"));
        } catch(Exception e) {
            System.out.println("oops");
        }

        //Create JFrame
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Create JPanel
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
        overallPanel.setLayout(new OverlayLayout(overallPanel));

        //Create buttons
        addStudentButton = new JButton(addStudentPicture);
        removeStudentButton = new JButton(removeStudentPicture);
        displayFloorPlanButton = new JButton(displayFloorPlanPicture);
        exitProgramButton = new JButton(exitProgramPicture);


        addStudentButton.setBorder(null);
        removeStudentButton.setBorder(null);
        displayFloorPlanButton.setBorder(null);
        exitProgramButton.setBorder(null);

        //Set button bounds
        addStudentButton.setBounds(135, 50, 230, 50);
        removeStudentButton.setBounds(135, 150, 231, 50);
        displayFloorPlanButton.setBounds(135, 250, 230, 50);
        exitProgramButton.setBounds(135, 350, 231, 50);

        //Add action listeners
        ActionListener listener = new ButtonListener();
        addStudentButton.addActionListener(listener);
        removeStudentButton.addActionListener(listener);
        displayFloorPlanButton.addActionListener(listener);
        exitProgramButton.addActionListener(listener);

        //Add buttons to main panel
        mainPanel.add(addStudentButton);
        mainPanel.add(removeStudentButton);
        mainPanel.add(displayFloorPlanButton);
        mainPanel.add(exitProgramButton);

        background = new JLabel(backgroundPicture);

        backgroundPanel.add(background);

        overallPanel.add(mainPanel, new Integer(100));
        overallPanel.add(backgroundPanel, new Integer(0));

        //Add panel to frame
        frame.getContentPane().add(overallPanel);
        frame.setVisible(true);
    }

    private void addStudents() {
        addStudentsPanel.setSize(500, 500);
        nameField = new JTextField(20);
        studentNumberField = new JTextField(20);
        dietaryRestrictionsField = new JTextField(20);
        friendField = new JTextField(20);

        addStudentsPanel.setLayout(null);
        addStudentsPanel.setOpaque(false);

        removeStudentsPanel.setLayout(null);
        removeStudentsPanel.setOpaque(false);

        nameField.setBounds(135, 20, 230, 20);
        studentNumberField.setBounds(135, 80, 230, 20);
        dietaryRestrictionsField.setBounds(135, 140, 230, 20);
        friendField.setBounds(135, 200, 230, 20);

        submitInfoButton = new JButton(submitInfoPicture);
        submitInfoButton.setBorder(null);
        ActionListener buttons = new ButtonListener();
        submitInfoButton.addActionListener(buttons);
        submitInfoButton.setBounds(135, 300, 231, 50);

        returnButton = new JButton(returnPicture);
        returnButton.setBorder(null);
        returnButton.addActionListener(buttons);
        returnButton.setBounds(135, 375, 231, 50);

        addStudentsPanel.add(nameField);
        addStudentsPanel.add(studentNumberField);
        addStudentsPanel.add(dietaryRestrictionsField);
        addStudentsPanel.add(friendField);
        addStudentsPanel.add(submitInfoButton);
        addStudentsPanel.add(returnButton);

        removeStudentsPanel.setSize(500, 500);
        removeStudentNumber = new JTextField(20);
        removeStudentNumber.setBounds(135, 80, 230, 20);
        returnFromRemoveButton = new JButton(returnPicture);
        returnFromRemoveButton.addActionListener(buttons);
        returnFromRemoveButton.setBorder(null);
        returnFromRemoveButton.setBounds(135, 375, 231, 50);
        removeStudentsPanel.add(removeStudentNumber);
        removeStudentsPanel.add(returnFromRemoveButton);


    }

    private void makeStudent() {
        name = nameField.getText();
        studentNumber = studentNumberField.getText();
        tempDiet = dietaryRestrictionsField.getText();
        tempFriend = friendField.getText();

        do {
            if (tempDiet.indexOf(",") != -1) {
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

        do {
            if (tempFriend.indexOf(",") != -1) {
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

        System.out.println(name + studentNumber);
        System.out.println(dietaryRestrictions);
        System.out.println(friendStudentNumbers);
        students.add(new Student(name, studentNumber, new ArrayList<String> (dietaryRestrictions), new ArrayList<String> (friendStudentNumbers)));
        
        nameField.setText("");
        studentNumberField.setText("");
        dietaryRestrictionsField.setText("");
        friendField.setText("");
        dietaryRestrictions.clear();
        friendStudentNumbers.clear();
    }

    private void removeStudent() {
        friendStudentNumber = removeStudentNumber.getText();
        System.out.println(friendStudentNumber);
        students.remove(students.indexOf(friendStudentNumber));
    }

    private JPanel displayFloorPlan() {
//        tables = seatingAlgorithm.generateTables();
//        floorPlan.generateFloorPlan(tables);
//        floorPlan.displayFloorPlan();
        System.out.println("pretend we have these classes");
        return null;
    }

    private void writeToFile() {
        try {
            output = new PrintWriter(new File("Students.txt"));
        } catch(Exception e) {
            System.out.println("oops");
        }
        for (int i = 0; i < students.size(); i++) {
            output.println(students.get(i).getName() + ", " + students.get(i).getStudentNumber() + ", " + students.get(i).getDietaryRestrictions() + ", " + students.get(i).getFriendStudentNumbers());
        }
        output.close();
    }

    private void readFile() {
        System.out.println("this is doing something");
        //add a bunch of temp variables here
//        input.nextLine();
//        tempStudent = new Student();
//        savedStudentList.add(tempStudent);
        //return savedStudentList;
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent press) {
            if (press.getSource() == addStudentButton) {
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(addStudentsPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == removeStudentButton) {
                //readFile();
                overallPanel.removeAll();
                overallPanel.add(backgroundPanel, new Integer(0));
                overallPanel.add(removeStudentsPanel, new Integer(100));
                frame.repaint();
            } else if (press.getSource() == displayFloorPlanButton) {
                displayFloorPlan();
            } else if (press.getSource() == exitProgramButton) {
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
            }
        }
    }

}
