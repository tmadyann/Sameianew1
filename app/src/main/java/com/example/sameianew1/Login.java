package com.example.sameianew1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    public static final String TAG = "Login";
    public static final int REQUEST_CODE = 786;
    private EditText userNameET;
    private EditText passwordET;
    private TextView createTV;
    private Button loginBtn;
    private DatabaseReference dbr;

    private SharedPreferences sp;
    private String userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbr = FirebaseDatabase.getInstance().getReference();
        passwordET = findViewById(R.id.password_login);
        userNameET = findViewById(R.id.user_name_login);
        createTV = findViewById(R.id.create_account_login);
        loginBtn = findViewById(R.id.login_btn);
        sp = getSharedPreferences("data.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        createTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameET.getText().toString();
                dbr.child("UsersType").child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        DataSnapshot ds = task.getResult();
                        if (ds.exists()) {

                            userType = ds.getValue(String.class);
                            dbr.child("Users").child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    DataSnapshot dataSnapshot = task.getResult();
                                    User user = null;
                                    if (userType.equals("T0")) {
                                        user = dataSnapshot.getValue(PersonalUser.class);
                                        Log.d(TAG,user.toString());
                                    } else {
                                        user = dataSnapshot.getValue(CompanyUser.class);
                                    }
                                    String pwd = passwordET.getText().toString();
                                    if (pwd.equals(user.getPassword())) {
                                            editor.putString("type",userType);
                                            editor.commit();
                                            if( userType.equals("T0")) {
                                                Intent intent = new Intent(Login.this,PersonalActivity.class);
                                                startActivity(intent);
                                            }else{
                                                Intent intent = new Intent(Login.this,CompanyActivity.class);
                                                startActivity(intent);
                                            }
                                    } else {
                                        Toast.makeText(Login.this,"Error password",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }else{
                            Toast.makeText(Login.this,"Error user name",Toast.LENGTH_LONG).show();

                        }
                    }


                });
//
            }
        });

        createTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        userNameET.setText(data.getStringExtra("userName"));
        passwordET.setText(data.getStringExtra("password"));
    }
}