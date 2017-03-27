package com.example.anfal.ksuexpress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the login button */
    public void openLoginActivity(View view) {
        Intent intent = new Intent(this, Log_in.class);
        startActivity(intent);
    }


    /** Called when the user clicks the signup button */
    public void openSignupActivity(View view) {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }


}
