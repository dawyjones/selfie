/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selfie;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author fraval
 */

@Path("vote")
@Stateless
public class VoteResource {
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vote> getUsers() {
        return em.createQuery("SELECT u FROM Vote u",Vote.class).getResultList();
    }
    
@POST
@Path("newvote")
public Response newVote(@QueryParam(value =  "pictureId") int pictureId)
    {
        Vote vote = new Vote();
        //create current date
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        String date = String.valueOf(year) + "-" + String.valueOf(month) + "-"
                      + String.valueOf(day);
        vote.setDate(date);
        vote.setPictureID(pictureId);
        em.persist(vote);
        return Response.ok(pictureId).build();
    }
}

