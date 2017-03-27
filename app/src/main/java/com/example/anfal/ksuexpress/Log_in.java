package com.example.anfal.ksuexpress;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Log_in extends AppCompatActivity {

    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    public void onLogin(View view){

        String un = username.getText().toString();
        String pw = password.getText().toString();
        String type = "login";
        backgroundWorker newBackground = new backgroundWorker(this);
        newBackground.execute(type, un, pw);
    }

    /** Called when the user clicks the forget password button */
    public void forgetPassword(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("نسيت كلمة المرور");
        alertDialog.setMessage("لم يتم تفعيل الخاصية بعد. :$");
        alertDialog.show();
        //Intent intent = new Intent(this, ......class);
        //startActivity(intent);
    }

}
