package com.nolabs.lifeline11.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.Donation;
import com.nolabs.lifeline11.Utilities.utilities.Model.Notifications;
import com.nolabs.lifeline11.ui.profile.ProfileViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DonationsS2Fragment extends Fragment {

    ProfileViewModel profileViewModel;
    Donation donation;
    int i;

    MaterialButton donebtn;
    MaterialTextView address;

    String donationtypedata;
    String userID;
    String notifID;

    String body;
    String hashkey;
    String location;
    String needs;
    String status;
    String titlebundle;
    String datebundle;
    String authorname;
    String addressbunle;

    FirebaseAuth fauth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference donationDatabaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        //bundle data
        donationtypedata = getArguments().getString("donationtype");

        notifID = getArguments().getString("notificationidd1frag");

        //bundles to receive from s1
        //body = getArguments().getString("bodys1bundle");
        hashkey = getArguments().getString("hash");
        addressbunle = getArguments().getString("s1donationaddress");
        //location = getArguments().getString("locations1bundle");
        //needs = getArguments().getString("needss1bundle");
        //status = getArguments().getString("statuss1bundle");
        //titlebundle = getArguments().getString("titles1bundle");
        //datebundle = getArguments().getString("dates1bundle");
        //authorname = getArguments().getString("authornames1bundle");

        //Log.i(TAG, "body" +body);
        //Log.i(TAG, "hashkey" +hashkey);
        //Log.i(TAG, "location" +location);
        //Log.i(TAG, "needs" +needs);
        //Log.i(TAG, "status" +status);
        //Log.i(TAG, "titlebundle" +titlebundle);
        //Log.i(TAG, "datebundle" +datebundle);
        //Log.i(TAG, "authorname" +authorname);


        fauth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Posts");
        donationDatabaseReference = database.getReference("Donations");
        final DatabaseReference user = database.getReference("Users");


        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_donation_step_2, container, false);

        donebtn = root.findViewById(R.id.main_fragment_donation_step_2_btn_done);
        address = root.findViewById(R.id.main_fragment_donation_step_2_data_address);

        address.setText(addressbunle);

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = fauth.getCurrentUser().getUid();
                boolean donated = true;

                String timestampdata = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
                String dateString = formatr.format(new Date(Long.parseLong(timestampdata)));




                //String donorimg = profileViewModel.getProfileImg().getValue();
                String donorname = profileViewModel.getDataFullName().getValue();
                String posttitle = getArguments().getString("titled1");

                Log.i(TAG, "bundle" +posttitle);

                //DatabaseReference donors = databaseReference.child(hashkey);
                //DatabaseReference contributors = donors.child("Donors").push();
                //DatabaseReference donorsuserposts = user.child(notifID).child("UserPosts").child(hashkey).child("Donors").push();
                DatabaseReference donors = donationDatabaseReference.child(hashkey).push();
                Donation donation = new Donation(userID, donorname, donationtypedata, dateString);
                donors.setValue(donation);
                //contributors.setValue(donation);
               // donorsuserposts.setValue(donation);


                //DatabaseReference notifdatabaseref = user.child(notifID).child("Notifications");
                //Notifications datanotif = new Notifications(donorname, posttitle, dateString, donationtypedata, body, hashkey, location, needs, status, titlebundle, datebundle, authorname);
                //DatabaseReference notification = notifdatabaseref.child(hashkey);
                //notification.setValue(datanotif);

                navigator.navigate(R.id.action_navigation_donationsS2Fragment_to_navigation_home);
            }
        });

        return root;
    }



}
