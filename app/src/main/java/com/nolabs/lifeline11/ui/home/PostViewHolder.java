package com.nolabs.lifeline11.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.ui.profile.ProfileViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PostViewHolder extends RecyclerView.ViewHolder {
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
    MaterialTextView donated;
    ImageView badge;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        authorimg = itemView.findViewById(R.id.card_feed_item_data_author_img);
        container = itemView.findViewById(R.id.card_feed_item_data_container_card);
        authorname = itemView.findViewById(R.id.card_feed_item_data_author);
        date = itemView.findViewById(R.id.card_feed_item_data_date);
        title = itemView.findViewById(R.id.card_feed_item_data_title);
        body = itemView.findViewById(R.id.card_feed_item_data_body);
        status = itemView.findViewById(R.id.card_feed_item_data_status);
        needs = itemView.findViewById(R.id.card_feed_item_data_needs);
        city = itemView.findViewById(R.id.card_feed_item_data_city);
        quantity = itemView.findViewById(R.id.card_feed_item_data_max);
        donated = itemView.findViewById(R.id.card_feed_item_data_donated);


    }

   // public void onClick(final int position){
     //   container.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View v) {
        //       Log.i(TAG, "position: " +position);
        //    }
     //   });
   // }
}
