package com.nolabs.lifeline11.ui.home;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.Donation;
import com.nolabs.lifeline11.ui.profile.ProfileViewModel;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PostFragment extends BottomSheetDialogFragment {
    String TAG = "test";
    ProfileViewModel profileViewModel;

    CircleImageView authorimg;
    MaterialTextView author;
    MaterialTextView title;
    MaterialTextView body;
    MaterialTextView date;
    MaterialTextView contributebtn;
    MaterialTextView status;
    MaterialTextView needs;
    MaterialTextView location;
    RecyclerView recyclerView;
    MaterialTextView address;
    MaterialTextView max;
    ImageView menu;
    ImageView backtbn;


    String hashkey;
    String notifID;
    String titlebundle;
    String userID;
    String closed = "Closed";


    //bundles
    String authorbundle;
    String bodybundle;
    String locationbundle;
    String needsbundle;
    String statusbundle;
    String datebundle;
    String addressbundle;
    String quantitybundle;

    FirebaseAuth fauth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    FirebaseRecyclerOptions<Donation> options;
    FirebaseRecyclerAdapter<Donation, DonationViewHolder> adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        fauth = FirebaseAuth.getInstance();
        userID = fauth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Posts");

        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_selected_item, container, false);
        BottomNavigationView activitynav = getActivity().findViewById(R.id.nav_view);
        activitynav.setVisibility(View.GONE);

        //database
        hashkey = getArguments().getString("hash");
        notifID = getArguments().getString("notificationidcard");
        titlebundle = getArguments().getString("title");

        authorbundle = getArguments().getString("author");
        bodybundle = getArguments().getString("body");
        locationbundle = getArguments().getString("location");
        needsbundle = getArguments().getString("needs");
        statusbundle = getArguments().getString("status");
        titlebundle = getArguments().getString("title");
        datebundle = getArguments().getString("date");
        addressbundle = getArguments().getString("donationaddress");
        quantitybundle = getArguments().getString("quantity");




        authorimg = root.findViewById(R.id.main_fragment_selected_item_data_author_img);
        author = root.findViewById(R.id.main_fragment_selected_item_data_author);
        title = root.findViewById(R.id.main_fragment_selected_item_data_title);
        body = root.findViewById(R.id.main_fragment_selected_item_data_body);
        date = root.findViewById(R.id.main_fragment_selected_item_data_date);
        contributebtn = root.findViewById(R.id.main_fragment_selected_item_btn_contribute);
        status = root.findViewById(R.id.main_fragment_selected_item_data_status);
        needs = root.findViewById(R.id.main_fragment_selected_item_data_needs);
        location = root.findViewById(R.id.main_fragment_selected_item_data_city);
        menu = root.findViewById(R.id.main_fragment_selected_item_btn_options);
        recyclerView = root.findViewById(R.id.main_fragment_selected_item_recyclerview);
        backtbn = root.findViewById(R.id.main_fragment_selected_item_btn_back);
        address = root.findViewById(R.id.main_fragment_selected_item_data_address);
        max = root.findViewById(R.id.main_fragment_selected_item_data_target);

        backtbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_postFragment_to_navigation_home);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle hashbtm = new Bundle();
                hashbtm.putString("posthash", hashkey);

               hashbtm.putString("posttitle", titlebundle);
               hashbtm.putString("postbody", bodybundle);
               hashbtm.putString("postdonaddressbundle", addressbundle);
               hashbtm.putString("postneeds", needsbundle);
               hashbtm.putString("postquantity", quantitybundle);
               hashbtm.putString("postlocation", locationbundle);
               hashbtm.putString("poststatus", statusbundle);
               hashbtm.putString("postnotificationID", notifID);
               hashbtm.putString("postauthor", authorbundle);


                navigator.navigate(R.id.action_navigation_postFragment_to_navigation_optionsBottomSheetFragment, hashbtm);
            }
        });

        contributebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle hash = new Bundle();
                hash.putString("hash", hashkey);
                hash.putString("notificationidpostfrag", notifID);
                hash.putString("postdonationaddress", addressbundle);





                hash.putString("titlepostfrag", titlebundle);
                navigator.navigate(R.id.action_navigation_postFragment_to_navigation_donationS1Fragment, hash);
            }
        });




        author.setText(getArguments().getString("author"));
        title.setText(getArguments().getString("title"));
        body.setText(getArguments().getString("body"));
        date.setText(getArguments().getString("date"));
        status.setText(getArguments().getString("status"));
        needs.setText(getArguments().getString("needs"));
        location.setText(getArguments().getString("location"));
        max.setText(getArguments().getString("quantity"));
        address.setText(addressbundle);

       // Picasso.with(getActivity()).load(getArguments().getString("authorimg")).into(authorimg);


        if (notifID.equals(userID)){
            contributebtn.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
            Log.i(TAG, "MATCHED");
        } if (statusbundle.equals(closed)){
            contributebtn.setVisibility(View.GONE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getData();


        return root;
    }


    private void getData(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference donorref = reference.child("Donations");
        Query query = donorref.child(hashkey);

        Log.i(TAG, "DATA: " +query);

        options = new FirebaseRecyclerOptions.Builder<Donation>().setQuery(query, Donation.class).build();

        adapter = new FirebaseRecyclerAdapter<Donation, DonationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DonationViewHolder holder, int position, @NonNull Donation model) {
                //Picasso.with(getActivity()).load(model.getDonorimg()).into(holder.donorimg);
                holder.name.setText(model.getName());
                holder.donated.setText(model.getDonated());
                holder.date.setText(model.getDate());
            }

            @NonNull
            @Override
            public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.card_donor_item, parent, false);

                return new DonationViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
