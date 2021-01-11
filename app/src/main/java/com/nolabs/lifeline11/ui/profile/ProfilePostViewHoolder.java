package com.nolabs.lifeline11.ui.profile;


import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.nolabs.lifeline11.R;

import de.hdodenhof.circleimageview.CircleImageView;

class ProfilePostViewHoolder extends RecyclerView.ViewHolder {
    CircleImageView authorimg;
    MaterialCardView container;
    MaterialTextView authorname;
    MaterialTextView date;
    MaterialTextView title;
    MaterialTextView body;
    MaterialTextView status;
    MaterialTextView needs;
    MaterialTextView city;
    MaterialTextView quantity;
    ProgressBar progressBar;
    MaterialTextView donated;

    public ProfilePostViewHoolder(@NonNull View itemView) {
        super(itemView);
        authorimg = itemView.findViewById(R.id.card_feed_profile_item_data_author_img);
        container = itemView.findViewById(R.id.card_feed_profile_item_data_container);
        authorname = itemView.findViewById(R.id.card_feed_profile_item_data_author);
        date = itemView.findViewById(R.id.card_feed_profile_item_data_date);
        title = itemView.findViewById(R.id.card_feed_profile_item_data_title);
        body = itemView.findViewById(R.id.card_feed_profile_item_data_body);
        status = itemView.findViewById(R.id.card_feed_profile_item_data_status);
        needs = itemView.findViewById(R.id.card_feed_profile_item_data_needs);
        city = itemView.findViewById(R.id.card_feed_item_profile_item_data_city);
        quantity = itemView.findViewById(R.id.card_feed_profile_item_data_max);
        progressBar = itemView.findViewById(R.id.card_feed_profile_item_data_progressbar);
        donated = itemView.findViewById(R.id.card_feed_profile_item_data_donated);
    }
}
