package com.example.agri_expertfinder;

public class Farmer {

    private String  Name;
    private String Email;

    // empty constructor

    public  Farmer()
    {

    }

    public Farmer(String name, String email) {
        Name = name;
        Email = email;
    }

    // getters and setters

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
