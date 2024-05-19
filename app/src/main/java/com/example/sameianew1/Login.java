package com.example.sameianew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
     private EditText idnumsignET;
     private EditText passwordET;
     private TextView createTV ;
     private Button loginBTN ;

      private SharedPreferences sp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordET = findViewById(R.id.password_login);
        idnumsignET = findViewById(R.id.id_login);
        createTV = findViewById(R.id.newaccount_signin);
        loginBTN = findViewById(R.id.login_btn);
        String idlog = idnumsignET.getText().toString();
        String passlog = passwordET.getText().toString();
        sp = getSharedPreferences("userpref", Context.MODE_PRIVATE);
        createTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);

            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userid",idlog);
                editor.putString("userpass",passlog);
                editor.commit();
            }
        });

    }
}