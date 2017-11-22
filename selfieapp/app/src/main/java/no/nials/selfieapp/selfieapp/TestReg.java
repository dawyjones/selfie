package no.nials.selfieapp.selfieapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Kami on 15.11.2017.
 */

public class TestReg  extends AppCompatActivity {

    Context c;

    EditText editTextPhone, editTextEmail, editTextPassword, editTextBirthday, editTextName;
    RadioGroup radioGroupGender;

    String password;
    String email;
    String name;
    String birthday;
    String phone;
    String url = "nials.no:8080/api/api/user/newUser";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = this;

        setContentView(R.layout.activity_main);

        //Casting
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextBirthday = (EditText) findViewById(R.id.editTextBirthday);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);


        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  _("Login button hit");

                email = editTextEmail.getText() + "";
                name = editTextName.getText() + "";
                phone = editTextPhone.getText() + "";
                password = editTextPassword.getText() + "";

                if (phone.length() == 0 || name.length() == 0 || email.length() == 0 || password.length() == 0) {
                    Toast.makeText(c, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.length() > 0 && name.length() > 0 && email.length() > 0 && password.length() > 0) {
                    //Do networking
                    Networking n = new Networking();
                    n.execute(url, Networking.NETWORK_STATE_REGISTER);
                }

            }
        });
    }
}




