package com.example.sameianew1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private EditText idnumber ;
    private EditText username ;
    private EditText passowrd ;
    private EditText passowrdcon ;
    private EditText phonenum;
    private Button createaccount ;
    FirebaseDatabase database;
    DatabaseReference refernce ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        idnumber = findViewById(R.id.idnum);
        username=findViewById(R.id.username);
        passowrd = findViewById(R.id.password);
        passowrdcon = findViewById(R.id.password2);
        phonenum = findViewById(R.id.phonenum);
        createaccount = findViewById(R.id.createaccount);

      createaccount.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              database = FirebaseDatabase.getInstance();
              refernce = database.getReference("users");
             String idnumbert=  idnumber.getText().toString();
             String usernamet = username.getText().toString();
             String passowrdt = passowrd.getText().toString();
             String passwordcont = passowrdcon.getText().toString();
             String phonenumt = phonenum.getText().toString();
             if(!passowrdt.equals(passwordcont)) {
                 Toast toast = Toast.makeText(Signup.this,"סיסמאות לא תואמות", 5);
                 toast.show();
             }
              User user = new User(usernamet,phonenumt,passowrdt,idnumbert);
             refernce.child(usernamet).setValue(user);
              Intent intent = new Intent(Signup.this,Login.class);
              int id = Integer.parseInt(idnumbert);
              int passw = Integer.parseInt(passowrdt);
              intent.putExtra("idnumber",id);
              intent.putExtra("pass",passw);
              sta
          }
      });

    }
}