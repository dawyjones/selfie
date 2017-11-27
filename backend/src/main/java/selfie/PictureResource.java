/**
 * Class to create new paths for pictures.
 * 
 * 
 * 
 */
package selfie;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Frank Even Valde
 */
@Path("picture")
@Stateless
public class PictureResource {
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Picture> getPaths() {
        return em.createQuery("SELECT u FROM Picture u",Picture.class).getResultList();
    }
    
    @POST
    @Path("newpicture")
   //@Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON)
    public Response newPicture(String pictureJson,
                               @QueryParam(value = "author") int author)
    {
        Picture picture = new Picture();
        picture.setAuthor(author);
        picture.setpicture(pictureJson);
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String date = String.valueOf(year) + "-" + String.valueOf(month) + "-"
                      + String.valueOf(day);
        picture.setDate(date);
        
        picture.setVote(0);
        
        em.persist(picture);
        System.out.println("gjreiohewpfgjerwoip");
        return Response.ok(picture).build();
        
    }
}
