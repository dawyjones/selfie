package selfie;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
    A class for the object "User"
    The class objects have the methods for returning and setting variables
    which returns the information to the database and changes it via the
    Class "UserResrouce".
*/

/**
 *
 * @author Frank Even Valde
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="user")
public class User implements Serializable {
    
    @Id
    @GeneratedValue
    private int userId;
    private String email;
    private String password;
    private int phone;
    private String name;
    private String birthday;
    private String gender;

    public int getUserId()
    {
        return userId;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirhtday(String birhtday) {
        this.birthday = birhtday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

   public String getBirhtday() {
       return birthday;
    }
    
    public String getGender()  {
        return gender;
    }
    
}
