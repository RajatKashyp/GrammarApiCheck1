package com.demo.touchstone.grammarapicheck;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by touchstone on 4/4/2017.
 */

public class ErrorListAdapter extends ArrayAdapter<String>  {
    private ArrayList<String> errorsList;
    Context context ;


    public ErrorListAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList errorsList) {
        super(context, resource);
        this.context = context ;
        this.errorsList = errorsList ;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        String value = getItem(position) ;
        return  value ;
    }

    @Override
    public int getPosition(@Nullable String item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView ;

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.error_list_view_layout, null);

            TextView errorText = (TextView)v.findViewById(R.id.errorstextview) ;

        }


        return v ;

    }
}
