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
@Table(name="path")
public class Picture {
    
    @Id
    @GeneratedValue
    
    private String path;
    private int author;
    private String date;
    private int vote;
    private long latitude;
    private long longitude;
    

    
}
