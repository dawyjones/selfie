package no.nials.selfieapp.selfieapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kristoffer on 11/21/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://nials.no:8080/selfie/api/user/newUser";
    private Map<String, String> params;

    public RegisterRequest(String email, String password, int phone, String name, String birthday, String gender, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("phone", phone + "");
        params.put("name", name);
        params.put("birthday", birthday);
        params.put("gender", gender);

    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
