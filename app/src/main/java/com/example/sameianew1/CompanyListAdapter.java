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

public class CompanyListAdapter extends ArrayAdapter<CompanyUser> {
    private Context context;
    private int resource;

    public CompanyListAdapter(@NonNull Context context, int resource, @NonNull List<CompanyUser> objects) {
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

        TextView companyNameTV = convertView.findViewById(R.id.company_list_name_tv);
        TextView phoneNumberCompany = convertView.findViewById(R.id.company_list_phone_num_tv);

        CompanyUser companyUser = getItem(position);
        companyNameTV.setText(companyUser.getCompanyName());
        phoneNumberCompany.setText( companyUser.getPhoneNumber());
        return convertView;
    }
}
