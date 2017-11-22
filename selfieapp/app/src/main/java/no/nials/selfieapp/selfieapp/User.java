package no.nials.selfieapp.selfieapp;



public class User {
    private int id;
    private String email, phone, gender;

    public User(int id, String email, String phone, String gender) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }
    public int getId(){ return id;}

    public String getEmail() {
        return email;
    }

    public String getPhone() {return phone;}

    public String getGender() {
        return gender;
    }
}

