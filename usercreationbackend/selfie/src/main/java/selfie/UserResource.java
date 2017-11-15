package selfie;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
    Uses the "User" class to report to the database via http requests
 */

/**
 *
 * @author Frank Even Valde
 */

@Path("user")
@Stateless
public class UserResource {
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u",User.class).getResultList();
    }
    
    
    @POST
    @Path("newUser")
    public Response newUser(@QueryParam(value = "name") String name,
                            @QueryParam(value = "birthday") Date birthday,
                            @QueryParam(value = "email") String email,
                            @QueryParam(value = "password") String password,
                            @QueryParam(value = "phone") int phone,
                            @QueryParam(value = "score") int score,
                            @QueryParam(value = "gender") String gender)
    {
        User user = new User();
        user.setName(name);
        user.setBirhtday(birthday);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setScore(score);
        user.setGender(gender);
        em.persist(user);
        return Response.ok(name).build();
    }
}
