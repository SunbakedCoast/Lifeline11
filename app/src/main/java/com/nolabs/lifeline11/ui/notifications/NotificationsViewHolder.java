package com.nolabs.lifeline11.ui.notifications;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.nolabs.lifeline11.R;

import de.hdodenhof.circleimageview.CircleImageView;

class NotificationsViewHolder  extends RecyclerView.ViewHolder {
    MaterialTextView timestamp;
    MaterialTextView title;
    MaterialCardView dataholder;

    public NotificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.card_notification_item_data_title);
        timestamp = itemView.findViewById(R.id.card_notification_item_data_timestamp);
        dataholder = itemView.findViewById(R.id.card_notification_item_dataholder);
    }
}
