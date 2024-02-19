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
     private EditText idnum1;
     private EditText password;
     private TextView create ;
     private Button loginb ;
      private SharedPreferences sp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password = findViewById(R.id.password);
        idnum1 = findViewById(R.id.id1);
        create = findViewById(R.id.newaccount);
        loginb = findViewById(R.id.loginb);
        String idlog = idnum1.getText().toString();
        String passlog = password.getText().toString();
        sp = getSharedPreferences("userpref", Context.MODE_PRIVATE);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
            }
        });

        loginb.setOnClickListener(new View.OnClickListener() {
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