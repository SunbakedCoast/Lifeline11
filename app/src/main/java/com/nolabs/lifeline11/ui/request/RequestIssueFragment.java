package com.nolabs.lifeline11.ui.request;

import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.PostRequest;
import com.nolabs.lifeline11.ui.profile.ProfileViewModel;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestIssueFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ProfileViewModel profileViewModel;

    EditText title;
    EditText body;
    EditText address;
    EditText needs;
    EditText quantity;
    MaterialTextView donebtn;
    //Spinner statusspinner;
    Spinner spinnercity;


    FirebaseAuth fauth;
    FirebaseDatabase database;
    DatabaseReference postDatabaseReference;
    DatabaseReference userpostDatabaseReference;
    //DatabaseReference donationDatabaseReference;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        final NavController navigaor = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_request,container, false);
        BottomNavigationView activitynav = getActivity().findViewById(R.id.nav_view);
        activitynav.setVisibility(View.GONE);

        fauth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        postDatabaseReference = database.getReference("Posts");
        userpostDatabaseReference = database.getReference("Users");
        //donationDatabaseReference = database.getReference("Donations");


        title = root.findViewById(R.id.main_fragment_request_input_data_title);
        body = root.findViewById(R.id.main_fragment_request_input_data_body);
        needs = root.findViewById(R.id.main_fragment_request_input_needs);
        donebtn = root.findViewById(R.id.main_fragment_request_btn_done);
        //statusspinner = root.findViewById(R.id.main_fragment_request_status_spinner);
        spinnercity = root.findViewById(R.id.main_fragment_request_data_city);
        address = root.findViewById(R.id.main_fragment_request_input_data_address);
        quantity = root.findViewById(R.id.main_fragment_request_input_quantity);


        ArrayAdapter<CharSequence> cityadapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_city, android.R.layout.simple_spinner_item);
        cityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercity.setAdapter(cityadapter);
        spinnercity.setOnItemSelectedListener(this);

        //ArrayAdapter<CharSequence> statusadapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_status, android.R.layout.simple_spinner_item);
        //statusadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //statusspinner.setAdapter(statusadapter);
        //statusspinner.setOnItemSelectedListener(this);

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String authornamedata = profileViewModel.getDataFullName().getValue();
               // String authorimgdata = profileViewModel.getProfileImg().getValue();
                String titledata = title.getText().toString().trim();
                String bodydata = body.getText().toString().trim();
                String needsdata = needs.getText().toString().trim();
                //String statusspinnerdata = statusspinner.getSelectedItem().toString();
                String userIDdata = fauth.getCurrentUser().getUid();
                String citydata = spinnercity.getSelectedItem().toString();
                String addressdata = address.getText().toString().trim();
                String quantitydata = quantity.getText().toString().trim();
                String timestampdata = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
                String dateString = formatr.format(new Date(Long.parseLong(timestampdata)));
                String statusdata = "Open";








                if (TextUtils.isEmpty(titledata)){
                    title.setError("Title required");
                    return;
                }

                if(TextUtils.isEmpty(bodydata)){
                    body.setError("Description required");
                    return;
                }

                if (TextUtils.isEmpty(needsdata)){
                    needs.setError("Please enter what you needs");
                    return;
                }

                if (TextUtils.isEmpty(addressdata)){
                    address.setError("Please input your donation address");
                }


                if (TextUtils.isEmpty(quantitydata)){
                    quantity.setError("Quantity required");
                }

                DatabaseReference postData = postDatabaseReference.push();


                //inserts data to the posts child
                String hashkey = postData.getKey();
                PostRequest postRequest = new PostRequest(hashkey, titledata, bodydata, dateString, authornamedata, statusdata, needsdata, userIDdata, citydata, addressdata, quantitydata);
                postData.setValue(postRequest);


                //fan out data

                DatabaseReference userpost = userpostDatabaseReference.child(userIDdata).child("UserPosts").child(hashkey);
                //DatabaseReference donors = donationDatabaseReference.child(hashkey);

                userpost.setValue(postRequest);

                navigaor.navigate(R.id.action_navigation_requestIssueFragment_to_navigation_home);

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
