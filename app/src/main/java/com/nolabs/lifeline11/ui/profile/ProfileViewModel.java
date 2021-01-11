package com.nolabs.lifeline11.ui.profile;

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

public class ProfileViewModel  extends ViewModel {
    private FirebaseAuth fauth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String userID;


    private MutableLiveData<String> fullname;
   // private MutableLiveData<String> profileimg;

    public ProfileViewModel(){
        fauth = FirebaseAuth.getInstance();
        userID = fauth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        DatabaseReference userdata = databaseReference.child(userID);
        DatabaseReference usermetadata = databaseReference.child(userID).child("Metadata");

        fullname = new MutableLiveData<>();

     //   profileimg = new MutableLiveData<>();

        userdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fullnamedata = (String) dataSnapshot.child("fullname").getValue();

                fullname.setValue(fullnamedata);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public LiveData<String> getDataFullName(){
        return fullname;
    }

    //public LiveData<String> getProfileImg(){
      //  return profileimg;
  //  }




}
