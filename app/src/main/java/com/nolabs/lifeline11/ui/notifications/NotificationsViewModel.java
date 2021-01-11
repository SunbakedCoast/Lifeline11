package com.nolabs.lifeline11.ui.notifications;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationsViewModel extends ViewModel {

    private FirebaseAuth fauth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String userID;

    private MutableLiveData<Integer> notificationcount;

    public NotificationsViewModel(){
        fauth = FirebaseAuth.getInstance();
        userID = fauth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        DatabaseReference notifications = databaseReference.child(userID);

        notificationcount = new MutableLiveData<>();

        notifications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int notifcount = (int) dataSnapshot.child("Notifications").getChildrenCount();

                notificationcount.setValue(notifcount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<Integer> getNotificationCount(){
        return notificationcount;
    }
}
