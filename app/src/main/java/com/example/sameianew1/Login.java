package com.example.sameianew1;

import androidx.annotation.Nullable;
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
    public static final int REQUEST_CODE = 786;
     private EditText idnumsignET;
     private EditText passwordET;
     private TextView createTV ;
     private Button loginBtn;

      private SharedPreferences sp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordET = findViewById(R.id.password_login);
        idnumsignET = findViewById(R.id.id_login);
        createTV = findViewById(R.id.create_account_login);
        loginBtn = findViewById(R.id.login_btn);
        String idlog = idnumsignET.getText().toString();
        String passlog = passwordET.getText().toString();
        sp = getSharedPreferences("userpref", Context.MODE_PRIVATE);
        createTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userid",idlog);
                editor.putString("userpass",passlog);
                editor.commit();
            }
        });

        createTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}