package com.example.sameianew1;

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
    private EditText idNumET ;
    private EditText companyNameET ;
    private EditText passowrdET ;
    private EditText passowrdConET ;
    private EditText phoneNumET;
    private Button createAccountBTN ;
    FirebaseDatabase database;
    DatabaseReference refernce ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        idNumET = findViewById(R.id.id_num_signup);
        companyNameET=findViewById(R.id.companyName_signup);
        passowrdET = findViewById(R.id.password_signup);
        passowrdConET = findViewById(R.id.passwordcon_signup);
        phoneNumET = findViewById(R.id.phone_num_signup);
        createAccountBTN= findViewById(R.id.createaccount_signup);

      createAccountBTN.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              database = FirebaseDatabase.getInstance();
              refernce = database.getReference("users");
             String idnumbert=  idNumET.getText().toString();
             String companyNamet = companyNameET.getText().toString();
             String passowrdt = passowrdET.getText().toString();
             String passwordcont = passowrdConET.getText().toString();
             String phonenumt = phoneNumET.getText().toString();
             if(!passowrdt.equals(passwordcont)) {
                 Toast.makeText(Signup.this,"סיסמאות לא תואמות",Toast.LENGTH_LONG).show();
             }
              CompanyUser CompanyUser = new CompanyUser(companyNamet,phonenumt,passowrdt,0,idnumbert);
             refernce.child(companyNamet).setValue(CompanyUser);
              Intent intent = new Intent(Signup.this,Login.class);
              int idforsignin = Integer.parseInt(idnumbert);
              int passforsignin = Integer.parseInt(passowrdt);
              intent.putExtra("idnumber",idforsignin);
              intent.putExtra("pass",passforsignin);
               startActivity(intent);
          }
      });

    }
}