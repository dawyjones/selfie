package no.nials.selfieapp.selfieapp;

/**
 *
 * user	userId	email	password	phone(unique)(primaryKey)	name	birthday	score
 *
 * Created by Kami on 30.10.2017.
 */


public class User {
 //   private int id;
    private String email, phone, gender, birthday, name;

    public User( String name, String email, String phone, String birthday, String gender) {
        //this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }
    //public int getId(){ return id;}

    public String getName() { return name;}
    public String getEmail() {
        return email;
    }

    public String getPhone() {return phone;}

    public String getGender() {
        return gender;
    }

    public String getBirthday() { return birthday;}
}