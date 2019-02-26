
import java.util.ArrayList;


/**
 * Student
 * This class represents the individual students in the ticketing system
 * @author Arjun Pillai and Joyce Huang
 * @since Feb 22 2019
 */
public class Student {
  String name;
  String studentNumber;
  ArrayList<String> dietaryRestrictions;
  ArrayList<String> friendStudentNumbers;
  
  
  /**
   * Constructor for Student 
   * @param name The name of the student
   * @param studentNumber The number of the student
   * @param dietaryRestrictions The array list of the student's dietary restrictions
   * @param friendStudentNumbers The array list of the numbers of the Student's friends
   */
  Student(String name, String studentNumber, ArrayList<String> dietaryRestrictions, ArrayList<String> friendStudentNumbers){
    this.name=name;
    this.studentNumber=studentNumber;
    this.dietaryRestrictions=dietaryRestrictions;
    this.friendStudentNumbers=friendStudentNumbers;
  }
  
  
  /**
   *This method takes in a name and sets it as the student's name.
   * @param name The new name for the student
   */
  public void setName(String name){
    this.name=name; 
  }
  
  /**
   * This method gets the name of the Student
   * @return the name of the Student 
   */
  public String getName(){
    return name; 
  }
  
  /**
   *This method takes in a number and sets it as the student's number.
   * @param studentNumber The new number of the student
   */
  public void setStudentNumber(String studentNumber){
    this.studentNumber=studentNumber; 
  }
  
  /**
   * This method gets the number of the Student
   * @return Student's number
   */
  public String getStudentNumber(){
    return studentNumber;
  }
  
  /**
   *This method takes in an arraylist of the student's dietary restrictions and assigns it to the student.
   * @param dietaryRestrictions The new dietary restrictions of the student
   */
  public void setDietaryRestrictions(ArrayList<String> dietaryRestrictions){
    this.dietaryRestrictions=dietaryRestrictions; 
  }
  
  /**
   * This method gets the dietary restrictions of the Student
   * @return Student's dietary restrictions
   */
  public ArrayList<String> getDietaryRestrictions(){
    return dietaryRestrictions;
  }
  
  /**
   *This method takes in an arraylist of the student's friend's numbers and assigns it to the student.
   * @param friendStudentNumbers The new list of the student's friend's numbers
   */
  public void setFriendStudentNumbers (ArrayList<String> friendStudentNumbers){
    this.friendStudentNumbers=friendStudentNumbers; 
  }
  
  /**
   * This method gets the array list of the Student's friend's numbers.
   * @return Student's array list of friends numbers
   */
  public ArrayList<String> getFriendStudentNumbers (){
    return friendStudentNumbers; 
  }
  
}