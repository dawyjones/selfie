/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selfie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author fraval
 */
@Path("test")
public class MyServicee {
    @GET
    public String getHello() {
        return "Hello";
    }
}
