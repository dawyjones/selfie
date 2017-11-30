/*package no.nials.selfieapp.selfieapp;

/**
 *
 * user	userId	email	password	phone(unique)(primaryKey)	name	birthday	score
 *
 * Created by Kami on 30.10.2017.
 */
/*

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
} */

package no.nials.selfieapp.selfieapp;



public class User {
    private int id, phone;
    private String email, password, birthday, gender, name;

    public User(int id, String email, String password, int phone, String name, String birthday, String gender) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.password = password;

    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getbirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
}