package com.example.agri_expertfinder;

public class Eperts {

    private String Name;
    private String location;
    private String Email;
    private String Password;

    // empty contractor
    public Eperts()
    {

    }

    // contractor
    public Eperts(String name, String location, String email, String password) {
        Name = name;
        this.location = location;
        Email = email;
        Password = password;
    }

    // getters and    getters

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
