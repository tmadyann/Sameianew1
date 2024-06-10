package com.example.sameianew1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {
    public static final int COMPANY = 1;
    List<CompanyUser> companyList;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    CompanyListAdapter companyListAdapter;
    ListView companyListView;
    private String comapnyUserName;
    private String userName;
    private EditText numberOfKubsET;
    private EditText paymentMethodET;
    private DatePicker datePickerDP;
    private Button orderBtn;
    private CardView cardViewCD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numberOfKubsET = findViewById(R.id.number_of_kubs);
        paymentMethodET = findViewById(R.id.payment_method);
        datePickerDP = findViewById(R.id.date_picker_actions);
        orderBtn = findViewById(R.id.order_btn_personal);
        cardViewCD = findViewById(R.id.input_order_cardview);


        companyList = new ArrayList<CompanyUser>();
        sharedPreferences = getSharedPreferences("data.txt",MODE_PRIVATE);
        userName = sharedPreferences.getString("userName","");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").orderByChild("userType").equalTo(COMPANY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        CompanyUser companyUser = ds.getValue(CompanyUser.class);
                        companyList.add(companyUser);
                    }
                    companyListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        companyListView = findViewById(R.id.company_list);
        companyListAdapter = new CompanyListAdapter(this,R.layout.company_item,companyList);
        companyListView.setAdapter(companyListAdapter);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cardViewCD.setVisibility(View.VISIBLE);
                companyListView.setOnItemClickListener(null);
                comapnyUserName = companyList.get(position).getUserName();


            }
        };
        companyListView.setOnItemClickListener(onItemClickListener);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewCD.setVisibility(View.GONE);
                int numOfKubs = Integer.parseInt(numberOfKubsET.getText().toString());
                String payMentMethod = paymentMethodET.getText().toString();
                String date = datePickerDP.getDayOfMonth()+"/"+datePickerDP.getMonth() +
                                 "/" + datePickerDP.getYear();
                Order order = new Order(date,payMentMethod,numOfKubs,userName,comapnyUserName);
                databaseReference.child("Orders").push().setValue(order);
                companyListView.setOnItemClickListener(onItemClickListener);
            }
        });

    }
}