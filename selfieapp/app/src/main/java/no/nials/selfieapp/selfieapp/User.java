package no.nials.selfieapp.selfieapp;

/**
 *
 * user	userId	email	password	phone(unique)(primaryKey)	name	birthday	score
 *
 * Created by Kami on 30.10.2017.
 */


public class User {
    private int id;
    private String email, phone, gender;

    public User(int id, String email, String phone, String gender) {
        this.id = id;
        this.email = email;
        this.phone = phone;
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