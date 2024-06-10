package com.example.sameianew1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyActivity extends AppCompatActivity {
    private ListView ordersLV;
    private List<Order> orders;
   // private Map<String,User> users;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPreferences;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_company);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sharedPreferences = getSharedPreferences("data.txt",MODE_PRIVATE);
        userName = sharedPreferences.getString("userName","");

        ordersLV = findViewById(R.id.order_list);
        orders = new ArrayList<Order>();
        OrderListAdapter orderListAdapter = new OrderListAdapter(this,R.layout.order_item,orders);
        ordersLV.setAdapter(orderListAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Orders").orderByChild("companyUserName").equalTo(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren() ){
                        Order order = dataSnapshot.getValue(Order.class);
                        orders.add(order);
                    }
                    orderListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    users = snapshot.getValue(Map.class);
//                    Log.d("Users",users.toString());
////                    for(DataSnapshot dataSnapshot : snapshot.getChildren() ){
////                        Order order = dataSnapshot.getValue(Order.class);
////                        orders.add(order);
////                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}