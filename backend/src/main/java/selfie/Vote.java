package selfie;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
    A class for the object "Vote"
    The objects of this class is used to print information
    into the database. Votes is the value of each picture.
    This class is used by "VoteResource"
*/

/**
 *
 * @author Frank Even Valde
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="Vote")
public class Vote implements Serializable {
    
    @Id
    @GeneratedValue
    private int voteID;
    private int pictureID;
    private String date;
    
    public int getVoteID() {
        return voteID;
    }
    public int getPictureID() {
        return pictureID;
    }

    public String getDate() {
        return date;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
              