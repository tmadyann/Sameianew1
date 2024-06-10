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
        TextView companyNameTV = convertView.findViewById(R.id.company_name_tv_orders);
        TextView numbersOfKubsTV = convertView.findViewById(R.id.number_of_kubs_orders);

        Order order = getItem(position);
        companyNameTV.setText(order.getCompanyUserName());
        numbersOfKubsTV.setText( order.getNumOfKubs()+"");
        return convertView;
    }
}
