package com.nolabs.lifeline11.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nolabs.lifeline11.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OptionsBottomSheetFragment extends BottomSheetDialogFragment {

    String userID;
    String posthash;
    String notifhashbundle;
    String closed = "Closed";
    String open = "Open";

    MaterialTextView btnclosed;
    MaterialTextView btnedit;
    MaterialTextView btndel;
    MaterialTextView btnopen;

    FirebaseAuth fauth;
    FirebaseDatabase database;
    DatabaseReference postdatabaseReference;
    DatabaseReference userpostsdatabaseReference;
    DatabaseReference donationDatabaseReference;

    //bundles
    String titlebundle;
    String bodybundle;
    String addressbundle;
    String needsbundle;
    String quantitybundle;
    String locationbundle;
    String statusbundle;
    String authorbundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        fauth = FirebaseAuth.getInstance();
        userID = fauth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        postdatabaseReference = database.getReference("Posts");
        //forwarddatabaseReference = database.getReference("closed");
        //notificationreference = database.getReference("Users").child(userID).child("Notifications");

        userpostsdatabaseReference = database.getReference("Users").child(userID).child("UserPosts");
        donationDatabaseReference = database.getReference("Donations");




        //receive bundles here


        //keybundles
        posthash = getArguments().getString("posthash");
        notifhashbundle = getArguments().getString("postnotificationID");

        //post bundles
        titlebundle = getArguments().getString("posttitle");
        bodybundle = getArguments().getString("postbody");
        addressbundle = getArguments().getString("postdonaddressbundle");
        needsbundle = getArguments().getString("postneeds");
        quantitybundle = getArguments().getString("postquantity");
        locationbundle = getArguments().getString("postlocation");
        statusbundle = getArguments().getString("poststatus");
        authorbundle = getArguments().getString("postauthor");


        //DEBUGGING
        //Log.i(TAG, "posthash val: " +posthash);
        //Log.i(TAG, "notifhash val: " +notifhashbundle);
        //Log.i(TAG, "title val: " +titlebundle);
        //Log.i(TAG, "body val: " +bodybundle);
        //Log.i(TAG, "address val: " +addressbundle);
        //Log.i(TAG, "needs val: " +needsbundle);
        //Log.i(TAG, "quantity val: " +quantitybundle);
        //Log.i(TAG, "location val: " +locationbundle);
        //Log.i(TAG, "status val: " +statusbundle);





        View root = inflater.inflate(R.layout.bottom_sheet_menu_options, container, false);



        Log.i(TAG, "HASHKEY: " +posthash);

        btnclosed = root.findViewById(R.id.bottom_sheet_menu_options_btn_markasclosed);
        btnedit = root.findViewById(R.id.bottom_sheet_menu_options_btn_edit);
        btndel = root.findViewById(R.id.bottom_sheet_menu_options_btn_delete);
        btnopen = root.findViewById(R.id.bottom_sheet_menu_options_btn_markasopen);

        btnopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference openstat = postdatabaseReference.child(posthash).child("status");
                openstat.setValue(open);

                DatabaseReference userpostsopen = userpostsdatabaseReference.child(posthash).child("status");
                userpostsopen.setValue(open);

                Snackbar.make(v, "Post has been marked as Open", Snackbar.LENGTH_LONG).show();
                navigator.navigate(R.id.action_navigation_optionsBottomSheetFragment_to_navigation_profile);

            }
        });

        btnclosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   DatabaseReference closestat = postdatabaseReference.child(posthash).child("status");
                   closestat.setValue(closed);

                   DatabaseReference userpostsclose = userpostsdatabaseReference.child(posthash).child("status");
                   userpostsclose.setValue(closed);






                Snackbar.make(v, "Post has been marked as Closed", Snackbar.LENGTH_LONG).show();
                navigator.navigate(R.id.action_navigation_optionsBottomSheetFragment_to_navigation_profile);

            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //passbundles
                Bundle bundle = new Bundle();
                bundle.putString("optionsposthash", posthash);
                bundle.putString("optionsnotifidhash", notifhashbundle);
                bundle.putString("optionstitle", titlebundle);
                bundle.putString("optionsbody", bodybundle);
                bundle.putString("optionsaddress", addressbundle);
                bundle.putString("optionsneeds", needsbundle);
                bundle.putString("optionsquantity", quantitybundle);
                bundle.putString("optionslocation", locationbundle);
                bundle.putString("optionsstatus", statusbundle);
                bundle.putString("optionsauthor", authorbundle);

                navigator.navigate(R.id.action_navigation_optionsBottomSheetFragment_to_navigation_editPostFragment, bundle);
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference delpost = postdatabaseReference.child(posthash);
                delpost.removeValue();

                DatabaseReference userpostdel = userpostsdatabaseReference.child(posthash);
                userpostdel.removeValue();

                DatabaseReference donationsclose = donationDatabaseReference.child(posthash);
                donationsclose.removeValue();



                Snackbar.make(v, "Post has been removed", Snackbar.LENGTH_LONG).show();
                navigator.navigate(R.id.action_navigation_optionsBottomSheetFragment_to_navigation_profile);
            }
        });



        return root;
    }
}
