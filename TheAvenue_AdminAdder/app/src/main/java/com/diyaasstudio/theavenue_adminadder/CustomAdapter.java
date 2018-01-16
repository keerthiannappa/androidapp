package com.diyaasstudio.theavenue_adminadder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Keerthi on 11/28/2017.
 */

public class CustomAdapter extends ArrayAdapter<AdminDetails> {

    private ArrayList<AdminDetails> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtNumber;
        TextView txtEmail;
    }



    public CustomAdapter(ArrayList<AdminDetails> data, Context context) {
        super(context, R.layout.rowitem, data);
        this.dataSet = data;
        this.mContext=context;
    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AdminDetails dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.rowitem, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name);
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.Number);
            viewHolder.txtEmail = (TextView) convertView.findViewById(R.id.Email);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtNumber.setText(dataModel.getNumber());
        viewHolder.txtEmail.setText(dataModel.getEmail());
        // Return the completed view to render on screen
        return convertView;
    }


}
