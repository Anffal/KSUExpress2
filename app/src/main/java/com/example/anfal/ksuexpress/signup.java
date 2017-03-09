package com.example.anfal.ksuexpress;

import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    EditText username, password, name;
    Switch  blind;
    Button reg;
    String visually_impaired, buildings, beacon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        blind = (Switch) findViewById(R.id.blindOpt);
        reg = (Button) findViewById(R.id.register);

    }


    public void register(View view){

        String un = username.getText().toString();
        String pw = password.getText().toString();
        String na = name.getText().toString();
        Boolean blind_opt = blind.isChecked();

        if (blind_opt){
            visually_impaired = "y";
            buildings = "y";
            beacon = "y";
        }
        else {
            visually_impaired = "n";
            buildings = "n";
            beacon = "n";
        }

        //Checking information....

        //Register new user
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    /*
                    AlertDialog.Builder g = new AlertDialog.Builder(signup.this);
                    g.setMessage("success: "+ success)
                            .setNegativeButton("OK", null)
                            .create()
                            .show();
                    */

                    if (success){
                        Intent intent = new Intent(signup.this, Log_in.class);
                        signup.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(signup.this);
                        builder.setMessage("فشل التسجيل. قد يكون عنوان البريد الإلكتروني مستخدم بالفعل.")
                                .setNegativeButton("المحاولة مرة أخرى", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(na, un, pw, visually_impaired, beacon, buildings, responseListener);
        RequestQueue queue = Volley.newRequestQueue(signup.this);
        queue.add(registerRequest);

    }

    public boolean checkName(String name){
        if (name.length() <= 30 && name != null && name != "")
            return true;
        else
            return false;
    }

    public boolean checkEmail(String email){
        if (email.length() <= 30 && email != null && email != "") {
            Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
            return matcher.find();
        }
        else
            return false;
    }

    public boolean checkPassword(String password){
        if (password.length() >= 12 && password.length() <= 30 &&
                password != null && password != "")
            return true;
        else
            return false;
    }


}
