package com.nolabs.lifeline11.ui.home;

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

public class PostCounterViewmodel extends ViewModel {
    private FirebaseAuth fauth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String userID;

    private MutableLiveData<Integer> userpostscounter;

    public PostCounterViewmodel(){
        fauth = FirebaseAuth.getInstance();
        userID = fauth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        DatabaseReference userposts = databaseReference.child(userID).child("UserPosts");

        userpostscounter = new MutableLiveData<>();

        userposts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int postcount = (int) dataSnapshot.getChildrenCount();

                userpostscounter.setValue(postcount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<Integer> getPostOount(){
        return userpostscounter;
    }
}
