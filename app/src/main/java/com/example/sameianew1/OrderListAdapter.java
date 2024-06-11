package com.example.sameianew1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;

public class OrderListAdapter extends ArrayAdapter<Order> {
    private Context context;
    private int resource;
    private List<Order> orders;



    public OrderListAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if( convertView == null )
            convertView = layoutInflater.inflate(resource,parent,false);
        TextView clientnameTV = convertView.findViewById(R.id.client_name);
        TextView numbersOfKubsTV = convertView.findViewById(R.id.number_of_kubs_orders);
        TextView  phoneNumberTV= convertView.findViewById(R.id.client_phone_num);
        TextView location = convertView.findViewById(R.id.location);
        TextView paymentmethodTV = convertView.findViewById(R.id.payment_method);

        Order order = getItem(position);
        numbersOfKubsTV.setText( order.getNumOfKubs()+"");
        clientnameTV.setText( order.getPersonalUserName()+"");
        location.setText(order.getLocation());
        phoneNumberTV.setText(order.getPhoneNum());
        paymentmethodTV.setText(order.getPayMethod());

        return convertView;
    }
}
