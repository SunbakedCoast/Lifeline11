package com.nolabs.lifeline11.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.Donation;
import com.nolabs.lifeline11.Utilities.utilities.Model.PostRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EditPostFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    MaterialTextView btnupdate;
    EditText title;
    EditText body;
    EditText address;
    EditText needs;
    EditText quantity;
    Spinner city;
    Spinner status;


    String posthashbundle;
    String notifhashbundle;
    String titlebundle;
    String bodybundle;
    String addressbundle;
    String needsbundle;
    String quantitybundle;
    String locationbundle;
    String statusbundle;
    String authorbundle;

    FirebaseAuth fauth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;



    String userID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fauth = FirebaseAuth.getInstance();
        userID = fauth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);



        View root = inflater.inflate(R.layout.main_fragment_edit_post, container, false);

        btnupdate = root.findViewById(R.id.main_fragment_edit_post_btn_update);
        title = root.findViewById(R.id.main_fragment_edit_post_input_title);
        body = root.findViewById(R.id.main_fragment_edit_post_input_body);
        address = root.findViewById(R.id.main_fragment_edit_post_input_address);
        needs = root.findViewById(R.id.main_fragment_edit_post_input_needs);
        quantity = root.findViewById(R.id.main_fragment_edit_post_input_quantity);
        city = root.findViewById(R.id.main_fragment_edit_post_spinner_city);
        status = root.findViewById(R.id.main_fragment_edit_post_spinner_status);


        ArrayAdapter<CharSequence> cityadapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_city, android.R.layout.simple_spinner_item);
        cityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityadapter);
        city.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> statusadapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_status, android.R.layout.simple_spinner_item);
        statusadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusadapter);
        status.setOnItemSelectedListener(this);

        //bundles from options
        posthashbundle = getArguments().getString("optionsposthash");
        notifhashbundle = getArguments().getString("optionsnotifidhash");

        titlebundle = getArguments().getString("optionstitle");
        bodybundle = getArguments().getString("optionsbody");
        addressbundle = getArguments().getString("optionsaddress");
        needsbundle = getArguments().getString("optionsneeds");
        quantitybundle = getArguments().getString("optionsquantity");
        //locationbundle = getArguments().getString("optionslocation");
        //statusbundle = getArguments().getString("optionsstatus");
        authorbundle = getArguments().getString("optionsauthor");

        Log.i(TAG, "posthash val: " +posthashbundle);
        Log.i(TAG, "notifhash val: " +notifhashbundle);


        title.setText(titlebundle);
        body.setText(bodybundle);
        address.setText(addressbundle);
        needs.setText(needsbundle);
        quantity.setText(quantitybundle);

        //UPDATE USERPOST AND POST HERE

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titlenew = title.getText().toString().trim();
                String bodynew = body.getText().toString().trim();
                String addressnew = address.getText().toString().trim();
                String needsnew = needs.getText().toString().trim();
                String quantitynew = quantity.getText().toString().trim();

                String locationdata = city.getSelectedItem().toString();
                String statusdata = status.getSelectedItem().toString();

                String timestampdata = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
                String dateString = formatr.format(new Date(Long.parseLong(timestampdata)));




                DatabaseReference postreference = databaseReference.child("Posts").child(posthashbundle);
                DatabaseReference userpostreference = databaseReference.child("Users").child(userID).child("UserPosts").child(posthashbundle);
                PostRequest postRequest = new PostRequest(posthashbundle, titlenew, bodynew, dateString, authorbundle, statusdata, needsnew, notifhashbundle, locationdata, addressnew, quantitynew);
                postreference.setValue(postRequest);
                userpostreference.setValue(postRequest);

                navigator.navigate(R.id.action_navigation_editPostFragment_to_navigation_home);
            }
        });



        return root;
    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
