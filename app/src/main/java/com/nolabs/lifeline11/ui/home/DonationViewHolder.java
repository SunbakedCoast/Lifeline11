package com.nolabs.lifeline11.ui.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.nolabs.lifeline11.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DonationViewHolder extends RecyclerView.ViewHolder {

    CircleImageView donorimg;
    MaterialTextView name;
    MaterialTextView donated;
    MaterialTextView date;


    public  DonationViewHolder(@NonNull View itemView) {
        super(itemView);
        donorimg = itemView.findViewById(R.id.card_donor_item_data_profileimg);
        name = itemView.findViewById(R.id.card_donor_item_data_fullname);
        donated = itemView.findViewById(R.id.card_donor_item_data_donated);
        date = itemView.findViewById(R.id.card_donor_item_data_date);
    }
}
