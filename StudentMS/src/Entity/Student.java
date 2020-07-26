/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author khanh
 */
public class Student {


    private int studentId = 0;
    
    private String rollNo;
    
    private String name;

    private String birthdate;

    private String gender;

    private String address;

    private String email;

    private String phone;

    private int accId;

    public Student() {
        
    }

    public Student(String rollNo, String name, String birthday, String gender, String address, String email, String phone, int accId) {
        this.rollNo = rollNo;
        this.name = name;
        this.birthdate = birthday;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.accId = accId;
    }

    public Student(int studentId, String rollNo, String name, String birthdate, String gender, String address, String email, String phone, int accId) {
        this.studentId = studentId;
        this.rollNo = rollNo;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.accId = accId;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthday) {
        this.birthdate = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }


    
}
