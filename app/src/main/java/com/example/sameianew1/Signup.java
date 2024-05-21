package com.example.sameianew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText idNumET;
    private EditText companyNameET;
    private EditText passowrdET;
    private EditText passowrdConET;
    private EditText phoneNumET;
    private EditText cityNameET;
    private EditText userNameET;
    private Button createAccountBTN;
    private int type; // 0 client  1 company
    private RadioGroup radioGroup;
    private RadioButton clientRadioBtn;
    FirebaseDatabase database;
    DatabaseReference refernce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        type = 0;
        idNumET = findViewById(R.id.id_num_signup);
        companyNameET = findViewById(R.id.companyName_signup);
        passowrdET = findViewById(R.id.password_signup);
        passowrdConET = findViewById(R.id.passwordcon_signup);
        phoneNumET = findViewById(R.id.phone_num_signup);
        createAccountBTN = findViewById(R.id.createaccount_signup);
        cityNameET = findViewById(R.id.city_name_signup);
        firstNameET = findViewById(R.id.firstName_signup);
        lastNameET = findViewById(R.id.lastName_signup);
        radioGroup = findViewById(R.id.radio_group_signup);
        clientRadioBtn = findViewById(R.id.client_rb);
        userNameET = findViewById(R.id.userName_signup);

        refernce = FirebaseDatabase.getInstance().getReference();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.client_rb) {
                    companyNameET.setVisibility(View.GONE);
                    firstNameET.setVisibility(View.VISIBLE);
                    lastNameET.setVisibility(View.VISIBLE);
                    idNumET.setVisibility(View.VISIBLE);
                    type = 0;
                } else {
                    companyNameET.setVisibility(View.VISIBLE);
                    firstNameET.setVisibility(View.GONE);
                    lastNameET.setVisibility(View.GONE);
                    idNumET.setVisibility(View.GONE);
                    type = 1;
                }
            }
        });


        createAccountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passowd = passowrdET.getText().toString();
                String passwordcon = passowrdConET.getText().toString();
                String phonenum = phoneNumET.getText().toString();
                String city = cityNameET.getText().toString();
                String userName = userNameET.getText().toString();
                User user = null;
                Intent intent = new Intent();
                intent.putExtra("type", type);
                intent.putExtra("userName", userName);
                intent.putExtra("password", passowd);
                intent.putExtra("city", city);
                if (type == 0) {
                    String firstName = firstNameET.getText().toString();
                    String lastName = lastNameET.getText().toString();
                    String id = idNumET.getText().toString();
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    user = new PersonalUser(userName, passowd, phonenum, firstName, lastName, id, city);

                } else {
                    String companyName = companyNameET.getText().toString();
                    intent.putExtra("companyName", companyName);
                    user = new CompanyUser(userName, passowd, phonenum, companyName, city);
                }
                boolean allValid = true;
                if (!ValidateInput.isUserNameValid(userName)) {
                    allValid = false;
                }

                if (allValid) {
                    refernce.child("Users").child(user.getUserName()).setValue(user);
                    refernce.child("UsersType").child(user.getUserName()).setValue("T"+type);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

    }
}