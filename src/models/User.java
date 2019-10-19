package models;

public class User {
  private int idUser;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNum;
  
  public User() {
    //no arg constructor
  }
  
  public User(int idUser, String username, String password, String firstName, 
      String lastName, String email, String phoneNum) {
    this.idUser = idUser;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNum = phoneNum;
  }
  
  public int getIdUser() {
    return idUser;
  }
  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhoneNum() {
    return phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }
}
