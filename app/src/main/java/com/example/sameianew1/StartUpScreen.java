package com.example.sameianew1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;


public class StartUpScreen extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_screen);
        Thread thread = new Thread(this);
        thread.start();


    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Intent intent = new Intent();
        SharedPreferences sharedPreferences = getSharedPreferences("file.data", MODE_PRIVATE);
        String st = sharedPreferences.getString("type", "");

        if (st.equals("T0"))
            intent = new Intent(StartUpScreen.this, PersonalUser.class);
        else if (st.equals("T1"))
            intent = new Intent(StartUpScreen.this, CompanyUser.class);
        else intent = new Intent(StartUpScreen.this, Login.class);
        startActivity(intent);

    }

}