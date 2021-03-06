package com.example.kidmd;

//User class with name, age, and email variables and getters; and a getter for Firebase-generated User ID
public class User {

    public String fullName, age, email;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String uid;

    public User() {}

    public User(String fullName, String age, String email) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

}
