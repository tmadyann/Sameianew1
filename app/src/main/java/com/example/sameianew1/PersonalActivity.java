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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private DatePicker datePickerDP;
    private Button orderBtn;
    private CardView cardViewCD;
    private String location;
    private String phoneNum;
    private RadioGroup radiopayment;
    private RadioButton radiovisa;
    private RadioButton radiocash;
    private RadioButton radiocheque;
    private int payment ;

//1cash 2 visa 3cheque


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
        datePickerDP = findViewById(R.id.date_picker_actions);
        orderBtn = findViewById(R.id.order_btn_personal);
        cardViewCD = findViewById(R.id.input_order_cardview);
        radiocash = findViewById(R.id.cash);
        radiocheque = findViewById(R.id.cheque);
        radiovisa= findViewById(R.id.visa);
        radiopayment= findViewById(R.id.radio_group_payment);


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
        databaseReference.child("Users").child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot=task.getResult();
                PersonalUser personalUser=snapshot.getValue(PersonalUser.class);
                location=personalUser.getCity();
                phoneNum=personalUser.getPhoneNumber();

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
                radiopayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.cash)
                            payment = 1;
                      if (checkedId == R.id.visa)
                            payment = 2;
                        if(checkedId == R.id.cheque)
                            payment = 3;


                    }
                });
                int numOfKubs = Integer.parseInt(numberOfKubsET.getText().toString());
                String payMentMethod ="";
                if(payment==1)
                    payMentMethod="מזומן";
                if(payment==2)
                    payMentMethod="אשראי";
                if(payment==3)
                    payMentMethod="צֶ'ק";

                String date = datePickerDP.getDayOfMonth()+"/"+datePickerDP.getMonth() +
                                 "/" + datePickerDP.getYear();
                Order order = new Order(date,payMentMethod,numOfKubs,userName,phoneNum,comapnyUserName,location);
                databaseReference.child("Orders").push().setValue(order);
                companyListView.setOnItemClickListener(onItemClickListener);
            }
        });

    }
}