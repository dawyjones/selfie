package no.nials.selfieapp.selfieapp;



public class User {
<<<<<<< HEAD
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
=======
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
>>>>>>> origin/Kristoffer

    public String getName() { return name;}
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

    public String getBirthday() { return birthday;}
}