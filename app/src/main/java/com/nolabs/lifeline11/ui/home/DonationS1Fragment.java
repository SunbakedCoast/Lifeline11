package com.nolabs.lifeline11.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
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

import com.google.android.material.button.MaterialButton;
import com.nolabs.lifeline11.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DonationS1Fragment extends Fragment implements AdapterView.OnItemSelectedListener {


    EditText donationtype;
    MaterialButton nextbtn;
    String titlebundle;
    String hashkey;
   String notifID;
   String bodybundle;
   String locationbundle;
   String needsbundle;
   String statusbundle;
   String datebundle;
   String authorname;
   String addressbunle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        notifID = getArguments().getString("notificationidpostfrag");

        Log.i(TAG, "ID: " +notifID );

        //bundles to be passed on s2
        //authorname = getArguments().getString("postauthor");
       // bodybundle = getArguments().getString("postbody");
        hashkey = getArguments().getString("hash");
        addressbunle = getArguments().getString("postdonationaddress");
       // locationbundle = getArguments().getString("postlocation");
       // needsbundle = getArguments().getString("postneeds");
       // statusbundle = getArguments().getString("poststatus");
       // titlebundle = getArguments().getString("posttitle");
       // datebundle = getArguments().getString("postdate");

       // Log.i(TAG, "body" +bodybundle);
       // Log.i(TAG, "hashkey" +hashkey);
       // Log.i(TAG, "location" +locationbundle);
       // Log.i(TAG, "needs" +needsbundle);
       // Log.i(TAG, "status" +statusbundle);
       // Log.i(TAG, "titlebundle" +titlebundle);
       // Log.i(TAG, "datebundle" +datebundle);
       // Log.i(TAG, "authorname" +authorname);

        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_donation_step_1, container, false);

        donationtype = root.findViewById(R.id.main_fragment_donation_step_1_input_other);
        nextbtn = root.findViewById(R.id.main_fragment_donation_step_1_btn_next);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donationtypedata = donationtype.getText().toString().trim();

                if (TextUtils.isEmpty(donationtypedata)){
                    donationtype.setError("Please specify your donation");
                    return;
                }

                Bundle data = new Bundle();
                data.putString("donationtype", donationtypedata);
                data.putString("notificationidd1frag", notifID);
                data.putString("hash", hashkey);
                data.putString("s1donationaddress", addressbunle);

                //data.putString("bodys1bundle", bodybundle);

               // data.putString("locations1bundle", locationbundle);
               // data.putString("needss1bundle", needsbundle);
               // data.putString("statuss1bundle", statusbundle);
               // data.putString("titles1bundle", titlebundle);
               // data.putString("dates1bundle", datebundle);
               // data.putString("authornames1bundle", authorname);


                navigator.navigate(R.id.action_navigation_donationS1Fragment_to_navigation_donationsS2Fragment, data);

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
