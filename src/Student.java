import java.util.ArrayList;

public class Student {
  String name;
  String studentNumber;
  ArrayList<String> dietaryRestrictions;
  ArrayList<String> friendStudentNumbers;
  
  Student(String name, String studentNumber, ArrayList<String> dietaryRestrictions, ArrayList<String> friendStudentNumbers){
  this.name=name;
  this.studentNumber=studentNumber;
  this.dietaryRestrictions=dietaryRestrictions;
  this.friendStudentNumbers=friendStudentNumbers;
  }
  
  public void setName(String name){
   this.name=name; 
  }
  
  public String getName(){
   return name; 
  }
  
  public void setStudentNumber(String studentNumber){
   this.studentNumber=studentNumber; 
  }
  
  public String getStudentNumber(){
    return studentNumber;
  }
  
  public void setDietaryRestrictions(ArrayList<String> dietaryRestrictions){
   this.dietaryRestrictions=dietaryRestrictions; 
  }
  
  public ArrayList<String> getDietaryRestrictions(){
    return dietaryRestrictions;
  }
  
  public void setFriendStudentNumbers (ArrayList<String> friendStudentNumbers){
   this.friendStudentNumbers=friendStudentNumbers; 
  }
  
   public ArrayList<String> getFriendStudentNumbers (){
   return friendStudentNumbers; 
  }
  
}