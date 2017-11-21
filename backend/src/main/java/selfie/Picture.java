/**
 * A class for creating object of the type "path".
 * Paths are used to find pictures on the server.
 * The path is saved in the database with the class "PathResource".
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
package selfie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Frank Even Valde
 */

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="picture")
public class Picture {
    
    @Id
    @GeneratedValue
    
    private String pictureJson;
    private int author;
    private String date;
    private int vote;

    public String getPictureJson() {
        return pictureJson;
    }

    public int getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public int getVote() {
        return vote;
    }


    public void setpicture(String pictureJson) {
        this.pictureJson = pictureJson;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
    
    

    
}
